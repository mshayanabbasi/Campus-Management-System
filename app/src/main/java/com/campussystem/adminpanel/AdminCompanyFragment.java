package com.campussystem.adminpanel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campussystem.CompanyPanel.CompanyUserMoreDetail;
import com.campussystem.R;
import com.campussystem.adminpanel.adminadapters.AdminCompanyAdapter;
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
 * Created by User on 3/15/2018.
 */

public class AdminCompanyFragment extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
     List<CompanyUserMoreDetail> jobDetail;
    AdminCompanyAdapter adapter;
    DatabaseReference ref;
    FirebaseAuth mAuth;

    public AdminCompanyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_admin_company_list, container, false);


        myRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewCompany);
        adapter = new AdminCompanyAdapter(getContext(), jobDetail);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (jobDetail == null){
        jobDetail = new ArrayList<>();}
        ref = FirebaseDatabase.getInstance().getReference().child("All post").child("users");
      /*  ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    CompanyUserMoreDetail users = snapshot.getValue(CompanyUserMoreDetail.class);
                    jobDetail.add(users);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



       ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyUserMoreDetail companyUserMoreDetail = dataSnapshot.getValue(CompanyUserMoreDetail.class);
                if(companyUserMoreDetail.getGenere().equals("Company"))
                {
                jobDetail.add(companyUserMoreDetail);
                adapter.notifyDataSetChanged();}


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
                adapter.notifyDataSetChanged();
*/

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();

//                for (CompanyUserMoreDetail aq : jobDetail) {
//                    if (key.equals(aq.getId())) {
//
//                        jobDetail.remove(key);
//                        adapter.notifyItemRemoved(Integer.parseInt(key));
//                       adapter.notifyDataSetChanged();
//                        break;
//                    }
//
//                }
               // CompanyUserMoreDetail detail = dataSnapshot.getValue(CompanyUserMoreDetail.class);

                for (CompanyUserMoreDetail detail:jobDetail) {
                    if (key.equals(detail.getId())) {
                        int index = jobDetail.indexOf(detail);

                        jobDetail.remove(index);
                       // index--;
                       adapter.notifyItemRemoved(index);
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
