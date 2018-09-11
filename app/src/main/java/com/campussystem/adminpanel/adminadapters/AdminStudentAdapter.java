package com.campussystem.adminpanel.adminadapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.R;
import com.campussystem.StudentFragments.ApplyJobActivity;
import com.campussystem.UserMoreDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by User on 3/14/2018.
 */

public class AdminStudentAdapter extends RecyclerView.Adapter<AdminStudentAdapter.MyViewHolder> {
   Context mContext;
    List<UserMoreDetails> mData;
    FirebaseAuth nAuth;

    public AdminStudentAdapter(Context mContext, List<UserMoreDetails> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.admin_student_custom_list,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.sNames.setText(mData.get(position).getUserName());
        holder.sQualifications.setText(mData.get(position).getQualification());
        holder.sUnivesitys.setText(mData.get(position).getUniversity());
        holder.sGpa.setText(mData.get(position).getGpa());
        holder.delStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent(mData.get(position).getEmail(),mData.get(position).getPassword(),position);
            }
        });



    }


   private void delete(final String id)
   {
      // final String[] jIdd = new String[1];
       DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id);
       ref.removeValue();

   /*    final DatabaseReference ref3= FirebaseDatabase.getInstance().getReference().child("All post").child("Applied Students");
       ref3.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ApplyJobActivity act=dataSnapshot.getValue(ApplyJobActivity.class);
               if(act.getUid().equals(id))
               {
                   String ids=act.getAppId();
                   DatabaseReference ref4=FirebaseDatabase.getInstance().getReference().child("All post").child("Applied Students").child(ids);
                   ref4.removeValue();

               }
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
       });*/



   }



    private void deleteStudent(String email, final String password, final int position) {
        nAuth=FirebaseAuth.getInstance();
        nAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View dialogView=inflater.inflate(R.layout.activity_update,null);
                    dialogBuilder.setView(dialogView);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                    String uid=nAuth.getCurrentUser().getUid();
                    Button no=(Button)dialogView.findViewById(R.id.btnNo);
                    Button dels=(Button)dialogView.findViewById(R.id.btnDeletes);
                    dels.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            AuthCredential credential = EmailAuthProvider.getCredential(nAuth.getCurrentUser().getEmail(),password);
                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        user.delete();
                                        delete(mData.get(position).getId());
                                        alertDialog.dismiss();

                                    }
                                    else {
                                        Toast.makeText(mContext.getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                    }


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(mContext.getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });



                }





            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView sNames, sQualifications, sUnivesitys, sGpa;
        Button delStd;


        public MyViewHolder(View itemView) {
            super(itemView);
            sNames=(TextView)itemView.findViewById(R.id.nameStudents);
            sQualifications=(TextView)itemView.findViewById(R.id.qualificationStudents);
            sUnivesitys=(TextView)itemView.findViewById(R.id.universityStudents);
            sGpa=(TextView)itemView.findViewById(R.id.studentGpas);
            delStd=(Button)itemView.findViewById(R.id.stdDelete);


        }
    }





}
