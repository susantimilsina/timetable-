package algorithm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timetable.DB_Connect;

/**
 * Don't be daunted by the number of classes in this chapter -- most of them are
 * just simple containers for information, and only have a handful of properties
 * with setters and getters.
 *
 * The real stuff happens in the GeneticAlgorithm class and the Route class.
 *
 * The Route class is what the genetic algorithm is expected to create a valid
 * version of -- meaning, after all is said and done, a chromosome is read into
 * a Route class, and the Route class creates a nicer, neater representation of
 * the chromosome by turning it into a proper list of Path
 *
 *
 * Finally, we overload the Route class by entrusting it with the "database
 * information" generated here in initializeRoute. Normally, that provides
 * information about path between cities
 *
 * @author acer
 *
 */
public class TimetableGa {

    public TimetableGa(List gl, List tl, List cl) {

        Timetable timetable = initializeTimetable(gl, tl, cl);

        /*
        HashMap<Integer, Module> modules = timetable.getModules();
        for (int i = 0; i < modules.size(); i++) {
            String a=modules.get(i).getModuleId() + "=" + modules.get(i).getModuleCode()+"taught by ";
            int[] ids=modules.get(i).getprofessorIds();
            for(int j=0;j<ids.length;j++){
                a+=ids[j]+",";
            }
            System.out.println(a);
        }
        HashMap<Integer, Room> rooms = timetable.getRooms();
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i).getRoomId() + "=" + rooms.get(i).getRoomNumber());

        }

        HashMap<Integer, Professor> professors = timetable.getProfessors();
        for (int i = 0; i < professors.size(); i++) {
            System.out.println(professors.get(i).getProfessorId() + "=" + professors.get(i).getProfessorName());

        }
        
        HashMap<Integer, Timeslot> timeslots = timetable.getTimeslots();
        for (int i = 0; i < timeslots.size(); i++) {
            System.out.println(timeslots.get(i).getTimeslotId() + "=" + timeslots.get(i).getTimeslot());

        }
        HashMap<Integer, Group> groups = timetable.getGroups();
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(groups.get(i).toString());
        }
        
         */
 /*
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        Population population = ga.initPopulation(timetable);
        ga.evalPopulation(population, timetable);
        int generation = 1;
        while (ga.isTerminationConditionMet(generation, 1000) == false
        && ga.isTerminationConditionMet(population) == false) {
        System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
        population = ga.crossoverPopulation(population);
        population = ga.mutatePopulation(population, timetable);
        ga.evalPopulation(population, timetable);
        generation++;
        }
        timetable.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + timetable.calcClashes());
        System.out.println();
        Class classes[] = timetable.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
        System.out.println("Class " + classIndex + ":");
        System.out.println("Module: "
        + timetable.getModule(bestClass.getModuleId()).getModuleName());
        System.out.println("Group: "
        + timetable.getGroup(bestClass.getGroupId()).getGroupId());
        System.out.println("Room: "
        + timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
        System.out.println("Professor: "
        + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
        System.out.println("Time: "
        + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
        System.out.println("-----");
        classIndex++;
        }
         */
    }

    public static int findIndex(String arr[], String t) {

        if (arr == null) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (arr[i].equals(t)) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    private static Timetable initializeTimetable(List gl, List tl, List cl) {
        Timetable timetable = new Timetable();
        DB_Connect dc = new DB_Connect();
        ResultSet rs = null;
        dc.connectDatabase();
        int i;
        try {
            Statement s = dc.conn.createStatement();
            String query = null;
            i = 0;
            String capacity = null;
            for (Object classNumber : cl) {
                query = "select classCapacity from classroom where classNumber='" + classNumber.toString() + "'";
                rs = s.executeQuery(query);
                while (rs.next()) {
                    capacity = rs.getString("classCapacity");

                }
                timetable.addRoom(i, classNumber.toString(), Integer.parseInt(capacity));
                i = i + 1;
            }

            i = 0;
            for (Object time : tl) {
                timetable.addTimeslot(i, time.toString());
                i = i + 1;
            }

            query = "SELECT DISTINCT t.professorUsername from subject s inner join teaches t on s.code = t.subjectCode WHERE (";
            for (Object fac : gl) {
                query += "s.faculty='" + fac.toString().substring(0, fac.toString().indexOf("-")) + "' or ";
            }
            query = query.substring(0, query.length() - 3);
            query += ") and (";
            for (Object fac : gl) {
                query += "s.semester='" + fac.toString().substring(fac.toString().indexOf("-") + 1) + "' or ";
            }
            query = query.substring(0, query.length() - 3) + ")";
            rs = s.executeQuery(query);
            rs.last();
            String[] prof = new String[rs.getRow()];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                prof[i] = rs.getString("professorUsername");
                timetable.addProfessor(i, rs.getString("professorUsername"));
                i = i + 1;
            }

            int[] subjects = null;
            ResultSet rs1 = null;
            Statement s1 = null;
            s1 = dc.conn.createStatement();
            int[] teachingProf = null;
            int l = 0;
            i = 0;
            for (Object fac : gl) {
                int k = 0;
                query = "select s.name,s.code from subject s "
                        + "where s.faculty='" + fac.toString().substring(0, fac.toString().indexOf("-")) + "' and s.semester='"
                        + fac.toString().substring(fac.toString().indexOf("-") + 1) + "';";
                rs = s.executeQuery(query);

                rs.last();
                subjects = new int[rs.getRow()];
                rs.beforeFirst();
                while (rs.next()) {
                    query = "select professorUsername from teaches where subjectCode='" + rs.getString("code") + "'";
                    rs1 = s1.executeQuery(query);
                    rs1.last();
                    teachingProf = new int[rs1.getRow()];
                    rs1.beforeFirst();
                    int j = 0;

                    while (rs1.next()) {
                        teachingProf[j] = findIndex(prof, rs1.getString("professorUsername"));
                        j = j + 1;
                    }
                    timetable.addModule(i, rs.getString("code"), rs.getString("name"), teachingProf);
                    subjects[k] = i;
                    k = k + 1;
                    i = i + 1;
                }
                query = "select s.NumberOfStudents from studentgroup s "
                        + "where s.faculty='" + fac.toString().substring(0, fac.toString().indexOf("-")) + "' and s.semester='"
                        + fac.toString().substring(fac.toString().indexOf("-") + 1) + "';";
                rs = s.executeQuery(query);
                while (rs.next()) {
                    timetable.addGroup(l, fac.toString(), Integer.parseInt(rs.getString("NumberOfStudents")), subjects);
                    l = l + 1;
                }

            }

            dc.closeDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TimetableGa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timetable;

    }

}
