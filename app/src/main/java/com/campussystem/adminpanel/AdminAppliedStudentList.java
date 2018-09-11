package com.campussystem.adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.campussystem.CompanyPanel.CompanyMain.AppliedAdapters;
import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.campussystem.adminpanel.adminadapters.AdminAppliedAdapters;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminAppliedStudentList extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<ApplyJobActivity> studentDetail;
    AdminAppliedAdapters adapters;
    FirebaseAuth nAuth;
    DatabaseReference dr;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_applied_student_list);
        recyclerView=(RecyclerView)findViewById(R.id.appliedRecyclerViews);
        studentDetail=new ArrayList<>();
        Intent intent = getIntent();
        final String compId = intent.getStringExtra("CId");
        final String jId=intent.getStringExtra("JId");
        adapters=new AdminAppliedAdapters(AdminAppliedStudentList.this,studentDetail);
        recyclerView.setAdapter(adapters);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        dr= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(compId).child("jobs").child(jId).child("Applied Students");;
        // dr= FirebaseDatabase.getInstance().getReference().child("All post").child("Jobs").child(id).child(cId).child("Applied Students");
        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ApplyJobActivity userMoreDetails=dataSnapshot.getValue(ApplyJobActivity.class);
                    studentDetail.add(userMoreDetails);
                    adapters.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

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
