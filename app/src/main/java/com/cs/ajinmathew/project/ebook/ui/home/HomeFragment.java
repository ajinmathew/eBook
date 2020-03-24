package com.cs.ajinmathew.project.ebook.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText edCode,edTitle,edDesc,edAuthor,edPublisher,edType,edPrize;
    Button btnAdd,btnUploadImage;
    DatabaseReference reference;
    Book book;

    ProgressBar progressBar;

    String Imagepath;

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

        btnUploadImage=(Button)root.findViewById( R.id.uploadimage );

        progressBar=(ProgressBar)root.findViewById( R.id.progress );

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
                        book.setImagepath( Imagepath );

                        Toast.makeText( getActivity(),Imagepath,Toast.LENGTH_LONG ).show();

                        reference.push().setValue( book ).addOnSuccessListener( new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText( getActivity(),"Added",Toast.LENGTH_LONG ).show();
                            }
                        } );
                    }
                } );
                btnUploadImage.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility( View.VISIBLE );
                        Intent intent=new Intent( Intent.ACTION_GET_CONTENT );
                        //setting which type files to select...
                        intent.setType( "image/*" );
                        startActivityForResult( intent,1 );

                    }
                } );

            }
        } );
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if(requestCode==1){
            if(resultCode==RESULT_OK){

                Uri fileUri=data.getData();
                //setting references of storage...
                //child name is the folder name...
                StorageReference folder= FirebaseStorage.getInstance().getReference().child( "StudentPics" );
                //create a timestamp for rename...
                String timestamp=String.valueOf( System.currentTimeMillis() );

                final StorageReference filename=folder.child( timestamp+fileUri.getLastPathSegment() );

                filename.putFile( fileUri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //download the uploaded files url from storage...
                        filename.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //uri contain the path of the uploaded data...
                                Imagepath=String.valueOf( uri );
                                Toast.makeText( getContext(),"Successfully Uploaded",Toast.LENGTH_LONG ).show();
                                progressBar.setVisibility( View.INVISIBLE );

                            }
                        } );
                    }
                } );
            }
        }
    }
}0