package com.cs.ajinmathew.project.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegisteration extends AppCompatActivity {
EditText edName,edAdmno,edPalce,edParent,edMobile,edEmail,edPassword,edCnfPassword;
Button btnRegister,btnLogin;
Spinner spinner;
Student student;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_registeration );

        spinner=(Spinner)findViewById( R.id.spinnerReg );

        edName=(EditText)findViewById( R.id.nameStudentReg );
        edAdmno=(EditText)findViewById( R.id.admnoStudentReg );
        edPalce=(EditText)findViewById( R.id.placeStudentReg );
        edParent=(EditText)findViewById( R.id.parentStudentReg );
        edMobile=(EditText)findViewById( R.id.mobileStudentReg );
        edEmail=(EditText)findViewById( R.id.emailStudentReg );
        edPassword=(EditText)findViewById( R.id.passwordStudentReg );
        edCnfPassword=(EditText)findViewById( R.id.cnfpasswordStudentReg );

        btnLogin=(Button)findViewById( R.id.loginStudentReg );
        btnRegister=(Button)findViewById( R.id.registerStudentReg );

        databaseReference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Student" );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(),StudentLogin.class ) );
            }
        } );
        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student=new Student(  );
                String password=edPassword.getText().toString().trim();
                String cnfpassword=edCnfPassword.getText().toString().trim();
                if(password.equals( cnfpassword )){
                    student.setAdmission_no( edAdmno.getText().toString().trim() );
                    student.setDistrict( spinner.getSelectedItem().toString().trim() );
                    student.setEmail( edEmail.getText().toString().trim() );
                    student.setMobile( edMobile.getText().toString().trim() );
                    student.setName( edName.getText().toString().trim() );
                    student.setParent_name( edParent.getText().toString().trim() );
                    student.setPassword( password );
                    student.setPlace( edPalce.getText().toString().trim() );

                    databaseReference.push().setValue( student ).addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText( getApplicationContext(),"Account created",Toast.LENGTH_LONG ).show();
                        }
                    } );
                }else {
                    Toast.makeText( getApplicationContext(),"Password are incorrect",Toast.LENGTH_LONG ).show();
                }

            }
        } );

    }
}
