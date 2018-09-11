package com.campussystem.StudentFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.CompanyPanel.CompanyMain.JobAdapter;
import com.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/14/2018.
 */

public class StudentCompanyList extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<JobActivity> jobDetail;
    JobAdapter adapter;
    DatabaseReference ref;
    FirebaseAuth mAuth;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_students_comapny_list,container,false);


        return v;
    }







}
