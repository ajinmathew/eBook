package com.cs.ajinmathew.project.ebook.ui.viewbook;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.ajinmathew.project.ebook.Book;
import com.cs.ajinmathew.project.ebook.CustomAdapter;
import com.cs.ajinmathew.project.ebook.R;
import com.cs.ajinmathew.project.ebook.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBookFragment extends Fragment {

    private ViewBookViewModel viewBookViewModel;

    DatabaseReference reference;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Book> book_list;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewBookViewModel = ViewModelProviders.of( this ).get( ViewBookViewModel.class );
        View root = inflater.inflate( R.layout.fragment_view, container, false );


        reference= FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );
        recyclerView=(RecyclerView)root.findViewById( R.id.recycler );
        recyclerView.setHasFixedSize( true );
        book_list=new ArrayList<>(  );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(),1 ) );


        viewBookViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                reference=FirebaseDatabase.getInstance().getReference().child( "Data" ).child( "Book" );
                reference.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataEmployee:dataSnapshot.getChildren())
                        {
                            Book book=dataEmployee.getValue(Book.class);
                            book_list.add( book );
                        }
                        adapter=new CustomAdapter( book_list,getActivity() );
                        recyclerView.setAdapter( adapter );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );

            }
        } );
        return root;
    }
}