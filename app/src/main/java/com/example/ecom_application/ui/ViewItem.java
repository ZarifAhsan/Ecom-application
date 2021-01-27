package com.example.ecom_application.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecom_application.R;

public class ViewItem extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        ImageView productImage = findViewById(R.id.view_item_img);
        TextView productName = findViewById(R.id.view_item_name);
        TextView productPrice = findViewById(R.id.view_item_price);

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        String price = intent.getStringExtra("Price");
        String image = intent.getStringExtra("Image_Url");

        Glide.with(this).load(image).into(productImage);
        productName.setText(name);
        productPrice.setText("$" + price);
    }
}