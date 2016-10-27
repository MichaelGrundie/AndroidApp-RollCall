package co.example.michaekgrundie.rollcallnrc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LecturerLogin extends AppCompatActivity {

    Button login;
    EditText username, password;
    TextView lecturerLogin, loginError;
    ImageView nrcLogo;
    boolean loginPass = false;
    int loginAttempts;
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);
        global = (Global)getApplicationContext();

        username = (EditText) (findViewById(R.id.txtEditEmail));
        password = (EditText) (findViewById(R.id.txtEditPassword));
        login = (Button) (findViewById(R.id.btnLogin));
        lecturerLogin = (TextView) (findViewById(R.id.txtLecturerLogin));
        loginError = (TextView) (findViewById(R.id.txtiewLoginError));
        nrcLogo = (ImageView) (findViewById(R.id.imageViewNRCLogo));
        loginAttempts = 0;
        loginPass = loginCheck();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAttempts++;

                if (loginPass == true)
                {
                    Intent openLecturerOptions = new Intent (LecturerLogin.this, LecturerOptions.class);
                    startActivity(openLecturerOptions);
                }else
                {
                    if (loginAttempts >= 3)
                    {
                        nrcLogo.setVisibility(View.INVISIBLE);
                        lecturerLogin.setVisibility(View.INVISIBLE);
                        loginError.setVisibility(View.VISIBLE);
                    }

                    Toast.makeText(LecturerLogin.this, "Username or password not recognised, please try again.)", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lecturer_login, menu);
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

    private boolean loginCheck()
    {
        String uName = username.getText().toString();
        String pWord = password.getText().toString();
        return true; //for testing
    }
}

