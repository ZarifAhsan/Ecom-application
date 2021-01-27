package com.example.ecom_application.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecom_application.R;
import com.example.ecom_application.ui.MainUi;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    private Button loginButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailField = findViewById(R.id.emailAddressField);
        passwordField = findViewById(R.id.passwordField);

        loginButton = findViewById(R.id.loginBtn);

        mAuth = FirebaseAuth.getInstance();

        bindListeners();
    }

    private void bindListeners() {

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if (!email.equals("") || !password.equals("") || email != null || password != null) {
                loginUser(email, password);
            } else {
                Toast.makeText(LoginActivity.this, "Fields are not filled properly", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainUi.class));
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

}