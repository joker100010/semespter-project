package com.example.semesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        EditText Name, Email, Password, Repassword;
        Button Register;
        TextView LoginText;
        FirebaseAuth Fauth;
        ProgressBar ProgressBar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);

        //fields
        Name = (EditText) findViewById(R.id.Nameid);
        Email = findViewById(R.id.Emailid);
        Password = (EditText) findViewById(R.id.Passwordid);
        Repassword = (EditText) findViewById(R.id.Repassowrdid);

        //Button
        Register = findViewById(R.id.Registorbtn);

        //Textviewe
        LoginText = (TextView) findViewById(R.id.Logintxt);

        //Firebase auth
        Fauth =  FirebaseAuth.getInstance();
        //ProgressBar = findViewById(R.id.)

        if(Fauth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),App.class));
            finish();
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Email.setError("Kindly add your email");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Password.setError("Kindly add your password");
                    return;
                }
                if(Password.length() < 6)
                {
                    Password.setError("Kindly add password more than 6 words");
                }

                //ProgressBar.setVisibility(View.VISIBLE);

                Fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Registor.this,"Registered Scucessfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                        else
                        {
                            Toast.makeText(Registor.this,"Registered un Scucessfull",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                LoginText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(getApplicationContext(),Login.class));
                    }
                });

            }
        });


    }
}