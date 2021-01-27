package com.example.ecom_application.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecom_application.R;

public class ItemAdapterHome extends RecyclerView.Adapter<ItemAdapterHome.HomeViewHolder> {

    String[] mName, mPrice;
    int[] mImage;
    Context context;

    public ItemAdapterHome(Context ct, String[] name, String[] price, int[] image) {
        context = ct;
        mName = name;
        mPrice = price;
        mImage = image;
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
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.itemName.setText(mName[position]);
        holder.itemPrice.setText("$" + mPrice[position]);
        holder.itemImage.setImageResource(mImage[position]);
    }

    @Override
    public int getItemCount() {
        return mName.length;
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
