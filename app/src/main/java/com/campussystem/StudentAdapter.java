package com.campussystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 3/14/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
   Context mContext;
    List<UserMoreDetails> mData;

    public StudentAdapter(Context mContext, List<UserMoreDetails> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.custom_student_list,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.sName.setText(mData.get(position).getUserName());
        holder.sQualification.setText(mData.get(position).getQualification());
        holder.sUnivesity.setText(mData.get(position).getUniversity());
        holder.sGpa.setText(mData.get(position).getGpa());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView sName,sQualification,sUnivesity,sGpa;


        public MyViewHolder(View itemView) {
            super(itemView);
            sName=(TextView)itemView.findViewById(R.id.nameStudent);
            sQualification=(TextView)itemView.findViewById(R.id.qualificationStudent);
            sUnivesity=(TextView)itemView.findViewById(R.id.universityStudent);
            sGpa=(TextView)itemView.findViewById(R.id.studentGpa);

        }
    }





}
