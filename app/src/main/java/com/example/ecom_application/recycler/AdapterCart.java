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

import com.bumptech.glide.Glide;
import com.example.ecom_application.R;
import com.example.ecom_application.data.Products;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartViewHolder> {

    private final Context context;
    ArrayList<Products> product;

    public AdapterCart(Context ct, ArrayList<Products> products) {
        context = ct;
        product =  products;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new AdapterCart.CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Products singleProduct = product.get(position);

        holder.productName.setText(singleProduct.getName());
        holder.productPrice.setText("$" + singleProduct.getPrice());
        Glide.with(context).load(singleProduct.getImage_Url()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgView;
        private final TextView productName;
        private final TextView productPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.item_image);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
