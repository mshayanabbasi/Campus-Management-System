package com.campussystem.adminpanel.adminadapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.campussystem.CompanyPanel.CompanyMain.AppliedStudentList;
import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.campussystem.adminpanel.AdminAppliedStudentList;
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

public class AdminJobAdapter extends RecyclerView.Adapter<AdminJobAdapter.MyViewHolder> {

       FirebaseAuth nAuth;
       DatabaseReference ref;
    Context context;
    List<JobActivity> mDatas;
    String names,qualifications,university;
    String comId,jId;
    String uid;

    public AdminJobAdapter(Context mContext, List<JobActivity> mDatas) {
        this.context = mContext;
        this.mDatas = mDatas;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_admin_job_list,parent,false);
       MyViewHolder viewHolders=new MyViewHolder(v);
        return viewHolders;





    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


     jId=mDatas.get(position).getjId();
     comId= mDatas.get(position).getId();
        holder.job.setText(mDatas.get(position).getJob());

        holder.type.setText(mDatas.get(position).getJobType());
        holder.experience.setText(mDatas.get(position).getJobExperience());
        holder.shift.setText(mDatas.get(position).getJobShift());
        holder.salary.setText(mDatas.get(position).getJobSalary());
        holder.appliedStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comId= mDatas.get(position).getId();
                jId=mDatas.get(position).getjId();
                Intent intent=new Intent(context,AdminAppliedStudentList.class);
                intent.putExtra("CId",comId);
                intent.putExtra("JId",jId);
                context.startActivity(intent);
            }
        });



        holder.btnJobDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       delete(jId,comId);



            }
        });


    }

    private void delete(String jId, String cId) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(cId).child("jobs").child(jId);
        ref.removeValue();
      /*  DatabaseReference ref2=FirebaseDatabase.getInstance().getReference().child("All post").child("Jobs").child(idJob).child(cId);
        ref2.removeValue();*/

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView job,type,experience,shift,salary;
        Button btnJobDel,appliedStudents;


        public MyViewHolder(View itemView) {
            super(itemView);


            job=(TextView)itemView.findViewById(R.id.jobsAd);
            type=(TextView)itemView.findViewById(R.id.typeJobsAd);
            experience=(TextView)itemView.findViewById(R.id.experienceJobsAd);
            shift=(TextView)itemView.findViewById(R.id.shiftJobsAd);
            salary=(TextView)itemView.findViewById(R.id.salaryJobsAd);
            btnJobDel=(Button)itemView.findViewById(R.id.btnJobDel);
            appliedStudents=(Button)itemView.findViewById(R.id.appliedStudents);



        }
    }
}
