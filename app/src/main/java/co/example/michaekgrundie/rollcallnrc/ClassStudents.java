package co.example.michaekgrundie.rollcallnrc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ClassStudents extends AppCompatActivity {

    TextView className, classCode, classTimes,nameView, stuNoView, telView;
    EditText name, stuNo, telNo;
    Button view, add, remove, submit;
    Spinner stuList;
    ArrayAdapter<String> adapter;
    Global global;
    boolean viewDone = false, submitAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        global = (Global) getApplicationContext();

        className = (TextView) findViewById(R.id.txtClassName);
        classCode = (TextView) findViewById(R.id.txtClassCode);
        classTimes = (TextView) findViewById(R.id.txtClassTimes);
        nameView = (TextView) findViewById(R.id.txtStudentName);
        stuNoView = (TextView) findViewById(R.id.txtStudentNo);
        telView = (TextView) findViewById(R.id.txtStudentTel);
        name = (EditText) findViewById(R.id.editTxtName);
        stuNo = (EditText) findViewById(R.id.editTextStuNo);
        telNo = (EditText) findViewById(R.id.editTextPhoneNo);
        view = (Button) findViewById(R.id.btnView);
        add = (Button) findViewById(R.id.btnAddStudent);
        remove = (Button) findViewById(R.id.btnRemoveStudent);
        submit = (Button) findViewById(R.id.btnSubmit);
        stuList = (Spinner) findViewById(R.id.spinnerStudentList);

        //FOR TESTING
        if(!global.runonce2)
        {
            testData();
            global.runonce2 =true;
        }
        //FOR TESTING



        refreshClassDetails();
        refreshButtons();
        refreshStudentList();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewDone)
                {
                    view.setText("OK");
                    add.setVisibility(View.INVISIBLE);
                    remove.setVisibility(View.INVISIBLE);
                    nameView.setVisibility(View.VISIBLE);
                    stuNoView.setVisibility(View.VISIBLE);
                    telView.setVisibility(View.VISIBLE);
                    setStudentDetails(stuList.getSelectedItem().toString());
                    stuList.setVisibility(View.INVISIBLE);
                    viewDone=true;

                }else
                {
                    view.setText("View");
                    add.setVisibility(View.VISIBLE);
                    remove.setVisibility(View.VISIBLE);
                    nameView.setVisibility(View.INVISIBLE);
                    stuNoView.setVisibility(View.INVISIBLE);
                    telView.setVisibility(View.INVISIBLE);
                    stuList.setVisibility(View.VISIBLE);
                    viewDone=false;
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stuList.setVisibility(View.INVISIBLE);
                add.setVisibility(View.INVISIBLE);
                remove.setVisibility(View.INVISIBLE);
                view.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                stuNo.setVisibility(View.VISIBLE);
                telNo.setVisibility(View.VISIBLE);
                submitAdd = true;




            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setVisibility(View.INVISIBLE);
                remove.setVisibility(View.INVISIBLE);
                view.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
                submitAdd = false;

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stuList.setVisibility(View.VISIBLE);
                add.setVisibility(View.VISIBLE);
                remove.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                submit.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);
                stuNo.setVisibility(View.INVISIBLE);
                telNo.setVisibility(View.INVISIBLE);
                if (submitAdd)
                {
                    addNewStudent();

                }else
                {
                    global.removeStudent(stuList.getSelectedItem().toString());
                }
                refreshStudentList();
                refreshButtons();

            }
        });
    }


    public void refreshStudentList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                studentNames(global.getStudentsOfCurrentCLass(global.getClassToView())));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stuList.setAdapter(adapter);
    }

    public String[] studentNames(Student[] students)
    {
        String[] studentNames = new String[students.length];

        for (int i =0; i<students.length; i++)
        {
            String name = students[i].getStudentName();
            studentNames[i] = name;
        }
        return studentNames;

    }

    public void addNewStudent()
    {
        Student student = new Student();
        student.setStudentName(name.getText().toString());
        student.setStudentNumber(stuNo.getText().toString());
        student.setStudentPhoneNumber(Integer.parseInt(telNo.getText().toString()));
        student.setClassName(global.getClassToView());
        global.addStudent(student);

    }

    public void refreshButtons()
    {
        global.getStudentsOfCurrentCLass(global.getClassToView());
        if( global.getStudentsInClass() < 1)
        {
            view.setVisibility(View.INVISIBLE);
            remove.setVisibility(View.INVISIBLE);
        }
    }

    public void refreshClassDetails()
    {
        ClassInfo classInfo = global.getClassInfo();
        className.setText(classInfo.getClassName());
        classCode.setText(classInfo.getClassCode());
        classTimes.setText(classInfo.getClassStartTime() + " - " + classInfo.getClassFinishTime());

    }

    public void setStudentDetails(String currentStudent)
    {
        Student student = global.getStudentInfo(currentStudent);
        nameView.setText(student.getStudentName());
        stuNoView.setText(student.getStudentNumber());
        telView.setText(Integer.toString(student.getStudentPhoneNumber()));

    }




    //TEST DATA
    public void testData()
    {
        //Maths - Michael, Daniel, James
        Student student = new Student();
        student.setStudentName("Michael Grundie");
        student.setClassName("Maths");
        student.setStudentNumber("mg123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
        student = new Student();
        student.setStudentName("Daniel O'reilly");
        student.setClassName("Maths");
        student.setStudentNumber("DO123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
        student = new Student();
        student.setStudentName("James Totton");
        student.setClassName("Maths");
        student.setStudentNumber("JT123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);

        //History - Daniel, Sarah, Laura
        student = new Student();
        student.setStudentName("Daniel O'reilly");
        student.setClassName("History");
        student.setStudentNumber("DO123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
        student = new Student();
        student.setStudentName("Sarah Gibson");
        student.setClassName("History");
        student.setStudentNumber("SG123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
        student = new Student();
        student.setStudentName("Laura Dalton");
        student.setClassName("History");
        student.setStudentNumber("LD123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);

        //Science - Michael, James, Conal
        student = new Student();
        student.setStudentName("Michael Grundie");
        student.setClassName("Science");
        student.setStudentNumber("MG123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
        student = new Student();
        student.setStudentName("James Dalton");
        student.setClassName("Science");
        student.setStudentNumber("JD123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
        student = new Student();
        student.setStudentName("Conal Mclaughlin");
        student.setClassName("Science");
        student.setStudentNumber("CM123");
        student.setStudentPhoneNumber(234567);
        global.addStudent(student);
    }
    //TEST DATA
}




