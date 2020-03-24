package com.cs.ajinmathew.project.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ChangePassword extends AppCompatActivity {
Button btnChange;
DatabaseReference reference;
EditText edPasswordOld,edPasswordNew,edPasswordNewCnf;
Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_password );

        btnChange=(Button)findViewById( R.id.change );

        edPasswordOld=(EditText)findViewById( R.id.oldPasswordChange );
        edPasswordNew=(EditText)findViewById( R.id.newPasswordChange );
        edPasswordNewCnf=(EditText)findViewById( R.id.cnfnewPasswordChange );

        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Student" );

        btnChange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                student=new Student(  );

                final String passwordOld=edPasswordOld.getText().toString().trim();
                String newpassword=edPasswordNew.getText().toString().trim();
                String cnfnewpassword=edPasswordNewCnf.getText().toString().trim();

                if(newpassword.equals( cnfnewpassword )){

                    SharedPreferences preferences=getSharedPreferences( "LoginStudent",MODE_PRIVATE );
                    String emailShared=preferences.getString( "UserEmail",null );

                    Query query=reference.orderByChild( "email" ).equalTo( emailShared );
                    query.addListenerForSingleValueEvent( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot datasnap:dataSnapshot.getChildren())
                            {
                                student=datasnap.getValue(Student.class);
                                if(passwordOld.equals( student.password )){
                                    //Toast.makeText( getApplicationContext(),"Same Password",Toast.LENGTH_LONG ).show();
                                    datasnap.getRef().child( "password" ).setValue( edPasswordNew.getText().toString() );
                                    Toast.makeText( getApplicationContext(),"Password Changed Successfully",Toast.LENGTH_LONG ).show();

                                }else {
                                    Toast.makeText( getApplicationContext(),"Wrong Old Password",Toast.LENGTH_LONG ).show();
                                }

                            }

                            // Toast.makeText( getApplicationContext(),student.email,Toast.LENGTH_LONG ).show();
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );

                }else {
                    Toast.makeText( getApplicationContext(),"Passwords are Diffrent",Toast.LENGTH_LONG ).show();
                }

            }
        } );
    }
}
