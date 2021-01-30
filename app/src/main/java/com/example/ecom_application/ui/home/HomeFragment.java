package com.example.ecom_application.ui.home;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ecom_application.R;
import com.example.ecom_application.data.Products;
import com.example.ecom_application.recycler.ItemAdapter;
import com.example.ecom_application.recycler.ItemAdapterHome;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView1, recyclerView2;

    TextView noInternet;

    ItemAdapterHome itemAdapterHome1, itemAdapterHome2;

    ConnectivityManager connMgr;

    NetworkInfo networkInfo;

    ScrollView layout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        recyclerView1 = view.findViewById(R.id.recycler_home_1);
        recyclerView2 = view.findViewById(R.id.recycler_home_2);

        noInternet = view.findViewById(R.id.internet_connectivity1);

        connMgr = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();

        layout = view.findViewById(R.id.layout_home);

        checkNetworkConnectivity();

        return view;
    }

    private void checkNetworkConnectivity() {
        if (networkInfo != null && networkInfo.isConnected()) {
            initLoader();
            itemAdapterHome1.startListening();
            itemAdapterHome2.startListening();
        } else {
            noInternet.setVisibility(View.VISIBLE);
            layout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        checkNetworkConnectivity();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (itemAdapterHome1 != null) {
            itemAdapterHome1.stopListening();
        }

        if (itemAdapterHome2 != null) {
            itemAdapterHome2.stopListening();
        }
    }

    private void initLoader() {
        // First recycler view
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);

        Query query1 = FirebaseDatabase.getInstance()
                .getReference()
                .child("Products")
                .child("Recommended");

        FirebaseRecyclerOptions<Products> options1 =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(query1, Products.class)
                        .build();

        itemAdapterHome1 = new ItemAdapterHome(getContext(), options1);
        recyclerView1.setAdapter(itemAdapterHome1);



        // Second recycler view
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        Query query2 = FirebaseDatabase.getInstance()
                .getReference()
                .child("Products")
                .child("DIY");

        FirebaseRecyclerOptions<Products> options2 =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(query2, Products.class)
                        .build();

        itemAdapterHome2 = new ItemAdapterHome(getContext(), options2);
        recyclerView2.setAdapter(itemAdapterHome2);
    }
}