package com.example.storagesystemapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText Forgemail;
    private Button sendEmail;
    private TextView goBack;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Forgemail = findViewById(R.id.ForgotEmail);

        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(this);

        sendEmail = findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendEmail:
                resetPassword();
                break;


            case R.id.goBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }

    private void resetPassword(){
        String emailAddress = Forgemail.getText().toString().trim();

        if(emailAddress.isEmpty()){
            Forgemail.setError("email can not be empty!");
            Forgemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            Forgemail.setError("please provide valid email");
            Forgemail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Something wrong, try again", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}