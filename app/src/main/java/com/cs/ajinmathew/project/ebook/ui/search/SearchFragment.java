package com.cs.ajinmathew.project.ebook.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cs.ajinmathew.project.ebook.Book;
import com.cs.ajinmathew.project.ebook.R;
import com.cs.ajinmathew.project.ebook.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    EditText edCode,edTitle,edDesc,edAuthor,edPublisher,edType,edPrize;
    Button btnSer;
    DatabaseReference reference;
    Book book;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of( this ).get( SearchViewModel.class );
        View root = inflater.inflate( R.layout.fragment_search, container, false );

        btnSer=(Button)root.findViewById( R.id.searchSearchStudent );
        edCode=(EditText)root.findViewById( R.id.codeSearchStudent );
        edTitle=(EditText)root.findViewById( R.id.titleSearchStudent );
        edDesc=(EditText)root.findViewById( R.id.descriptionSearchStudent );
        edAuthor=(EditText)root.findViewById( R.id.authorSearchStudent );
        edPublisher=(EditText)root.findViewById( R.id.publisherSearchStudent );
        edType=(EditText)root.findViewById( R.id.typeSearchStudent );
        edPrize=(EditText)root.findViewById( R.id.prizeSearchStudent );

        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );


        searchViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                btnSer.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title=edTitle.getText().toString().trim();

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

            }
        } );
        return root;
    }
}