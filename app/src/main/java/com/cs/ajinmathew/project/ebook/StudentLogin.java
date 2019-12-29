package com.cs.ajinmathew.project.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {
EditText edEmail,edPass;
Button btnLoginStudentLogin,btnRegStudentLogin;
String email,password;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_login );
        btnLoginStudentLogin=(Button)findViewById( R.id.loginStudentLogin );
        edEmail=(EditText)findViewById( R.id.emailStudentLogin );
        edPass=(EditText)findViewById( R.id.passwordStudentLogin );

        btnRegStudentLogin=(Button)findViewById( R.id.registerStudentLogin );
        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Student" );

        btnRegStudentLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(),StudentRegisteration.class ) );
            }
        } );

        btnLoginStudentLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=edEmail.getText().toString().trim();
                password=edPass.getText().toString().trim();
                if(email.isEmpty()){
                    edEmail.setError( "required email id" );
                    edEmail.requestFocus();
                }else if(password.isEmpty()){
                    edPass.requestFocus();
                    edPass.setError( "required Password" );
                }else {

                    Query query=reference.orderByChild( "email" ).equalTo( email );
                    query.addListenerForSingleValueEvent( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot datasnap:dataSnapshot.getChildren())
                            {
                                Student student=datasnap.getValue(Student.class);
                                String Password=student.password;
                                if(password.equals( Password )){
                                    Toast.makeText( getApplicationContext(),"Login Successful",Toast.LENGTH_LONG ).show();
                                    //startActivity( new Intent( getApplicationContext(),StudentHome.class ) );
                                }else {
                                    Toast.makeText( getApplicationContext(),"Login Error ",Toast.LENGTH_LONG ).show();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );

                }

            }
        } );
    }
}
