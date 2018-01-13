package com.example.xyz.demoappretrofit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tejraj on 12-Jan-18.
 */

public class ClientData implements Parcelable {
    public String client_id;
    public String company_name;
    public String address_line;
    public String city;
    public String pincode;
    public String state;
    public String country;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAddress_line() {
        return address_line;
    }

    public void setAddress_line(String address_line) {
        this.address_line = address_line;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    protected ClientData(Parcel in) {
        client_id = in.readString();
        company_name = in.readString();
        address_line = in.readString();
        city = in.readString();
        pincode = in.readString();
        state = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(client_id);
        dest.writeString(company_name);
        dest.writeString(address_line);
        dest.writeString(city);
        dest.writeString(pincode);
        dest.writeString(state);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClientData> CREATOR = new Creator<ClientData>() {
        @Override
        public ClientData createFromParcel(Parcel in) {
            return new ClientData(in);
        }

        @Override
        public ClientData[] newArray(int size) {
            return new ClientData[size];
        }
    };
}
