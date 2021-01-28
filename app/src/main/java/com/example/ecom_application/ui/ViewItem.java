package com.example.ecom_application.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecom_application.R;
import com.example.ecom_application.data.Products;

import java.util.ArrayList;
import java.util.Objects;

public class ViewItem extends AppCompatActivity {

    private Button addToCart;

    String name, price, pid, image;

    public static final ArrayList<Products> productList = new ArrayList<>();

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

        name = intent.getStringExtra("Name");
        price = intent.getStringExtra("Price");
        image = intent.getStringExtra("Image_Url");
        pid = intent.getStringExtra("PID");

        Objects.requireNonNull(getSupportActionBar()).setTitle(name);

        Glide.with(this).load(image).into(productImage);
        productName.setText(name);
        productPrice.setText("$" + price);

        bindListeners();
    }

    private void bindListeners() {
        addToCart.setOnClickListener(v -> {
            Products singleItem = new Products(image, name, Long.parseLong(price), pid);

            productList.add(singleItem);
            Toast.makeText(ViewItem.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
        });
    }
}