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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText  mail, Password;
    Button Loginbtn;
    TextView RegistorText;
    FirebaseAuth Fauth;
    ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.Emailid);
        Password = (EditText) findViewById(R.id.Passwordid);

        //Button
        Loginbtn = findViewById(R.id.Loginbtn);

        //Text view
        RegistorText = (TextView) findViewById(R.id.CreateAccount);

        Fauth = FirebaseAuth.getInstance();

        Loginbtn.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View view)
            {
                String email = mail.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mail.setError("Kindly add your email");
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

                Fauth.signInWithEmailAndPassword(password,email).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Login Successfully Scucessfull",Toast.LENGTH_SHORT).show();
                            Intent I = new Intent(getApplicationContext(), App.class);
                            startActivity(I);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Login un-Scucessfull",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                RegistorText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(getApplicationContext(),Registor.class));
                    }
                });
            }
        });




    }
}