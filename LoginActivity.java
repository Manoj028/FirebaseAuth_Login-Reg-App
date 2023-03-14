package com.example.myloginapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), LoginSuccessful.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        TextView Email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);
        MaterialButton loginbtn =(MaterialButton)findViewById(R.id.loginbtn);
        TextView btn = findViewById(R.id.signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
                loginbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String EmailTxt = Email.getText().toString();
                        final String passwordTxt = password.getText().toString();

                        if(EmailTxt.isEmpty() && passwordTxt.isEmpty()){
                            Toast.makeText(LoginActivity.this, "Please enter Email and password", Toast.LENGTH_SHORT).show();
                        }

                       else if(EmailTxt.isEmpty()){
                            Toast.makeText(LoginActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                        }

                        else if(passwordTxt.isEmpty()){
                            Toast.makeText(LoginActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                        }
                        mAuth.signInWithEmailAndPassword(EmailTxt,passwordTxt)
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Login Successull",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginSuccessful.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });


                    }


                });
    }
}