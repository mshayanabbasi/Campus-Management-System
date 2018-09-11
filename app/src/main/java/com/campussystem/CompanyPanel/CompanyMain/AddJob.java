package com.campussystem.CompanyPanel.CompanyMain;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddJob extends AppCompatActivity {
    String uid;
    String id;
    FirebaseAuth mAuth;
    DatabaseReference ref;
EditText edtJob,edtJobType,edtJobExperience,edtJobShift,edtJobSalary;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        mAuth=FirebaseAuth.getInstance();
       uid= mAuth.getCurrentUser().getUid();
        edtJob=(EditText)findViewById(R.id.job);
        edtJobType=(EditText)findViewById(R.id.jobType);
        edtJobExperience=(EditText)findViewById(R.id.jobExperience);
         edtJobShift=(EditText)findViewById(R.id.jobShift);
        edtJobSalary=(EditText)findViewById(R.id.jobSalary);
        btnSubmit=(Button)findViewById(R.id.jobSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJobs();

            }
        });






    }

    private void addJobs() {

        String job=edtJob.getText().toString().trim();
        String type=edtJobType.getText().toString().trim();
        String experience=edtJobExperience.getText().toString().trim();
        String shift=edtJobShift.getText().toString().trim();
        String salary=edtJobSalary.getText().toString().trim();
        if(job.isEmpty())
        {
            edtJob.setError("job required");
            edtJob.requestFocus();
        }
        if(type.isEmpty())
        {
            edtJobType.setError("type required");
            edtJobType.requestFocus();
        }
        if(experience.isEmpty())
        {
            edtJobExperience.setError("experinec required");
            edtJobExperience.requestFocus();
        }
        if(shift.isEmpty())
        {
            edtJobShift.setError("Shift required");
            edtJobShift.requestFocus();
        }
        if(salary.isEmpty())
        {
            edtJobSalary.setError("salary required");
            edtJobSalary.requestFocus();
        }
        else {



        ref= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid).child("jobs");
        String id = ref.push().getKey();
        JobActivity userActivitys = new JobActivity(id,uid,job,type,experience,shift,salary);
        ref.child(id).setValue(userActivitys);
            finish();


        }



 //public JobActivity(String id, String job, String jobType, String jobExperience, String jobShift, String jobSalary)
    }
}
