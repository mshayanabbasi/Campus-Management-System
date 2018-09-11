package com.campussystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.campussystem.CompanyPanel.ComapnyMoreDetails;
import com.campussystem.LogInSignUp.LogIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentsMoreDetail extends AppCompatActivity {
    TextView textName,textType;
EditText editQualification;
    Button buttonRegister;
    EditText editUniversity;
    EditText editGpa;
    DatabaseReference dr;
    FirebaseAuth nAuth;
    ProgressBar progressBar;
  String name;
    String type;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_more_detail);
        progressBar=(ProgressBar)findViewById(R.id.progressbarStudent);
        nAuth=FirebaseAuth.getInstance();
        textName=(TextView)findViewById(R.id.textUserName);
        textType=(TextView)findViewById(R.id.textType);
        editQualification= (EditText) findViewById(R.id.editQualification);
        editUniversity=(EditText)findViewById(R.id.editTextUniversity);
        editGpa=(EditText)findViewById(R.id.gpa);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        Intent intent = getIntent();
      name = intent.getStringExtra("Name");
     type = intent.getStringExtra("Type");
       // final String idd=intent.getStringExtra("Id");
      email=intent.getStringExtra("Email");
         password=intent.getStringExtra("Password");
        textName.setText(name);
        textType.setText(type);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //public UserMoreDetails(String id, String userName, String genere, String qualification, String gpa,String university,String email,String password)
                //(String id, String userName, String genere, String qualification, String gpa,String university,)


                add();
            }
        });
    }

    private void add() {
        final String sQualification=editQualification.getText().toString().trim();
        final String sUniversity=editUniversity.getText().toString().trim();
        final String sGpa=editGpa.getText().toString().trim();
        if(sQualification.isEmpty())
        {
            editQualification.setError("qualification req");
            editQualification.requestFocus();

        }
        if(sUniversity.isEmpty())
        {
            editUniversity.setError("university error");
            editQualification.requestFocus();
        }

        if(sGpa.isEmpty())
        {
            editGpa.setError("GAp required");
            editGpa.requestFocus();
        }

        else {
            progressBar.setVisibility(View.VISIBLE);
            nAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "you are already registered", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                 else{
                        progressBar.setVisibility(View.GONE);

                        String uid=nAuth.getCurrentUser().getUid();
                        dr = FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(uid);
                        //String id = dr.push().getKey();
                        UserMoreDetails userMoreDetail=new UserMoreDetails(uid,name,type,sQualification,sGpa,sUniversity,email,password);
                        dr.setValue(userMoreDetail);
                        finish();
                        startActivity(new Intent(StudentsMoreDetail.this, LogIn.class));
                    }





                }
            });






        }






    }
}
