package com.example.ecom_application.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecom_application.R;
import com.example.ecom_application.recycler.AdapterCart;
import com.example.ecom_application.recycler.ItemAdapter;
import com.example.ecom_application.recycler.ItemAdapterHome;

import java.util.Objects;

import static com.example.ecom_application.ui.ViewItem.productList;

public class ViewCart extends AppCompatActivity {

    private AdapterCart adapterCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cart");

        RecyclerView recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        if (productList != null) {
            adapterCart = new AdapterCart(this, productList);
        }

        recyclerView.setAdapter(adapterCart);
    }
}