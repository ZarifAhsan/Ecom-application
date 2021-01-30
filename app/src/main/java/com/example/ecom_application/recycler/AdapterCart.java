package com.example.ecom_application.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        View view = inflater.inflate(R.layout.item_layout_cart, parent, false);
        return new AdapterCart.CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Products singleProduct = product.get(position);

        holder.productName.setText(singleProduct.getName());
        holder.productPriceAndQuantity.setText("$ " + singleProduct.getPrice() + " x " + "1");
        Glide.with(context).load(singleProduct.getImage_Url()).into(holder.imgView);

        holder.increment.setOnClickListener(v -> {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
            int i = Integer.parseInt(holder.quantity.getText().toString());
            holder.quantity.setText(i++ + "");
        });

        holder.decrement.setOnClickListener(v -> {
            int d = Integer.parseInt(holder.quantity.getText().toString());
            if (d > 1) {
                holder.quantity.setText(d-- + "");
            } else {
                Toast.makeText(context, "Quantity can't be less than 1", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgView;
        private final TextView productName;
        private final TextView productPriceAndQuantity;
        private final TextView productPriceTotal;
        private final Button increment;
        private final Button decrement;
        private final TextView quantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPriceAndQuantity = itemView.findViewById(R.id.product_price_and_quantity);
            productPriceTotal = itemView.findViewById(R.id.product_price_total);
            increment = itemView.findViewById(R.id.increment);
            decrement = itemView.findViewById(R.id.decrement);
            quantity = itemView.findViewById(R.id.quantity_text_view);
        }
    }
}
