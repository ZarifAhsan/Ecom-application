package com.example.ecom_application.recycler;

import android.annotation.SuppressLint;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ItemAdapter extends FirebaseRecyclerAdapter<Products, ItemAdapter.ViewHolder> {

    public ItemAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Products products) {
        holder.itemName.setText("" + products.getName());
        holder.itemPrice.setText("$" + products.getPrice());
        Glide.with(holder.itemImage.getContext()).load(products.getImage_Url()).into(holder.itemImage);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemName, itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
