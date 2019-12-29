package com.cs.ajinmathew.project.ebook.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cs.ajinmathew.project.ebook.Book;
import com.cs.ajinmathew.project.ebook.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    EditText edCode,edTitle,edDesc,edAuthor,edPublisher,edType,edPrize;
    Button btnSer;
    DatabaseReference reference;
    Book book;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of( this ).get( DashboardViewModel.class );
        View root = inflater.inflate( R.layout.fragment_dashboard, container, false );

        btnSer=(Button)root.findViewById( R.id.searchSearchAdmin );
        edCode=(EditText)root.findViewById( R.id.codeSearchAdmin );
        edTitle=(EditText)root.findViewById( R.id.titleSearchAdmin );
        edDesc=(EditText)root.findViewById( R.id.descriptionSearchAdmin );
        edAuthor=(EditText)root.findViewById( R.id.authorSearchAdmin );
        edPublisher=(EditText)root.findViewById( R.id.publisherSearchAdmin );
        edType=(EditText)root.findViewById( R.id.typeSearchAdmin );
        edPrize=(EditText)root.findViewById( R.id.prizeSearchAdmin );

        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );

        dashboardViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

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
        } );
        return root;
    }
}