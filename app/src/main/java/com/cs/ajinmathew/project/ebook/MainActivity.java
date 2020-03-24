package com.cs.ajinmathew.project.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnStudentMain,btnAdminMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        SharedPreferences preferences=getSharedPreferences( "LoginStudent",MODE_PRIVATE );
        String name=preferences.getString( "UserName",null );
        if(name!=null)
        {
            startActivity( new Intent( getApplicationContext(), HomeStd.class ) );
        }

        btnStudentMain=(Button)findViewById( R.id.studentMain );
        btnAdminMain=(Button)findViewById( R.id.adminMain );
        btnAdminMain.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(),AdminLogin.class ) );
            }
        } );
        btnStudentMain.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(),StudentLogin.class ) );
            }
        } );

    }
}
