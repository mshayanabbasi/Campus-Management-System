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

import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.campussystem.StudentFragments.NewApplyActivity;
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

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {
    int i=0;
       FirebaseAuth nAuth;
       DatabaseReference ref;
    Context context;
    List<JobActivity> mDatas;
    String names,qualifications,university;
    String compId,jobId;
    String job,type,experience,shift,salary;
    String uids;
    String jids;
    String uid;
/*    String acts;*/
   // String id;
   /* DataSnapshot jids=null;
    String ids=null;*/


    public JobAdapter(Context mContext, List<JobActivity> mDatas) {
        this.context = mContext;
        this.mDatas = mDatas;




    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_job_list,parent,false);
       MyViewHolder viewHolders=new MyViewHolder(v);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.job.setText(mDatas.get(position).getJob());
        holder.type.setText(mDatas.get(position).getJobType());
        holder.experience.setText(mDatas.get(position).getJobExperience());
        holder.shift.setText(mDatas.get(position).getJobShift());
        holder.salary.setText(mDatas.get(position).getJobSalary());
       // holder.status.setText(appDetail.get(i).getName());
        holder.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jobId=mDatas.get(position).getjId();
                job=mDatas.get(position).getJob();
                type=mDatas.get(position).getJobType();
                shift=mDatas.get(position).getJobShift();
                salary=mDatas.get(position).getJobSalary();
                experience=mDatas.get(position).getJobExperience();




             /*   nAuth=FirebaseAuth.getInstance();
               String uid=nAuth.getCurrentUser().getUid();*/

                nAuth=FirebaseAuth.getInstance();
                uid=nAuth.getCurrentUser().getUid();
                compId= mDatas.get(position).getId();
                jobId=mDatas.get(position).getjId();
                ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                                      names=valueOf(dataSnapshot.child("userName").getValue());
                        qualifications=valueOf(dataSnapshot.child("qualification").getValue());
                        university=valueOf(dataSnapshot.child("university").getValue());
                       // ((Activity)context).finish();
                        Intent intent=new Intent(context, NewApplyActivity.class);
                        intent.putExtra("jobId",jobId);
                        intent.putExtra("uid",uid);
                        intent.putExtra("compId",compId);

                        intent.putExtra("name",names);
                        intent.putExtra("qualification",qualifications);
                        intent.putExtra("university",university);
                        intent.putExtra("jobid",jobId);
                        intent.putExtra("job",job);
                        intent.putExtra("type",type);
                        intent.putExtra("shift",shift);
                        intent.putExtra("salary",salary);
                        intent.putExtra("experience",experience);

                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);

/*
                        ref=FirebaseDatabase.getInstance().getReference().child("All post").child("Applied Students");

                        String appid=ref.push().getKey();
                        ApplyJobActivity applyJobActivitys=new ApplyJobActivity(compId,uid,names,qualifications,university,jobId,appid,"you have applied for this job and it is pending");
                        ref.child(appid).setValue(applyJobActivitys);*/



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



/*
                ref=FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid).child("Applied Student");
                NewApply newApply=new NewApply(jobId,"Applied");
                ref.child(jobId).setValue(newApply);
                Toast.makeText(context,"you applied for this jobs",Toast.LENGTH_LONG).show();
                holder.btnApply.setVisibility(View.GONE);
                holder.status.setText("Applied");*/
                // public ApplyJobActivity(String jobId, String uid, String name, String qualification, String university)
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView job,type,experience,shift,salary;
        Button btnApply;
        TextView status;
        public MyViewHolder(View itemView) {
            super(itemView);
            job=(TextView)itemView.findViewById(R.id.job);
            type=(TextView)itemView.findViewById(R.id.typeJob);
            experience=(TextView)itemView.findViewById(R.id.experienceJob);
            shift=(TextView)itemView.findViewById(R.id.shiftJob);
            salary=(TextView)itemView.findViewById(R.id.salaryJob);
            btnApply=(Button)itemView.findViewById(R.id.btnApply);
            status=(TextView) itemView.findViewById(R.id.status);
        }
    }
}
