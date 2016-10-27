package co.example.michaekgrundie.rollcallnrc;


/**
 * Created by MichaekGrundie on 14/12/2015.
 */
public class ClassInfo {

    private String className, classCode, classDay;

    private int classStartTime, classFinishTime, noOfStudents;

    private Student[] classStudents;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassDay(String classDay) {
        this.classDay = classDay;
    }

    public String getClassDay() {
        return classDay;
    }

    public void setClassStartTime(int classStartTime) {
        this.classStartTime = classStartTime;
    }

    public int getClassStartTime() {
        return classStartTime;
    }

    public void setClassFinishTime(int classFinishTime) {
        this.classFinishTime = classFinishTime;
    }

    public int getClassFinishTime() {
        return classFinishTime;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }
}
