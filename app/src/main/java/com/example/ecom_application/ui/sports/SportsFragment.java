package com.example.ecom_application.ui.sports;

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
import android.widget.TextView;

import com.example.ecom_application.R;
import com.example.ecom_application.data.Products;
import com.example.ecom_application.recycler.ItemAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class SportsFragment extends Fragment {

    RecyclerView recyclerView;

    ItemAdapter itemAdapter;

    TextView noInternet;

    ConnectivityManager connMgr;

    NetworkInfo networkInfo;

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler);

        noInternet = view.findViewById(R.id.internet_connectivity);

        connMgr = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();

        checkNetworkConnectivity();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkNetworkConnectivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        checkNetworkConnectivity();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (itemAdapter != null) {
            itemAdapter.stopListening();
        }
    }

    private void checkNetworkConnectivity() {
        if (networkInfo != null && networkInfo.isConnected()) {
            initLoader();
            itemAdapter.startListening();
        } else
            noInternet.setVisibility(View.VISIBLE);
    }

    private void initLoader() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Products")
                .child("Sports");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(query, Products.class)
                        .build();

        itemAdapter = new ItemAdapter(getContext(), options);
        recyclerView.setAdapter(itemAdapter);
    }

}