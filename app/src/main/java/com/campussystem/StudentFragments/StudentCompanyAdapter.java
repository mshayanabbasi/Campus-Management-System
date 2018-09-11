package com.campussystem.StudentFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.campussystem.CompanyPanel.CompanyUserMoreDetail;
import com.campussystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by User on 3/15/2018.
 */

public class StudentCompanyAdapter extends RecyclerView.Adapter<StudentCompanyAdapter.MyViewHolder> {
    Activity activity;
    Context context;
    ArrayList<CompanyUserMoreDetail> mData=new ArrayList<>();
   FirebaseAuth nAuth;
    public StudentCompanyAdapter(Context context, ArrayList<CompanyUserMoreDetail> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.student_custom_company_list,parent,false);
        StudentCompanyAdapter.MyViewHolder vHolder=new StudentCompanyAdapter.MyViewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String id = mData.get(position).getId();
       /* if(mData.get(position).equals(null)){}*/
        holder.cName.setText(mData.get(position).getName());
        holder.cType.setText(mData.get(position).getCompanyType());
        holder.cAddress.setText(mData.get(position).getAddress());
        holder.cContact.setText(mData.get(position).getContacts());
        holder.jobCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,NewJobActivity.class);
                intent.putExtra("Id",mData.get(position).getId());
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cName,cType,cAddress,cContact;
        Button jobCompany;


        public MyViewHolder(View itemView) {
            super(itemView);

            cName=(TextView)itemView.findViewById(R.id.nameCompanysSt);
            cType=(TextView)itemView.findViewById(R.id.typeCompanysSt);
            cAddress=(TextView)itemView.findViewById(R.id.addressCompanysSt);
            cContact=(TextView)itemView.findViewById(R.id.contactCompanysSt);
            jobCompany=(Button)itemView.findViewById(R.id.jobCompany);


        }
    }
}
