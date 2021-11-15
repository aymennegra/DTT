package com.example.dtt_test;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dtt_test.Adapter.HomeAdapter;
import com.example.dtt_test.Entity.Data;
import com.example.dtt_test.Retrofit.INodeJS;
import com.example.dtt_test.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {

    List<Data> housesList;


    RecyclerView recyclerView;
    ImageView emptylist;
    public static INodeJS iNodeJS;
    Context mContext;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Implementing Filtred list
    private void filter (String text){
        ArrayList<Data>  filtredList = new ArrayList<>();
        for (Data item : housesList){
            if (item.getCity().toLowerCase().contains(text.toLowerCase()) || item.getZip().toLowerCase().contains(text.toLowerCase()) ){
                filtredList.add(item);
            }
        }
        HomeAdapter adapter = new HomeAdapter(mContext, filtredList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter.filterList(filtredList);
        if (filtredList.size()>0){
            emptylist.setVisibility(View.INVISIBLE);
        }else {
            emptylist.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.houses);
        emptylist = view.findViewById(R.id.emptylist);
        EditText search = view.findViewById(R.id.home_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    filter(s.toString());
            }
        });
   //Consuming Api with retrofit
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        Call<List<Data>> call = iNodeJS.getHousesList();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                housesList = response.body();
                Collections.sort(housesList,Data.Pricecompare);
                HomeAdapter adapter = new HomeAdapter(mContext, housesList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            }
            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });

        return view;
    }
}