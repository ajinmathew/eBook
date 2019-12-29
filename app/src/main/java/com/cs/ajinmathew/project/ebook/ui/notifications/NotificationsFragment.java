package com.cs.ajinmathew.project.ebook.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cs.ajinmathew.project.ebook.Book;
import com.cs.ajinmathew.project.ebook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {

    EditText edCode,edTitle,edDesc,edAuthor,edPublisher,edType,edPrize;
    Button btnSer,btnDel,btnUpd;
    DatabaseReference reference;
    Book book;
    String title;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of( this ).get( NotificationsViewModel.class );
        View root = inflater.inflate( R.layout.fragment_notifications, container, false );

        btnSer=(Button)root.findViewById( R.id.searchEditAdmin );
        btnDel=(Button)root.findViewById( R.id.deleteEditAdmin );
        btnUpd=(Button)root.findViewById( R.id.updateEditAdmin );
        edCode=(EditText)root.findViewById( R.id.codeEditAdmin );
        edTitle=(EditText)root.findViewById( R.id.titleEditAdmin );
        edDesc=(EditText)root.findViewById( R.id.descriptionEditAdmin );
        edAuthor=(EditText)root.findViewById( R.id.authorEditAdmin );
        edPublisher=(EditText)root.findViewById( R.id.publisherEditAdmin );
        edType=(EditText)root.findViewById( R.id.typeEditAdmin );
        edPrize=(EditText)root.findViewById( R.id.prizeEditAdmin );

        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );

        notificationsViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                btnSer.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        title=edTitle.getText().toString().trim();
                        Query query=reference.orderByChild( "title" ).equalTo( title );
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

                btnUpd.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        title=edTitle.getText().toString();

                        Query query=reference.orderByChild( "title" ).equalTo( title );
                        query.addListenerForSingleValueEvent( new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot datasnap:dataSnapshot.getChildren())
                                {
                                    datasnap.getRef().child( "author" ).setValue( edAuthor.getText().toString() );
                                    datasnap.getRef().child( "code" ).setValue( edCode.getText().toString() );
                                    datasnap.getRef().child( "description" ).setValue( edDesc.getText().toString() );
                                    datasnap.getRef().child( "prize" ).setValue( edPrize.getText().toString() );
                                    datasnap.getRef().child( "publisher" ).setValue( edPublisher.getText().toString() );
                                    datasnap.getRef().child( "type" ).setValue( edType.getText().toString() );


                                }

                                Toast.makeText( getActivity(),"Data Updated",Toast.LENGTH_LONG ).show();
                            }



                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        } );
                    }
                } );
                btnDel.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        title=edTitle.getText().toString();

                        Query query=reference.orderByChild( "title" ).equalTo( title );
                        query.addListenerForSingleValueEvent( new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot datadelete:dataSnapshot.getChildren())
                                {
                                    datadelete.getRef().removeValue();
                                    Toast.makeText( getActivity(),"Data Deleted",Toast.LENGTH_LONG ).show();
                                    edAuthor.setText( "" );
                                    edCode.setText( "" );
                                    edDesc.setText( "" );
                                    edPrize.setText( "" );
                                    edPublisher.setText( "" );
                                    edTitle.setText( "" );
                                    edType.setText( "" );
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        } );
                    }
                } );
            }
        } );
        return root;
    }
}