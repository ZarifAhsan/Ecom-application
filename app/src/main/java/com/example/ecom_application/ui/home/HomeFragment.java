package com.example.ecom_application.ui.home;

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
import android.widget.TextView;

import com.example.ecom_application.R;
import com.example.ecom_application.recycler.ItemAdapterHome;

import java.util.Objects;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView1, recyclerView2;

    ItemAdapterHome itemAdapterHome;

    TextView noInternet;

    ConnectivityManager connMgr;

    NetworkInfo networkInfo;

    LinearLayout linearLayout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        recyclerView1 = view.findViewById(R.id.recycler_home_1);
        recyclerView2 = view.findViewById(R.id.recycler_home_2);

        noInternet = view.findViewById(R.id.internet_connectivity1);

        connMgr = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();

        linearLayout = view.findViewById(R.id.contents);

        int[] images = {R.drawable.diy_paint_bq, R.drawable.diy_paint_brush, R.drawable.diywallpaper,
                R.drawable.sealant};

        String[] productName = getResources().getStringArray(R.array.Diy_products);
        String[] productPrice = getResources().getStringArray(R.array.Diy_price);

        itemAdapterHome = new ItemAdapterHome(getContext(), productName, productPrice, images);

        checkNetworkConnectivity();

        return view;
    }

    private void checkNetworkConnectivity() {
        if (networkInfo != null && networkInfo.isConnected()) {
            initLoader();
        } else {
            noInternet.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void initLoader() {
        // First recycler view
        recyclerView1.setAdapter(itemAdapterHome);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);

        // Second recycler view
        recyclerView2.setAdapter(itemAdapterHome);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
    }
}