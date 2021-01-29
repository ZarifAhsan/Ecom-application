package com.example.ecom_application.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        Toolbar toolbar = findViewById(R.id.toolbar_view_cart);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cart");

        TextView noItem = findViewById(R.id.no_item_text_view);

        RecyclerView recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        if (!productList.isEmpty()) {
            AdapterCart adapterCart = new AdapterCart(this, productList);
            recyclerView.setAdapter(adapterCart);
        } else {
            noItem.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.place_order) {
            // Todo functions
        }
        return super.onOptionsItemSelected(item);
    }
}