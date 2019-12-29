package com.cs.ajinmathew.project.ebook.ui.home;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText edCode,edTitle,edDesc,edAuthor,edPublisher,edType,edPrize;
    Button btnAdd;
    DatabaseReference reference;
    Book book;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of( this ).get( HomeViewModel.class );
        View root = inflater.inflate( R.layout.fragment_home, container, false );

        btnAdd=(Button)root.findViewById( R.id.addAddAdmin );
        edCode=(EditText)root.findViewById( R.id.bookcodeAddAdmin );
        edTitle=(EditText)root.findViewById( R.id.titleAddAdmin );
        edDesc=(EditText)root.findViewById( R.id.descriptionAddAdmin );
        edAuthor=(EditText)root.findViewById( R.id.authorAddAdmin );
        edPublisher=(EditText)root.findViewById( R.id.publisherAddAdmin );
        edType=(EditText)root.findViewById( R.id.typeAddAdmin );
        edPrize=(EditText)root.findViewById( R.id.prizeAddAdmin );

        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );

        homeViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                btnAdd.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        book=new Book(  );
                        book.setAuthor( edAuthor.getText().toString().trim() );
                        book.setCode( edCode.getText().toString().trim() );
                        book.setDescription( edDesc.getText().toString().trim() );
                        book.setPrize( edPrize.getText().toString().trim() );
                        book.setPublisher( edPublisher.getText().toString().trim() );
                        book.setTitle( edTitle.getText().toString().trim() );
                        book.setType( edType.getText().toString().trim() );

                        reference.push().setValue( book ).addOnSuccessListener( new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText( getActivity(),"Added",Toast.LENGTH_LONG ).show();
                            }
                        } );
                    }
                } );

            }
        } );
        return root;
    }
}