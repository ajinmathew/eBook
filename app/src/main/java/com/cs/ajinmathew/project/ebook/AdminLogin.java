package com.cs.ajinmathew.project.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
Button btnLoginAdmin;
EditText edUserName,edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_login );

        btnLoginAdmin=(Button)findViewById( R.id.loginAdminLogin );
        edUserName=(EditText)findViewById( R.id.usernameAdminLogin );
        edPassword=(EditText)findViewById( R.id.passwordAdminLogin );
        btnLoginAdmin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName=edUserName.getText().toString().trim();
                String Password=edPassword.getText().toString().trim();
                if(UserName.isEmpty()) {
                    edUserName.setError( "UserName Required" );
                    edUserName.requestFocus();
                }else if(Password.isEmpty()){
                    edPassword.setError( "Password Required" );
                    edPassword.requestFocus();
                }
                else
                {
                    if((UserName.equals( "admin" )&&(Password.equals( "12345" ))))
                    {
                        startActivity( new Intent( getApplicationContext(),AdminHome.class ) );
                    }else {
                        Toast.makeText( getApplicationContext(),"Incorrect UserName or Password",Toast.LENGTH_LONG ).show();
                    }
                }

            }
        } );

    }
}
