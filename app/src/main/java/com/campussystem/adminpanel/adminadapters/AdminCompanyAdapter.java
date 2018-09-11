package com.campussystem.adminpanel.adminadapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.campussystem.CompanyPanel.CompanyMain.JobActivity;
import com.campussystem.CompanyPanel.CompanyUserMoreDetail;
import com.campussystem.R;
import com.campussystem.StudentAdapter;
import com.campussystem.StudentFragments.NewJobActivity;
import com.campussystem.adminpanel.AdminCompanyFragment;
import com.campussystem.adminpanel.AdminJobActivity;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.campussystem.R.id.parent;

/**
 * Created by User on 3/15/2018.
 */

public class AdminCompanyAdapter extends RecyclerView.Adapter<AdminCompanyAdapter.MyViewHolder> {
    Activity activity;
    Context context;
    List<CompanyUserMoreDetail> mData=new ArrayList<>();
   FirebaseAuth nAuth;
    public AdminCompanyAdapter(Context context, List<CompanyUserMoreDetail> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(context).inflate(R.layout.custom_company_list,parent,false);
        AdminCompanyAdapter.MyViewHolder vHolder=new AdminCompanyAdapter.MyViewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String id=mData.get(position).getId();
        holder.cName.setText(mData.get(position).getName());
        holder.cType.setText(mData.get(position).getCompanyType());
        holder.cAddress.setText(mData.get(position).getAddress());
        holder.cContact.setText(mData.get(position).getContacts());
        holder.buttonJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AdminJobActivity.class);
                intent.putExtra("Id",mData.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });





        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // CompanyUserMoreDetail company=mData.get(position);
               // showUpdateDialog(position,mData.get(position).getName(),mData.get(position).getCompanyType(),mData.get(position).getAddress(),mData.get(position).getContacts(),mData.get(position).getId(),mData.get(position).getEmail());
              deleteArtist(mData.get(position).getEmail(),position,mData.get(position).getPassword());
              notifyDataSetChanged();

               //authDelete(company.getEmail());
               // authDelete(mData.get(position).getEmail(),position);
               // Toast.makeText(context,""+position,Toast.LENGTH_LONG).show();
            }
        });
    }





    private void deleteArtist(String emails, final int position, final String password) {


        nAuth=FirebaseAuth.getInstance();
        nAuth.signInWithEmailAndPassword(emails,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View dialogView=inflater.inflate(R.layout.activity_update,null);
                    dialogBuilder.setView(dialogView);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    String uid=nAuth.getCurrentUser().getUid();
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("All post").child("users").child("company").child(uid);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String key =dataSnapshot.child("name").getValue(String.class);
                            dialogBuilder.setTitle("Delete"+key);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Button no=(Button)dialogView.findViewById(R.id.btnNo);
                    Button dels=(Button)dialogView.findViewById(R.id.btnDeletes);
                    dels.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            AuthCredential credential = EmailAuthProvider.getCredential(nAuth.getCurrentUser().getEmail(), password);
                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        user.delete();

                                        delete(mData.get(position).getId());

                                        alertDialog.dismiss();
                                       /* FirebaseAuth.getInstance().signOut();*/



                                    }
                                    else {
                                        Toast.makeText(context.getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                    }


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context.getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
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


    private void delete(final String id) {

        final DatabaseReference ref3= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id).child("jobs");
        ref3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                JobActivity activity=dataSnapshot.getValue(JobActivity.class);
                if(activity.getId().equals(id))
                {
                    String idd=activity.getjId();
                    DatabaseReference ref4=FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id).child("jobs").child(idd).child("Applied Students");
                    ref4.removeValue();
                    Toast.makeText(context,""+id,Toast.LENGTH_LONG).show();
                   // activity(context.finish());
                 /*   Intent intent=new Intent();
                    context.startActivity(intent);*/
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
        });

        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id).child("jobs");
        ref1.removeValue();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id);
        ref.removeValue();





    }





    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cName,cType,cAddress,cContact;
        Button del,buttonJobs;

        public MyViewHolder(View itemView) {
            super(itemView);

            cName=(TextView)itemView.findViewById(R.id.nameCompanys);
            cType=(TextView)itemView.findViewById(R.id.typeCompanys);
            cAddress=(TextView)itemView.findViewById(R.id.addressCompanys);
            cContact=(TextView)itemView.findViewById(R.id.contactCompanys);
            del=(Button)itemView.findViewById(R.id.btnDelete);
            buttonJobs=(Button)itemView.findViewById(R.id.buttonJobs);

        }
    }
}
