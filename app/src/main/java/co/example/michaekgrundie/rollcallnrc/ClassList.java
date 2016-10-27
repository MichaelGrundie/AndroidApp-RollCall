package co.example.michaekgrundie.rollcallnrc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClassList extends AppCompatActivity {

    Spinner classes, dayPicker;
    Button addClass, delClass, submit, startTime, endTime, viewClass,confirmTime;
    EditText className, classCode, noOfStudents;
    TimePicker timepicker;
    final String[] weekDays = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
    Global global;
    boolean setStartTime,submitAddClass = true;
    int newClassStartTime, newClassEndTime;
    ArrayAdapter<String> adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        global = (Global)getApplicationContext();

        addClass = (Button) findViewById(R.id.btnAddClass);
        confirmTime = (Button) findViewById(R.id.btnConfirmTime);
        viewClass = (Button) findViewById(R.id.btnViewClass);
        delClass = (Button) findViewById(R.id.btnDeleteClass);
        submit = (Button) findViewById(R.id.btnSubmitClass);
        startTime = (Button) findViewById(R.id.btnStartClass);
        endTime = (Button) findViewById(R.id.btnEndClass);
        classes = (Spinner) (findViewById(R.id.spinnerClasses));
        dayPicker = (Spinner) (findViewById(R.id.spinnerDayPick));
        className = (EditText) (findViewById(R.id.editTextNewClassName));
        classCode = (EditText) (findViewById(R.id.editTextNewClassCode));
        noOfStudents = (EditText) (findViewById(R.id.editTextNewClassNoOfStudents));
        timepicker = (TimePicker) (findViewById(R.id.timePicker1));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, weekDays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayPicker.setAdapter(adapter);


        //FOR TESTING
        if(!global.runonce1) {
            testData();
            global.runonce1 = true;
        }
        //FOR TESTING

        refreshButtons();
        refreshClassSpinner();

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAddClass = true;
                addClass.setVisibility(View.INVISIBLE);
                delClass.setVisibility(View.INVISIBLE);
                classes.setVisibility(View.INVISIBLE);
                viewClass.setVisibility(View.INVISIBLE);
                className.setVisibility(View.VISIBLE);
                classCode.setVisibility(View.VISIBLE);
                noOfStudents.setVisibility(View.VISIBLE);
                startTime.setVisibility(View.VISIBLE);
                endTime.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                dayPicker.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (submitAddClass) {
                    getNewClassData();
                }else
                {
                    global.removeClass(classes.getSelectedItem().toString());
                }
                addClass.setVisibility(View.VISIBLE);
                delClass.setVisibility(View.VISIBLE);
                viewClass.setVisibility(View.VISIBLE);
                classes.setVisibility(View.VISIBLE);
                className.setVisibility(View.INVISIBLE);
                classCode.setVisibility(View.INVISIBLE);
                noOfStudents.setVisibility(View.INVISIBLE);
                startTime.setVisibility(View.INVISIBLE);
                endTime.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                dayPicker.setVisibility(View.INVISIBLE);
                refreshClassSpinner();
                refreshButtons();

            }
        });

        delClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAddClass = false;
                addClass.setVisibility(View.INVISIBLE);
                delClass.setVisibility(View.INVISIBLE);
                viewClass.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);

            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                className.setVisibility(View.INVISIBLE);
                classCode.setVisibility(View.INVISIBLE);
                noOfStudents.setVisibility(View.INVISIBLE);
                startTime.setVisibility(View.INVISIBLE);
                endTime.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                timepicker.setVisibility(View.VISIBLE);
                confirmTime.setVisibility(View.VISIBLE);
                setStartTime = true;
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                className.setVisibility(View.INVISIBLE);
                classCode.setVisibility(View.INVISIBLE);
                noOfStudents.setVisibility(View.INVISIBLE);
                startTime.setVisibility(View.INVISIBLE);
                endTime.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                timepicker.setVisibility(View.VISIBLE);
                confirmTime.setVisibility(View.VISIBLE);
                setStartTime = false;


            }
        });

        confirmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTime();
                className.setVisibility(View.VISIBLE);
                classCode.setVisibility(View.VISIBLE);
                noOfStudents.setVisibility(View.VISIBLE);
                startTime.setVisibility(View.VISIBLE);
                endTime.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                timepicker.setVisibility(View.INVISIBLE);
                confirmTime.setVisibility(View.INVISIBLE);
            }
        });

        viewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                global.setClassToView(classes.getSelectedItem().toString());
                Intent openStudents = new Intent(ClassList.this, ClassStudents.class);
                startActivity(openStudents);
            }
        });

    }

    public String[] classNames(ClassInfo[] classinfo)
    {
        String[] classNames = new String[classinfo.length];

        for (int i =0; i<classinfo.length; i++)
        {
            classNames[i] = classinfo[i].getClassName();
        }
        return classNames;

    }

    public void getNewClassData()
    {
        ClassInfo newCLass = new ClassInfo();
        newCLass.setClassDay(dayPicker.getSelectedItem().toString());
        newCLass.setClassName(className.getText().toString());
        newCLass.setClassCode(classCode.getText().toString());
        newCLass.setNoOfStudents(Integer.parseInt(noOfStudents.getText().toString()));
        newCLass.setClassStartTime(newClassStartTime);
        newCLass.setClassFinishTime(newClassEndTime);
        global.addClass(newCLass);

    }

    public void getTime()
    {
        String hour = Integer.toString(timepicker.getCurrentHour());
        String min = Integer.toString(timepicker.getCurrentMinute());
        String time = hour + min;
        int selectedTime = Integer.parseInt(time);

        if (setStartTime)
        {
            newClassStartTime = selectedTime;

        }else
        {
            newClassEndTime = selectedTime;
        }
    }

    public void refreshClassSpinner()
    {
        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, classNames(global.getClasses()));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classes.setAdapter(adapter2);
    }

    public void refreshButtons()
    {
        if( global.getClassesAdded() < 1)
        {
            viewClass.setVisibility(View.INVISIBLE);
            delClass.setVisibility(View.INVISIBLE);
        }
    }


    //FOR TESTING
    public void testData()
    {
        ClassInfo classs = new ClassInfo();
        classs.setClassName("Maths");
        classs.setClassCode("Mat123");
        classs.setClassDay("Monday");
        classs.setClassStartTime(250);
        classs.setClassFinishTime(1730);
        classs.setNoOfStudents(15);
        global.addClass(classs);
        classs = new ClassInfo();
        classs.setClassName("History");
        classs.setClassCode("His123");
        classs.setClassDay("Tuesday");
        classs.setClassStartTime(1030);
        classs.setClassFinishTime(1245);
        classs.setNoOfStudents(11);
        global.addClass(classs);
        classs = new ClassInfo();
        classs.setClassName("Science");
        classs.setClassCode("Sci123");
        classs.setClassDay("Wednesday");
        classs.setClassStartTime(1430);
        classs.setClassFinishTime(1530);
        classs.setNoOfStudents(30);
        global.addClass(classs);
    }
    //FOR TESTING


}
