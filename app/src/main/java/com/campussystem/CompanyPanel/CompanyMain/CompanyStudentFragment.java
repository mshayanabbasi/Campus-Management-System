package com.campussystem.CompanyPanel.CompanyMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campussystem.R;
import com.campussystem.StudentAdapter;
import com.campussystem.UserMoreDetails;
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

public class CompanyStudentFragment extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<UserMoreDetails> studentDetail;
    StudentAdapter adapter;
    DatabaseReference ref;
    public CompanyStudentFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.activity_company_student_list,container,false);
        myRecyclerView=(RecyclerView)v.findViewById(R.id.profileRecyclerView);
         adapter=new StudentAdapter(getContext(),studentDetail);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentDetail=new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                UserMoreDetails userMoreDetails=dataSnapshot.getValue(UserMoreDetails.class);
                if(userMoreDetails.getGenere().equals("Student")){
                studentDetail.add(userMoreDetails);
                adapter.notifyDataSetChanged();}



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key=dataSnapshot.getKey();
              //  Log.d(TAG, "onChildRemoved: "+dataSnapshot.getValue().toString());
                for(UserMoreDetails aq:studentDetail)
                {if(key.equals(aq.getId()))
                {  studentDetail.remove(aq);
                    adapter.notifyDataSetChanged();
                    break;}
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
