package com.example.ddn_xbcad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText password, email;
    Button login, back;
    String pEmail, pPassword;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //***********************************************************

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.Login);
        back = findViewById(R.id.Back);
        progress = new ProgressDialog(Login.this);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                pEmail = email.getText().toString();
                pPassword = password.getText().toString();

                progress.setTitle("Loading");
                progress.setMessage("Logging in...");
                progress.show();

                if(pEmail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    return;
                }

                if(pPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }

                auth.signInWithEmailAndPassword(pEmail, pPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent j = new Intent(Login.this, ImageGallery.class);
                            startActivity(j);

                            //Fragment fragment = new VehiclesFragment();
                            //getSupportFragmentManager().beginTransaction().replace(R.id.container,new Gallery()).commit();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Cannot access this account", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //********************************************************
    }
}