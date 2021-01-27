package com.example.ecom_application.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.ecom_application.R;
import com.example.ecom_application.ui.auth.LoginActivity;
import com.example.ecom_application.ui.auth.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button RegisterButton;
    private Button LoginButton;
    private Button ContactButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RegisterButton = findViewById(R.id.registerBtn);
        LoginButton = findViewById(R.id.loginBtn);
        ContactButton = findViewById(R.id.contactUsBtn);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        checkForUser();
        bindListeners();

    }

    private void bindListeners() {

        RegisterButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        LoginButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        ContactButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
        });

    }

    private void checkForUser() {
        if (mUser != null) {
            startActivity(new Intent(MainActivity.this, MainUi.class));
            finish();
        }
    }

}