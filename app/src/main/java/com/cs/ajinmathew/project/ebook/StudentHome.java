package com.cs.ajinmathew.project.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentHome extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_home );

        final EditText edCode,edTitle,edDesc,edAuthor,edPublisher,edType,edPrize;
        Button btnSer;
        final DatabaseReference reference;
        Book book;


        btnSer=(Button)findViewById( R.id.searchSearchStd );
        edCode=(EditText)findViewById( R.id.codeSearchStd );
        edTitle=(EditText)findViewById( R.id.titleSearchStd );
        edDesc=(EditText)findViewById( R.id.descriptionSearchStd );
        edAuthor=(EditText)findViewById( R.id.authorSearchStd );
        edPublisher=(EditText)findViewById( R.id.publisherSearchStd );
        edType=(EditText)findViewById( R.id.typeSearchStd );
        edPrize=(EditText)findViewById( R.id.prizeSearchStd );

        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );

        btnSer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tirle=edTitle.getText().toString().trim();

                Query query=reference.orderByChild( "title" ).equalTo( tirle );
                query.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnap:dataSnapshot.getChildren())
                        {
                            Book book=dataSnap.getValue(Book.class);
                            edAuthor.setText( book.author );
                            edCode.setText( book.code );
                            edDesc.setText( book.description );
                            edPrize.setText( book.prize );
                            edPublisher.setText( book.publisher );
                            edType.setText( book.type );


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );

    }
}
