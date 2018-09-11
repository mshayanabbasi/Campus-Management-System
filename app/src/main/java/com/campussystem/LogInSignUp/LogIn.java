package com.campussystem.LogInSignUp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



import com.campussystem.CompanyPanel.CompanyMain.Main2Activity;
import com.campussystem.R;

import com.campussystem.StudentsActivity;
import com.campussystem.adminpanel.Admins;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    FirebaseAuth nAuth;
EditText editEmail,editPassword;
    Button btnLogin,btnSignUp;
    DatabaseReference dataRef;
    ProgressBar progressBar;
    String admin="admin@gmail.com";
    String pass="123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        progressBar=(ProgressBar)findViewById(R.id.progressbarLogin);
        editEmail=(EditText)findViewById(R.id.editEmail);
        editPassword=(EditText)findViewById(R.id.editPassword);
        btnLogin=(Button)findViewById(R.id.loginBtn);
        btnSignUp=(Button)findViewById(R.id.signUpBtn);
        nAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUpActivity.class));
            }
        });




    }

    private void userLogin() {

        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editEmail.setError("email is required");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {  editEmail.setError("please enter valid email");
            editEmail.requestFocus();
            return;

        }

        if(password.isEmpty())
        {
            editPassword.setError("password is required");
            editPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editPassword.setError("please enter 6 digits");
            editPassword.requestFocus();
            return;

        }

     /*   if (email.equals(admin)) {
            if (password.equals(pass)) {
                Intent intent = new Intent(LogIn.this, Admins.class);
                startActivity(intent);
            }
        }*/
        else {
            progressBar.setVisibility(View.VISIBLE);
            nAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull final Task<AuthResult> task) {


                    final String id;
                    if(task.isSuccessful())
                    {
                       id= nAuth.getCurrentUser().getUid();
                        dataRef= FirebaseDatabase.getInstance().getReference().child("All post").child("users").child(id);
                        dataRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                            /*  if(dataSnapshot.equals(null))
                              {
                                 Log.e("TAG", String.valueOf(task.getException()));
                              }*/
                            try {


                                String key = dataSnapshot.child("genere").getValue(String.class);
                                if (key.equals("Admin")) {
                                    progressBar.setVisibility(View.GONE);
                                    finish();
                                    startActivity(new Intent(LogIn.this, Admins.class));
                                }


                                if (key.equals("Student")) {
                                    progressBar.setVisibility(View.GONE);
                                    finish();
                                    startActivity(new Intent(LogIn.this, StudentsActivity.class));

                                }
                                if (key.equals("Company")) {
                                    progressBar.setVisibility(View.GONE);
                                    // Toast.makeText(LogIn.this,""+key,Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(LogIn.this, Main2Activity.class));

                                } else {


                                }

                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                                // genre[0] =key;
                              //  Toast.makeText(LogIn.this,""+key,Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("TAG", String.valueOf(task.getException()));

                            }
                        });}
                    else {progressBar.setVisibility(View.GONE);
                        editEmail.setText("");
                        editPassword.setText("");
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }




                }
            });








        }





    }


}
