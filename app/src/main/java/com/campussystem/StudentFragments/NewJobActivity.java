package com.campussystem.StudentFragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.CompanyPanel.CompanyMain.JobAdapter;
import com.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewJobActivity extends AppCompatActivity {
    View v;
    private RecyclerView myRecyclerViews;

    private List<JobActivity> jobDetail;
    JobAdapter adapter;
    DatabaseReference ref;
    FirebaseAuth mAuth;
    String uid;
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);
        mAuth=FirebaseAuth.getInstance();
       uid=mAuth.getCurrentUser().getUid();
        myRecyclerViews=(RecyclerView)findViewById(R.id.jobsRecyclerViews);
        jobDetail=new ArrayList<>();

        Intent intent = getIntent();
        final String id = intent.getStringExtra("Id");
        adapter=new JobAdapter(NewJobActivity.this,jobDetail);
        myRecyclerViews.setAdapter(adapter);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        myRecyclerViews.setLayoutManager(manager);







try {


    ref = FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id).child("jobs");
    ref.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            JobActivity activity = dataSnapshot.getValue(JobActivity.class);
            jobDetail.add(activity);
            adapter.notifyDataSetChanged();


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for (JobActivity aq : jobDetail) {
                if (key.equals(aq.getId())) {
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
catch (Exception e)
{
    e.printStackTrace();
}


    }
}
