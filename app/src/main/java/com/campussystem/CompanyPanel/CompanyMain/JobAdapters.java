package com.campussystem.CompanyPanel.CompanyMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by User on 3/14/2018.
 */

public class JobAdapters extends RecyclerView.Adapter<JobAdapters.MyViewHolder> {

    FirebaseAuth nAuth;
    DatabaseReference ref;
    Context context;
    List<JobActivity> mDatas;
    String names,qualifications,university;
    String comId,jId;
    String uid;

    public JobAdapters(Context mContext, List<JobActivity> mDatas) {
        this.context = mContext;
        this.mDatas = mDatas;

    }

    @Override
    public JobAdapters.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_job_list_company,parent,false);
        JobAdapters.MyViewHolder viewHolders=new JobAdapters.MyViewHolder(v);
        return viewHolders;





    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.jobs.setText(mDatas.get(position).getJob());

        holder.types.setText(mDatas.get(position).getJobType());
        holder.experiences.setText(mDatas.get(position).getJobExperience());
        holder.shifts.setText(mDatas.get(position).getJobShift());
        holder.salarys.setText(mDatas.get(position).getJobSalary());


       holder.btnStudentAppliedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comId= mDatas.get(position).getId();
                jId=mDatas.get(position).getjId();
               // ((Activity)context).finish();
                Intent intent=new Intent(context,AppliedStudentList.class);
                intent.putExtra("CId",comId);
                intent.putExtra("JId",jId);

              //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);




            }
        });



    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView jobs,types,experiences,shifts,salarys;

       Button btnStudentAppliedList;

        public MyViewHolder(View itemView) {
            super(itemView);



            jobs=(TextView)itemView.findViewById(R.id.jobs);
            types=(TextView)itemView.findViewById(R.id.typeJobs);
            experiences=(TextView)itemView.findViewById(R.id.experienceJobs);
            shifts=(TextView)itemView.findViewById(R.id.shiftJobs);
            salarys=(TextView)itemView.findViewById(R.id.salaryJobs);
            btnStudentAppliedList=(Button)itemView.findViewById(R.id.listAppliedStudents);
/*
        btnStudentAppliedList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,AppliedStudentList.class);

                    context.startActivity(intent);



                }
            });*/




        }
    } {
}}
