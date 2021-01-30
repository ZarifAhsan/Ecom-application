package com.example.ecom_application.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecom_application.R;
import com.example.ecom_application.data.Products;
import com.example.ecom_application.ui.ViewItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ItemAdapterHome extends FirebaseRecyclerAdapter<Products, ItemAdapterHome.HomeViewHolder> {

    Context context;

    public ItemAdapterHome(Context ct, FirebaseRecyclerOptions<Products> options) {
        super(options);
        context = ct;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_item_layout, parent, false);
        return new ItemAdapterHome.HomeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull HomeViewHolder holder, int position, @NonNull Products products) {
        holder.itemName.setText(products.getName());
        holder.itemPrice.setText("$ " + products.getPrice());
        Glide.with(context).load(products.getImage_Url()).into(holder.itemImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewItem.class);
            intent.putExtra("Name", products.getName());
            intent.putExtra("Price", products.getPrice().toString());
            intent.putExtra("Image_Url", products.getImage_Url());
            intent.putExtra("PID", products.getPID());
            context.startActivity(intent);
        });
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemName, itemPrice;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image_home);
            itemName = itemView.findViewById(R.id.item_name_home);
            itemPrice = itemView.findViewById(R.id.item_price_home);
        }
    }
}
