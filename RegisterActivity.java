package com.example.myloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        TextView btn = findViewById(R.id.alreadyHaveAccount);
        EditText EtUsername = findViewById(R.id.username);
        EditText Etemail = findViewById(R.id.email);
        EditText Etpassword = findViewById(R.id.password);
        EditText Etconfirmpass=findViewById(R.id.ConfirmPassword);
        MaterialButton RegBtn = (MaterialButton) findViewById(R.id.Registerbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String UserName, email , password , confirmpass;
               email = String.valueOf(Etemail.getText());
               password = String.valueOf(Etpassword.getText());
               confirmpass = String.valueOf(Etconfirmpass.getText());
               UserName = String.valueOf(EtUsername.getText());


                if(TextUtils.isEmpty(UserName)){
                    Toast.makeText(RegisterActivity.this, "Please enter the UserName", Toast.LENGTH_SHORT).show();
                    return;
                }

               if(TextUtils.isEmpty(email)){
                   Toast.makeText(RegisterActivity.this, "Please enter the email", Toast.LENGTH_SHORT).show();
                   return;
               }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpass)){
                    Toast.makeText(RegisterActivity.this, "Please re-enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });




            }
        });
    }
}