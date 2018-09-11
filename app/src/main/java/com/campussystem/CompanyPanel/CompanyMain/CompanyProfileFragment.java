package com.campussystem.CompanyPanel.CompanyMain;

import android.content.Intent;
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
 * Created by User on 3/13/2018.
 */

public class CompanyProfileFragment extends Fragment {
    TextView cName,cType,cAddress;
    DatabaseReference ref;
    FirebaseAuth mAuth;
    String names,types,addresss;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_company_profile_tab,container,false);

        cName=(TextView)v.findViewById(R.id.companyName);
        cType=(TextView)v.findViewById(R.id.companyType);
        cAddress=(TextView)v.findViewById(R.id.address);

        cName.setText(names);
        cType.setText(types);
        cAddress.setText(addresss);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        String uid=mAuth.getCurrentUser().getUid();
        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

           names=valueOf(dataSnapshot.child("name").getValue());
                 types=valueOf(dataSnapshot.child("companyType").getValue());
               addresss=valueOf(dataSnapshot.child("address").getValue());

                cName.setText(names);
                cType.setText(types);
                cAddress.setText(addresss);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
