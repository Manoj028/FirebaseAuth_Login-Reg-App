package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSuccessful extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        auth = FirebaseAuth.getInstance();
        MaterialButton Logout = (MaterialButton) findViewById(R.id.Logout);
        user = auth.getCurrentUser();
        TextView userdetail = findViewById(R.id.userdetails);

        if(user == null){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            userdetail.setText(user.getEmail());
        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}