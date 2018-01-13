package com.example.xyz.demoappretrofit.demoRetrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xyz.demoappretrofit.R;
import com.example.xyz.demoappretrofit.RetroFitApplication;
import com.example.xyz.demoappretrofit.adapter.MyClientRecyclerViewAdapterNew;
import com.example.xyz.demoappretrofit.bean.Client;
import com.example.xyz.demoappretrofit.bean.ClientData;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientList extends AppCompatActivity {
    TextView empty_msg;
    private MyClientRecyclerViewAdapterNew clientListAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Client clientResponceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        empty_msg = (TextView) findViewById(R.id.empty_client);
        //  empty_msg.setTypeface(tool);
        mRecyclerView = (RecyclerView) findViewById(R.id.myclient_recycler_view);

        loadJSONResponse();

    }


    private void loadJSONResponse() {
        Log.d("mWebservice", "" + String.valueOf(RetroFitApplication.getWebservice()));
        RetroFitApplication.getWebservice().updateChatStatus().enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Log.d("retrofilt success", "" + response.body());
                if (response.body() != null) {
                    clientResponceData = response.body();
//                    Gson gson = new Gson();
//                    String body = gson.toJson(response.body());
                    Log.d("retrofilt success2", "clientData" + clientResponceData.getResponse());
                    if (clientResponceData.getResponse() != null) {
                        initRV();
                    }
                } else {
// Empty Client List
                    Toast.makeText(ClientList.this, "Empty List", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.d("retrofilt error", "" + t);
                Toast.makeText(ClientList.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRV() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        clientListAdapter = new MyClientRecyclerViewAdapterNew(getApplicationContext(), clientResponceData.getResponse());
        mRecyclerView.setAdapter(clientListAdapter);

    }

/*
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        };
*/

}
