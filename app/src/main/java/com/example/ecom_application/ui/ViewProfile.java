package com.example.ecom_application.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom_application.R;
import com.google.firebase.auth.FirebaseAuth;

public class ViewProfile extends AppCompatActivity {

    private TextView profileName, userEmail;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        profileName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.email_field);
        signOut = findViewById(R.id.sign_out);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        userEmail.setText("Email: " + email);

        bindListeners();
    }

    private void bindListeners() {
        signOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}