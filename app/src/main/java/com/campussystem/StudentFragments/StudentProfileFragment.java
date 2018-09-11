package com.campussystem.StudentFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.String.valueOf;

/**
 * Created by User on 3/14/2018.
 */

public class StudentProfileFragment extends Fragment {
    TextView sName,sQualification,sUniversity,sEmail;
    DatabaseReference ref;
    FirebaseAuth nAuth;
    String names,qualifications,university,email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.activity_student_profile,container,false);
        sName=(TextView)v.findViewById(R.id.nameStudent);
        sQualification=(TextView)v.findViewById(R.id.qualificationStudent);
        sUniversity=(TextView)v.findViewById(R.id.universityStudent);
        sEmail=(TextView)v.findViewById(R.id.emailStudent);

           sName.setText(names);
        sQualification.setText(qualifications);
        sUniversity.setText(university);
        sEmail.setText(email);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nAuth=FirebaseAuth.getInstance();
        String uid=nAuth.getCurrentUser().getUid();
        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names=valueOf(dataSnapshot.child("userName").getValue());
                qualifications=valueOf(dataSnapshot.child("qualification").getValue());
                university=valueOf(dataSnapshot.child("university").getValue());
                email=valueOf(dataSnapshot.child("email").getValue());
                sName.setText(names);
                sQualification.setText(qualifications);
                sUniversity.setText(university);
                sEmail.setText(email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
