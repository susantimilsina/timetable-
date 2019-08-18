/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

/**
 *
 * @author acer
 */
public class ExistingClass {
    public int classNumber;
    public String timeslot;
    public String group;
    public String professor;
    public String subjectName;

    public ExistingClass(int classNumber, String timeslot, String group, String professor, String subjectName) {
        this.classNumber = classNumber;
        this.timeslot = timeslot;
        this.group = group;
        this.professor = professor;
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "ExistingClass{" + "classNumber=" + classNumber + ", timeslot=" + timeslot + ", group=" + group + ", professor=" + professor + '}';
    }
    
    
}
