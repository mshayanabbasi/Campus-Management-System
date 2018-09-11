package com.campussystem.CompanyPanel.CompanyMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campussystem.CompanyPanel.CompanyUserMoreDetail;
import com.campussystem.R;
import com.campussystem.StudentAdapter;
import com.campussystem.UserMoreDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/13/2018.
 */

public class CompanyJobFragments extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<JobActivity> jobDetail;
    JobAdapters adapter;
    DatabaseReference ref;
    FirebaseAuth mAuth;
    String uid;


    public CompanyJobFragments() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_company_jobs,container,false);

        FloatingActionButton actionButton=(FloatingActionButton)v.findViewById(R.id.floatingAction);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // getActivity().finish();
                Intent intent=new Intent(getActivity(),AddJob.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        myRecyclerView=(RecyclerView)v.findViewById(R.id.jobRecyclerView);
        adapter=new JobAdapters(getContext(),jobDetail);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);





        return v;



    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobDetail=new ArrayList<>();

         mAuth=FirebaseAuth.getInstance();
        final String uid=mAuth.getCurrentUser().getUid();
        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid).child("jobs");
       // ref= FirebaseDatabase.getInstance().getReference().child("All post").child("Jobs").child(uid);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                JobActivity activity=dataSnapshot.getValue(JobActivity.class);

                jobDetail.add(activity);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
             /*   JobActivity users=dataSnapshot.getValue(JobActivity.class);
                String key=dataSnapshot.getKey();
                for (JobActivity as:jobDetail)
                {
                    if(as.getId().equals(key))
                    {
                        as.setJob(users.getJob());
                        as.setJobType(users.getJobType());
                        as.setJobExperience(users.getJobExperience());
                        as.setJobShift(users.getJobShift());
                        as.setJobSalary(users.getJobSalary());


                        break;
                    }
                }
                adapter.notifyDataSetChanged();*/



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key=dataSnapshot.getKey();
                for(JobActivity aq:jobDetail){
                    if(key.equals(aq.getId()))
                    {
                        jobDetail.remove(aq);
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
