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

import com.cs.ajinmathew.project.ebook.Book;
import com.cs.ajinmathew.project.ebook.R;
import com.cs.ajinmathew.project.ebook.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewBookFragment extends Fragment {

    private ViewBookViewModel viewBookViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewBookViewModel = ViewModelProviders.of( this ).get( ViewBookViewModel.class );
        View root = inflater.inflate( R.layout.fragment_view, container, false );





        viewBookViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

               Toast.makeText( getActivity(),"View all",Toast.LENGTH_LONG ).show();
            }
        } );
        return root;
    }
}