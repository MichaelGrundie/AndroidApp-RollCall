package co.example.michaekgrundie.rollcallnrc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

public class Attendance extends AppCompatActivity {

    ImageButton tick, cross;
    TextView stuName;
    int time, loop;
    Global global;
    Student[] studentsInCurrentClass;
    boolean classFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        global = (Global)getApplicationContext();

        tick = (ImageButton)findViewById(R.id.imgBtnTick);
        cross = (ImageButton)findViewById(R.id.imgBtnCross);
        stuName = (TextView)findViewById(R.id.txtStuName);
        loop = -1;

        getTime();
        findClass(time);

        if(!classFound)
        {
            tick.setVisibility(View.INVISIBLE);
            cross.setVisibility(View.INVISIBLE);
            stuName.setText("No Class \nSheduled");
        }else
        {
            refreshStudentDetails(false);
        }




        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshStudentDetails(true);

            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshStudentDetails(false);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void getTime()
    {
        int hours = new Time(System.currentTimeMillis()).getHours();
        int mins = new Time(System.currentTimeMillis()).getMinutes();

        if (mins<1) {
            String Time = Integer.toString(hours) + "00";
            time = Integer.parseInt(Time);
        }else if (mins<10)
        {
            String Time = Integer.toString(hours) + "0" + Integer.toString(mins);
            time = Integer.parseInt(Time);
        }else
        {
            String Time = Integer.toString(hours) + Integer.toString(mins);
            time = Integer.parseInt(Time);
        }
    }

    public void findClass(int time)
    {
        ClassInfo[] classes = global.getClasses();
        ClassInfo currentClass = new ClassInfo();
        for (int i = 0; i<classes.length; i++)
        {
            if (classes[i].getClassStartTime() <= time && classes[i].getClassFinishTime() >= time)
            {
                classFound =true;
                currentClass = classes[i];
            }
        }

        studentsInCurrentClass = global.getStudentsOfCurrentCLass(currentClass.getClassName());
    }

    public void refreshStudentDetails(boolean delete)
    {

        if (studentsInCurrentClass.length <1)
        {
            stuName.setText("Roll Complete!");
            tick.setVisibility(View.INVISIBLE);
            cross.setVisibility(View.INVISIBLE);
        }else
        {
            if (delete)
            {
                deleteCurrentStudent();
            }
            if (studentsInCurrentClass.length <1) {
                stuName.setText("Roll Complete!");
                tick.setVisibility(View.INVISIBLE);
                cross.setVisibility(View.INVISIBLE);
            } else {
                loop++;
                if (loop >= studentsInCurrentClass.length) {
                    loop = 0;
                }
                stuName.setText(studentsInCurrentClass[loop].getStudentName());
            }
        }

    }

    public void deleteCurrentStudent()
    {
        Student[] newStudents = new Student[studentsInCurrentClass.length -1];
        int newStudentsIndex = 0;

        for (int i= 0; i< studentsInCurrentClass.length; i++) {
            if (studentsInCurrentClass[i].getStudentName()
                    .equalsIgnoreCase(studentsInCurrentClass[loop].getStudentName())) {

            } else {
                newStudents[newStudentsIndex] = studentsInCurrentClass[i];
                newStudentsIndex++;
            }
        }
        studentsInCurrentClass = newStudents;
    }
}
