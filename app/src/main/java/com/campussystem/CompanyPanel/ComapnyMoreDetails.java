package com.campussystem.CompanyPanel;

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

import com.campussystem.LogInSignUp.LogIn;
import com.campussystem.R;
import com.campussystem.StudentsMoreDetail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComapnyMoreDetails extends AppCompatActivity {
 TextView company,comName;
    EditText address,companyType,contacts;
    Button btnReg;
    DatabaseReference dr;
    FirebaseAuth nAuth;
    String name;
    String type;
    String email;
    String password;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comapny_more_details);
        progressBar=(ProgressBar)findViewById(R.id.progressbarCompany);
        nAuth=FirebaseAuth.getInstance();
       company=(TextView)findViewById(R.id.emailCompany);
        address=(EditText)findViewById(R.id.address);
        companyType=(EditText)findViewById(R.id.companyType);
        contacts=(EditText)findViewById(R.id.editTextContact);
        comName=(TextView)findViewById(R.id.cName);
        btnReg=(Button)findViewById(R.id.buttonRegister);
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        type = intent.getStringExtra("Type");
       // final String id=intent.getStringExtra("Id");
        email=intent.getStringExtra("Email");
        password=intent.getStringExtra("Password");

         company.setText(email);
          comName.setText(name);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                add();

                //public CompanyUserMoreDetail(String id, String name, String email, String address, String companyType, String contacts,String genere)
            }
        });
    }

    private void add() {
        final String companyTypes=companyType.getText().toString();
        final String contact=contacts.getText().toString().trim();
        final String cAddress=address.getText().toString().trim();

        if(companyTypes.isEmpty())
        {
            companyType.setError("Name Required");
            contacts.requestFocus();
        }

        if(contact.isEmpty())
        {
            contacts.setError("Contact required");
            contacts.requestFocus();
        }

        if(cAddress.isEmpty())
        {
            address.setError("Address required");
            address.requestFocus();
        }

        else
        { progressBar.setVisibility(View.VISIBLE);
            nAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful())
                    {   progressBar.setVisibility(View.GONE);
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "you are already registered", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }


                        else{
                        progressBar.setVisibility(View.GONE);
                        String userID=nAuth.getCurrentUser().getUid();
                        dr = FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(userID);
                        //String idd = dr.push().getKey();
                        CompanyUserMoreDetail detailCompany=new CompanyUserMoreDetail(userID,name,email,cAddress,companyTypes,contact,type,password);
                        dr.setValue(detailCompany);
                        finish();
                        startActivity(new Intent(ComapnyMoreDetails.this, LogIn.class));






                    }



                }
            });





        }



    }


}
