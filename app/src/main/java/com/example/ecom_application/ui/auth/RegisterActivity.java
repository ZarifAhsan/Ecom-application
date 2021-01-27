package com.example.ecom_application.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ecom_application.R;
import com.example.ecom_application.ui.MainUi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField;

    private Button registerButton;

    private CheckBox termsAndConditionButton;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        usernameField = findViewById(R.id.usernameField);
        emailField = findViewById(R.id.emailAddressField);
        passwordField = findViewById(R.id.passwordField);

        registerButton = findViewById(R.id.registerBtn);

        termsAndConditionButton = findViewById(R.id.termsAndConditionsBox);

        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();


        bindListeners();
    }

    private void bindListeners() {

        registerButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if (username.equals("")
                    || email.equals("")
                    || password.equals("")
                    || !termsAndConditionButton.isChecked()) {

                Toast.makeText(this, "All fields are not properly filled", Toast.LENGTH_SHORT).show();

            } else {
                signUpUser(username, email, password);
            }
        });
    }

    private void signUpUser(String username, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User created",
                                Toast.LENGTH_SHORT).show();
                        dataQuery(username, email);
                        startActivity(new Intent(RegisterActivity.this, MainUi.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "User creation failed",
                                Toast.LENGTH_SHORT).show();
                    }

                });

    }

    private void dataQuery(String username, String fullEmail) {
        String mEmail = fullEmail;
        if (fullEmail.contains("@")) {
            String[] mailStripper = fullEmail.split("@");
            String emailName = mailStripper[0];
            String emailValue = mailStripper[1];
            String emailValue2 = emailValue.replace(".", "_");
            mEmail = emailName + "_" + emailValue2;
        }
        String finalMEmail = mEmail;

        DatabaseReference ref = mDb.getReference().child("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(finalMEmail)) {
                    ref.child(finalMEmail).push().setValue(username, "UserName")
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Log.i("RegisterActivity", "User added to database");
                                } else {Log.i("RegisterActivity", "Failed to add user on database");}
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("RegisterActivity", "Failed to retrieve data from firebase");
            }
        });
    }

}