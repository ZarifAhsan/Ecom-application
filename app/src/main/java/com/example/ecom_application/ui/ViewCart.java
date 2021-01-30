package com.example.ecom_application.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecom_application.R;
import com.example.ecom_application.data.Order;
import com.example.ecom_application.data.Products;
import com.example.ecom_application.recycler.AdapterCart;
import com.example.ecom_application.recycler.ItemAdapter;
import com.example.ecom_application.recycler.ItemAdapterHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.ecom_application.ui.ViewItem.productList;
import static com.example.ecom_application.ui.auth.RegisterActivity.finalMEmail;

public class ViewCart extends AppCompatActivity {

    private AdapterCart adapterCart;

    public static int totalCostOfItems = 0;

    private static TextView totalCostView;

    private static final ArrayList<String> pidList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        Toolbar toolbar = findViewById(R.id.toolbar_view_cart);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("On Cart");

        TextView noItem = findViewById(R.id.no_item_text_view);
        totalCostView = findViewById(R.id.total_cost_text_view);

        totalCostView.setText("$ " + String.valueOf(totalCostOfItems));

        RecyclerView recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        if (!productList.isEmpty()) {
            adapterCart = new AdapterCart(this, productList);
            new ItemTouchHelper(ItemTouchHelperCallback).attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(adapterCart);
        } else {
            noItem.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }

        setTotalCost();
    }

    @SuppressLint("SetTextI18n")
    public static void setTotalCost() {
        totalCostView.setText("$ " + String.valueOf(totalCostOfItems));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.place_order) {
            pushOrderToDatabase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void pushOrderToDatabase() {
        arrangeData();
        Order order = new Order(pidList, finalMEmail, totalCostOfItems);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Orders");
        ref.child(finalMEmail).push().setValue(order).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getBaseContext(), "Your order has been placed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ItemTouchHelper.SimpleCallback ItemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            productList.remove(viewHolder.getAdapterPosition());
            adapterCart.notifyDataSetChanged();
            setTotalCost();
        }
    };

    private void arrangeData() {
        int s = productList.size();
        for (int i=0; i<s; i++) {
            Products item1 = productList.get(i);
            pidList.add(item1.getPID());
        }


    }
}