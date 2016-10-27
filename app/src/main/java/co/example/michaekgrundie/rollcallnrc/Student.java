package co.example.michaekgrundie.rollcallnrc;

/**
 * Created by MichaekGrundie on 14/12/2015.
 */
public class Student {

    private String studentName, studentNumber, className;
    private int studentPhoneNumber;

    public Student()
    {

    }

    public void setStudentName (String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentPhoneNumber(int studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public int getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
