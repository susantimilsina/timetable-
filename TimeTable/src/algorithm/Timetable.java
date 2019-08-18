package algorithm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import timetable.DB_Connect;
import timetable.Dashboard;

public class Timetable {

    private final HashMap<Integer, Room> rooms;
    private final HashMap<Integer, Professor> professors;
    private final HashMap<Integer, Module> modules;
    private final HashMap<Integer, Group> groups;
    private final HashMap<Integer, Timeslot> timeslots;
    private Class classes[];

    private int numClasses = 0;

    public Timetable() {
        this.rooms = new HashMap<Integer, Room>();
        this.professors = new HashMap<Integer, Professor>();
        this.modules = new HashMap<Integer, Module>();
        this.groups = new HashMap<Integer, Group>();
        this.timeslots = new HashMap<Integer, Timeslot>();
    }

    public Timetable(Timetable cloneable) {
        this.rooms = cloneable.getRooms();
        this.professors = cloneable.getProfessors();
        this.modules = cloneable.getModules();
        this.groups = cloneable.getGroups();
        this.timeslots = cloneable.getTimeslots();
    }

    public HashMap<Integer, Group> getGroups() {
        return this.groups;
    }

    public HashMap<Integer, Timeslot> getTimeslots() {
        return this.timeslots;
    }

    public HashMap<Integer, Module> getModules() {
        return this.modules;
    }

    public HashMap<Integer, Professor> getProfessors() {
        return this.professors;
    }

    public void addRoom(int roomId, String roomName, int capacity) {
        this.rooms.put(roomId, new Room(roomId, roomName, capacity));
    }

    public void addProfessor(int professorId, String professorName) {
        this.professors.put(professorId, new Professor(professorId, professorName));
    }

    public void addModule(int moduleId, String moduleCode, String module, int professorIds[]) {
        this.modules.put(moduleId, new Module(moduleId, moduleCode, module, professorIds));
    }

    public void addGroup(int groupId, String groupname, int groupSize, int moduleIds[]) {
        this.groups.put(groupId, new Group(groupId, groupname, groupSize, moduleIds));
        this.numClasses = 0;
    }

    public void addTimeslot(int timeslotId, String timeslot) {
        this.timeslots.put(timeslotId, new Timeslot(timeslotId, timeslot));
    }

    public void createClasses(Individual individual) {
        Class classes[] = new Class[this.getNumClasses()];

        int chromosome[] = individual.getChromosome();
        int chromosomePos = 0;
        int classIndex = 0;

        for (Group group : this.getGroupsAsArray()) {
            int moduleIds[] = group.getModuleIds();
            for (int moduleId : moduleIds) {
                classes[classIndex] = new Class(classIndex, group.getGroupId(), moduleId);

                classes[classIndex].addTimeslot(chromosome[chromosomePos]);
                chromosomePos++;

                classes[classIndex].setRoomId(chromosome[chromosomePos]);
                chromosomePos++;

                classes[classIndex].addProfessor(chromosome[chromosomePos]);
                chromosomePos++;

                classIndex++;
            }
        }

        this.classes = classes;
    }

    public Room getRoom(int roomId) {
        if (!this.rooms.containsKey(roomId)) {
            System.out.println("Rooms doesn't contain key " + roomId);
        }
        return (Room) this.rooms.get(roomId);
    }

    public HashMap<Integer, Room> getRooms() {
        return this.rooms;
    }

    public Room getRandomRoom() {
        Object[] roomsArray = this.rooms.values().toArray();
        Room room = (Room) roomsArray[(int) (roomsArray.length * Math.random())];
        return room;
    }

    public Professor getProfessor(int professorId) {
        return (Professor) this.professors.get(professorId);
    }

    public Module getModule(int moduleId) {
        return (Module) this.modules.get(moduleId);
    }

    public int[] getGroupModules(int groupId) {
        Group group = (Group) this.groups.get(groupId);
        return group.getModuleIds();
    }

    public Group getGroup(int groupId) {
        return (Group) this.groups.get(groupId);
    }

    public Group[] getGroupsAsArray() {
        return (Group[]) this.groups.values().toArray(new Group[this.groups.size()]);
    }

    public Timeslot getTimeslot(int timeslotId) {
        return (Timeslot) this.timeslots.get(timeslotId);
    }

    public Timeslot getRandomTimeslot() {
        Object[] timeslotArray = this.timeslots.values().toArray();
        Timeslot timeslot = (Timeslot) timeslotArray[(int) (timeslotArray.length * Math.random())];
        return timeslot;
    }

    public Class[] getClasses() {
        return this.classes;
    }

    public int getNumClasses() {
        if (this.numClasses > 0) {
            return this.numClasses;
        }

        int numClasses = 0;
        Group groups[] = (Group[]) this.groups.values().toArray(new Group[this.groups.size()]);
        for (Group group : groups) {
            numClasses += group.getModuleIds().length;
        }
        this.numClasses = numClasses;

        return this.numClasses;
    }

    public int calcClashes() {
        int clashes = 0;
        for (Class classA : this.classes) {
            int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
            int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();

            if (roomCapacity < groupSize) {
                clashes++;
            }
            
            for (Class classB : this.classes) {
                if (classA.getRoomId() == classB.getRoomId() && classA.getTimeslotId() == classB.getTimeslotId()
                        && classA.getClassId() != classB.getClassId()) {
                    clashes++;
                    break;
                }
                
            }

            for (Class classB : this.classes) {
                if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId()
                        && classA.getClassId() != classB.getClassId()) {
                    clashes++;
                    break;
                }
            }
            for (Class classB : this.classes) {
                if (classA.getGroupId() == classB.getGroupId() && classA.getTimeslotId() == classB.getTimeslotId()
                        && classA.getClassId() != classB.getClassId()) {
                    clashes++;
                    break;
                }
            }
            if(Dashboard.existingClasses!=null){
                for(ExistingClass classB:Dashboard.existingClasses){
                   if(Dashboard.t.getRoom(classA.getRoomId()).getRoomNumber().equals(Integer.toString(classB.classNumber))
                           && Dashboard.t.getTimeslot(classA.getTimeslotId()).getTimeslot().equals(classB.timeslot)) {
                       clashes++;
                       break;
                   }
                   if(Dashboard.t.getProfessor(classA.getProfessorId()).getProfessorName().equals((classB.professor))
                           && Dashboard.t.getTimeslot(classA.getTimeslotId()).getTimeslot().equals(classB.timeslot)) {
                       clashes++;
                       break;
                   }
                   if(Dashboard.t.getGroup(classA.getGroupId()).getGroupName().equals((classB.group))
                           && Dashboard.t.getTimeslot(classA.getTimeslotId()).getTimeslot().equals(classB.timeslot)) {
                       clashes++;
                       break;
                   }
                }
            }
        }
        

        return clashes;
//        int clashes = 0;
//        try {
//
//            Timetable timetable = new Timetable();
//            DB_Connect dc = new DB_Connect();
//            ResultSet rs = null;
//            dc.connectDatabase();
//            Statement s = dc.conn.createStatement();
//            String query = null;
//            String timeSlot = null;
//            int timeID = -1;
//            for (Class classA : this.classes) {
//                int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
//                int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
//
//                if (roomCapacity < groupSize) {
//                    clashes++;
//                }
////                timeSlot = Dashboard.t.getTimeslot(classA.getTimeslotId()).getTimeslot();
////                query = "SELECT timeID FROM classtime where startTime='"+timeSlot.substring(0,timeSlot.indexOf('-'))+"' AND endTime='"+timeSlot.substring(timeSlot.indexOf('-')+1)+"';";
////                System.out.println("timm :: "+query);
////                rs = s.executeQuery(query);
////                while(rs.next()){
////                    timeID=Integer.parseInt(rs.getString(1));
////                }
////                query = "select * from schedule where classNumber=" + Dashboard.t.getRoom(classA.getRoomId()).getRoomNumber() + " AND classTimeID='"+timeID+"';";
////                System.out.println("clash for room ::"+query);
////                rs = s.executeQuery(query);
////                if (rs.isBeforeFirst()) {
//////                    clashes++;
////                }
//                for (Class classB : this.classes) {
//                    if (classA.getRoomId() == classB.getRoomId() && classA.getTimeslotId() == classB.getTimeslotId()
//                            && classA.getClassId() != classB.getClassId()) {
//                        clashes++;
//                        break;
//                    }
//
//                }
//
//                for (Class classB : this.classes) {
//                    if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId()
//                            && classA.getClassId() != classB.getClassId()) {
//                        clashes++;
//                        break;
//                    }
//                }
//                for (Class classB : this.classes) {
//                    if (classA.getGroupId() == classB.getGroupId() && classA.getTimeslotId() == classB.getTimeslotId()
//                            && classA.getClassId() != classB.getClassId()) {
//                        clashes++;
//                        break;
//                    }
//                }
//            }
//            dc.closeDBConnection();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Timetable.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return clashes;
    }

    public int finfFirstRoom(int moduleId) {
        int roomid = -1;
        for (Class c : this.classes) {
            if (c.getModuleId() == moduleId) {
                roomid = c.getRoomId();
                break;
            }
        }
        return roomid;
    }
}
