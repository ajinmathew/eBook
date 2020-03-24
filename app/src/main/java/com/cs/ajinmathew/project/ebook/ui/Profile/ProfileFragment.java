package com.cs.ajinmathew.project.ebook.ui.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cs.ajinmathew.project.ebook.Book;
import com.cs.ajinmathew.project.ebook.ChangePassword;
import com.cs.ajinmathew.project.ebook.R;
import com.cs.ajinmathew.project.ebook.StudentLogin;
import com.cs.ajinmathew.project.ebook.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    SharedPreferences preferences;
    String name,email,phone,place,parent,admno,dist;
    Button btnLog,btnChngPass;

    TextView txtName,txtPlace,txtPhone,txtEmail,txtAdmno,txtDist,txtParent;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of( this ).get( ProfileViewModel.class );
        View root = inflater.inflate( R.layout.fragment_profile, container, false );

        txtEmail=(TextView)root.findViewById( R.id.emailStudentProfile );
        txtName=(TextView)root.findViewById( R.id.nameStudentProfile );
        txtPhone=(TextView)root.findViewById( R.id.mobileStudentProfile );
        txtPlace=(TextView)root.findViewById( R.id.placeStudentProfile );
        txtAdmno=(TextView)root.findViewById( R.id.admnoStudentProfile );
        txtDist=(TextView)root.findViewById( R.id.districtStudentProfile );
        txtParent=(TextView)root.findViewById( R.id.parentStudentProfile );

        btnLog=(Button)root.findViewById( R.id.logout );
        btnChngPass=(Button)root.findViewById( R.id.changePassword );

        SharedPreferences preferences=this.getActivity().getSharedPreferences( "LoginStudent",MODE_PRIVATE );
        name=preferences.getString( "UserName",null );
        email=preferences.getString( "UserEmail",null );
        phone=preferences.getString( "UserPhone",null );
        place=preferences.getString( "UserPlace",null );

        dist=preferences.getString( "UserDistrict",null );
        parent=preferences.getString( "UserParent",null );
        admno=preferences.getString( "UserAdmission",null );



        final SharedPreferences.Editor preferencesdelete=this.getActivity().getSharedPreferences( "LoginStudent",MODE_PRIVATE ).edit();

        profileViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


                txtName.setText( name );
                txtEmail.setText( email );
                txtPhone.setText( phone );
                txtPlace.setText( place );
                txtAdmno.setText( admno );
                txtDist.setText( dist );
                txtParent.setText( parent );


                btnLog.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferencesdelete.clear();
                        preferencesdelete.commit();
                        startActivity( new Intent( getContext(), StudentLogin.class ) );
                    }
                } );

                btnChngPass.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity( new Intent( getContext(), ChangePassword.class ) );
                    }
                } );

            }
        } );
        return root;
    }
}