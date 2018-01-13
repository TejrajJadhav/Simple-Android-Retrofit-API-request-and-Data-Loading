package com.example.xyz.demoappretrofit.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ABC on 11/04/2017.
 */

public class Client implements Parcelable {
    public List<ClientData> response = null;

    public List<ClientData> getResponse() {
        return response;
    }

    public void setResponse(List<ClientData> response) {
        this.response = response;
    }

    protected Client(Parcel in) {
        response = in.createTypedArrayList(ClientData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(response);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}