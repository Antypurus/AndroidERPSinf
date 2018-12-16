package com.example.david.sinfapplication.Activities.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class login_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void execute_login(View view)
    {
        String username = ((EditText)this.findViewById(R.id.username)).getText().toString();
        String password = ((EditText)this.findViewById(R.id.password)).getText().toString();

        if(username.equals("")||password.equals(""))
        {
            Log.d("Primavera Login","No Username or password suplied");
            return;
        }

        WebAPI.loginResult result = null;
        try {
            result = WebAPI.login(username,password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Log.d("Primavera Login","Finished Login Proccess");

        if(result!=null)
        {
            if(result.equals(WebAPI.loginResult.loginSucessfull))
            {
                //go to main menu
                Log.d("Primavera Login","Login Successfull");
            }
            else if(result.equals(WebAPI.loginResult.loginFailedWrongUsernameOrPassword))
            {
                // wrong username password combo error msg
                Log.d("Primavera Login","Wrong Username/password comobo");
            }
            else
            {
                // server error msg
                Log.d("Primavera Login","Login Server Error");
            }
        }
    }

}
