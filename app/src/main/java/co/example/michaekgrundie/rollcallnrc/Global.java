package co.example.michaekgrundie.rollcallnrc;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by MichaekGrundie on 14/12/2015.
 */
public class Global extends Application {

    private int classesAdded = 0, studentsAdded = 0, studentsInClass =0;
    private ClassInfo[] classes = new ClassInfo[classesAdded];
    private Student[] students = new Student[studentsAdded];
    private String classToView;
    public boolean runonce1 =false, runonce2 =false;
   //private Student[] students = new Student[studentsAdded];

    public void addClass (ClassInfo classInfo)
    {
        classesAdded++;
        ClassInfo[] newClasses = new ClassInfo[classesAdded];

        for (int i= 0; i<classes.length; i++)
        {
            newClasses[i] = classes[i];
        }
        classes = newClasses;
        classes[classesAdded-1] = classInfo;
    }

    public void removeClass (String className)
    {
        classesAdded--;
        ClassInfo[] newClasses = new ClassInfo[classesAdded];
        int newClassesIndex = 0;

        for (int i= 0; i< classes.length; i++) {
            if (classes[i].getClassName().equalsIgnoreCase(className)) {

            } else {
                newClasses[newClassesIndex] = classes[i];
                newClassesIndex++;
            }
        }
        classes = newClasses;

        for (int i = 0; i< students.length; i++)
        {
            if (students[i].getClassName().equalsIgnoreCase(className))
            {
                removeStudent(students[i].getStudentName());
                i--;
            }
        }
    }

    public int getStudentsInClass() {
        return studentsInClass;
    }

    public int getClassesAdded() {
        return classesAdded;
    }

    public int getStudentsAdded() {
        return studentsAdded;
    }

    public ClassInfo[] getClasses() {
        return classes;
    }

    public Student[] getStudents() {
        return students;
    }

    public Student[] getStudentsOfCurrentCLass(String className)
    {
        studentsInClass = 0;
        Student[] studentsOfCurrentClass = new Student[studentsInClass];

        for (int i =0; i<students.length; i++)
        {
            if(students[i].getClassName().equalsIgnoreCase(className))
            {
                studentsInClass++;
                Student[] newStudentsOfCurrentClass = new Student[studentsInClass];
                for (int j =0; j<studentsOfCurrentClass.length; j++)
                {
                    newStudentsOfCurrentClass[j] = studentsOfCurrentClass[j];
                }
                newStudentsOfCurrentClass[studentsInClass-1] = students[i];
                studentsOfCurrentClass =newStudentsOfCurrentClass;
            }

        }
        return studentsOfCurrentClass;
    }

    public void setClassToView (String className)
    {
        classToView = className;
    }

    public String getClassToView()
    {
        return classToView;
    }

    public void addStudent (Student student)
    {
        studentsAdded++;
        studentsInClass++;
        Student[] newStudents = new Student[studentsAdded];

        for (int i= 0; i<students.length; i++)
        {
            newStudents[i] = students[i];
        }
        students = newStudents;
        students[studentsAdded-1] = student;
    }

    public ClassInfo getClassInfo()
    {
        ClassInfo toReturn = new ClassInfo();
        for (int i = 0; i<classes.length; i++)
        {
            if(classes[i].getClassName().equalsIgnoreCase(classToView))
            {
                toReturn = classes[i];
                break;
            }
        }
        return toReturn;
    }

    public Student getStudentInfo(String studentName)
    {
        Student toReturn = new Student();
        for (int i = 0; i<students.length; i++)
        {
            if(students[i].getStudentName().equalsIgnoreCase(studentName) &&
                    students[i].getClassName().equalsIgnoreCase(classToView))
            {
                toReturn = students[i];
                break;
            }
        }
        return toReturn;
    }


    public void removeStudent (String studentName)
    {
        studentsAdded--;
        studentsInClass--;
        Student[] newStudents = new Student[studentsAdded];
        int newStudentsIndex = 0;

        for (int i= 0; i< students.length; i++) {
            if (students[i].getStudentName().equalsIgnoreCase(studentName) &&
                    students[i].getClassName().equalsIgnoreCase(classToView)) {

            } else {
                newStudents[newStudentsIndex] = students[i];
                newStudentsIndex++;
            }
        }
        students = newStudents;
    }

}
