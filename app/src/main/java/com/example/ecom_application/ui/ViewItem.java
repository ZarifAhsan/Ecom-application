package com.example.ecom_application.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecom_application.R;

import java.util.Objects;

public class ViewItem extends AppCompatActivity {

    private Button addToCart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Toolbar toolbar = findViewById(R.id.toolbar_view_item);
        setSupportActionBar(toolbar);

        addToCart = findViewById(R.id.add_to_cart_button);
        ImageView productImage = findViewById(R.id.view_item_img);
        TextView productName = findViewById(R.id.view_item_name);
        TextView productPrice = findViewById(R.id.view_item_price);

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        String price = intent.getStringExtra("Price");
        String image = intent.getStringExtra("Image_Url");
        String pid = intent.getStringExtra("PID");

        Objects.requireNonNull(getSupportActionBar()).setTitle(name);

        Glide.with(this).load(image).into(productImage);
        productName.setText(name);
        productPrice.setText("$" + price);

        bindListeners();
    }

    private void bindListeners() {
        addToCart.setOnClickListener(v -> {

        });
    }
}