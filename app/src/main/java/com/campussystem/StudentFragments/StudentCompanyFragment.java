package com.campussystem.StudentFragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.CompanyPanel.CompanyUserMoreDetail;
import com.campussystem.R;
import com.campussystem.adminpanel.adminadapters.AdminCompanyAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by User on 3/15/2018.
 */

public class StudentCompanyFragment extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private ArrayList<CompanyUserMoreDetail>jobDetail;
    StudentCompanyAdapter adapter;
    DatabaseReference ref;

    public StudentCompanyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.activity_admin_company_list,container,false);


        myRecyclerView=(RecyclerView)v.findViewById(R.id.recyclerViewCompany);
        adapter=new StudentCompanyAdapter(getContext(),jobDetail);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobDetail=new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyUserMoreDetail companyUserMoreDetail=dataSnapshot.getValue(CompanyUserMoreDetail.class);
                if(companyUserMoreDetail.getGenere().equals("Company"))
                {
                jobDetail.add(companyUserMoreDetail);
                adapter.notifyDataSetChanged();}
                if(dataSnapshot.equals(null))
                {
                    Toast.makeText(getContext(), ""+dataSnapshot, Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               /* CompanyUserMoreDetail users=dataSnapshot.getValue(CompanyUserMoreDetail.class);
                String key=dataSnapshot.getKey();
                for (CompanyUserMoreDetail as:jobDetail)
                {
                    if(as.getId().equals(key))
                    {
                        as.setName(users.getName());
                        as.setCompanyType(users.getCompanyType());
                        as.setAddress(users.getAddress());
                        as.setContacts(users.getContacts());
                        break;
                    }
                }
                adapter.notifyDataSetChanged();*/



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                final String key=dataSnapshot.getKey();
                for(CompanyUserMoreDetail aq:jobDetail)
                {if(key.equals(aq.getId()))
                {  jobDetail.remove(aq);
                    adapter.notifyDataSetChanged();
                    break;}
                }
               /* DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("All post").child("All jobs");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JobActivity activity=dataSnapshot.getValue(JobActivity.class);
                        if(activity.getId().equals(key))
                        {
                            String idd=activity.getjId();
                            DatabaseReference ref4=FirebaseDatabase.getInstance().getReference().child("All post").child("All jobs").child(idd);
                            ref4.removeValue();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Tag","error is"+databaseError);

            }
        });





    }


}
