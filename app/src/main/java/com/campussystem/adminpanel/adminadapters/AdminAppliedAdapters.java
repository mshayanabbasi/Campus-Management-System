package com.campussystem.adminpanel.adminadapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by User on 3/14/2018.
 */

public class AdminAppliedAdapters extends RecyclerView.Adapter<AdminAppliedAdapters.MyViewHolder>{

    Context context;
    List<ApplyJobActivity> mDatas;

    public AdminAppliedAdapters(Context context, List<ApplyJobActivity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.admin_applied_students,parent,false);
        AdminAppliedAdapters.MyViewHolder vHolder=new AdminAppliedAdapters.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.sName.setText(mDatas.get(position).getName());
        holder.sQualification.setText(mDatas.get(position).getQualification());
        holder.sUnivesity.setText(mDatas.get(position).getUniversity());
        holder.sGpa.setText("");

        holder.btnDeleteApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appId=mDatas.get(position).getAppId();
                String compId=mDatas.get(position).getCompId();
                String jId=mDatas.get(position).getjId();
                String name=mDatas.get(position).getName();
                String qualification=mDatas.get(position).getQualification();
                String status=mDatas.get(position).getStatus();
                String uid=mDatas.get(position).getUid();
                String university=mDatas.get(position).getUniversity();

                DatabaseReference ref2= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(compId).child("jobs").child(jId).child("Applied Students").child(uid);
                ref2.removeValue();
                ((Activity)context).finish();
/*
                DatabaseReference ref3=FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid).child("Applied Students").child(jId);
                ref3.removeValue();
                ((Activity)context).finish();*/

            }
        });

    }

    @Override
    public int getItemCount() {
      return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView sName,sQualification,sUnivesity,sGpa;
        Button btnDeleteApp;

        public MyViewHolder(View itemView) {
            super(itemView);
            sName=(TextView)itemView.findViewById(R.id.nameStudentApp);
            sQualification=(TextView)itemView.findViewById(R.id.qualificationStudentApp);
            sUnivesity=(TextView)itemView.findViewById(R.id.universityStudentApp);
            sGpa=(TextView)itemView.findViewById(R.id.studentGpaApp);
            btnDeleteApp=(Button)itemView.findViewById(R.id.btnDeleteApplied);




        }
    }

    }
