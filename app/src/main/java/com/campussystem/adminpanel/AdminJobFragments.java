package com.campussystem.adminpanel;

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

import com.campussystem.CompanyPanel.CompanyMain.AddJob;
import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.CompanyPanel.CompanyMain.JobAdapter;
import com.campussystem.CompanyPanel.CompanyMain.JobAdapters;
import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.campussystem.adminpanel.adminadapters.AdminJobAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/13/2018.
 */

public class AdminJobFragments extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<JobActivity> jobDetail;
    AdminJobAdapter adapter;
    DatabaseReference ref;
    FirebaseAuth mAuth;
    String uid;


    public AdminJobFragments() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_admin_company_jobs,container,false);




        myRecyclerView=(RecyclerView)v.findViewById(R.id.jobRecyclerView);
        adapter=new AdminJobAdapter( getContext(),jobDetail);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);





        return v;



    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobDetail=new ArrayList<>();
         mAuth=FirebaseAuth.getInstance();
//        String uid=mAuth.getCurrentUser().getUid();
        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("All jobs");
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
                    if(key.equals(aq.getjId()))
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

        /*ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseAuth nAuth=FirebaseAuth.getInstance();
                uid=nAuth.getCurrentUser().getUid();
                List<ApplyJobActivity>activities=new ArrayList<>();
                ApplyJobActivity act=dataSnapshot.getValue(ApplyJobActivity.class);
                if(act.getUid().equals(uid)&&act.getUid().equals(jobId))
                {
                    activities.add(act);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }
}
