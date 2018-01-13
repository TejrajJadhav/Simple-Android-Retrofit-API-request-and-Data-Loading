package com.example.xyz.demoappretrofit.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xyz.demoappretrofit.R;
import com.example.xyz.demoappretrofit.bean.Client;
import com.example.xyz.demoappretrofit.bean.ClientData;
import com.example.xyz.demoappretrofit.demoRetrofit.ClientList;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Ashu on 25-Jan-17.
 */

public class MyClientRecyclerViewAdapterNew extends RecyclerView
        .Adapter<MyClientRecyclerViewAdapterNew
        .MyViewHolder> {

    private static MyClientRecyclerViewAdapterNew.MyClickListener myClickListener;
    Context context;
    List<ClientData> clientData;

    public MyClientRecyclerViewAdapterNew(Context context, List<ClientData> myDataset) {
        this.clientData = myDataset;
        this.context = context;
    }

    public void setOnItemClickListener(MyClientRecyclerViewAdapterNew.MyClickListener myClickListener) {
        MyClientRecyclerViewAdapterNew.myClickListener = myClickListener;
    }


    @Override
    public MyClientRecyclerViewAdapterNew.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.client_item, parent, false);
        MyClientRecyclerViewAdapterNew.MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyClientRecyclerViewAdapterNew.MyViewHolder holder, int position) {
        Log.d("data", ""+clientData.get(position));
        if (clientData.get(position).getCompany_name() != null) {
        holder.client_name.setText(clientData.get(position).getCompany_name().toUpperCase());
        }

        final String pincode = clientData.get(position).getPincode();
        if (pincode != null) {
            holder.address.setText(clientData.get(position).getAddress_line() + ", " + clientData.get(position).getCity() + ", " + clientData.get(position).getPincode());
        } else if (clientData.get(position).getAddress_line() != null && clientData.get(position).getCity() != null){
            holder.address.setText(clientData.get(position).getAddress_line() + ", " + clientData.get(position).getCity());
        }
    }

    public void addItem(ClientData dataObj, int index) {
        clientData.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        clientData.remove(index);
        notifyItemRemoved(index);
    }


    @Override
    public int getItemCount() {
        return clientData.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView client_name;
        TextView address;
        TextView upcoming;
        //LikeButton favourite_flag;


        public MyViewHolder(View itemView) {
            super(itemView);
            client_name = (TextView) itemView.findViewById(R.id.comapny_name);
            upcoming = (TextView) itemView.findViewById(R.id.upcoming);
            address = (TextView) itemView.findViewById(R.id.address);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v,upcoming);
        }
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v, TextView upcoming);
    }
}