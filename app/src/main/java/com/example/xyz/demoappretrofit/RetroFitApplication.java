package com.example.xyz.demoappretrofit;

import android.app.Application;
import android.util.Base64;

import com.example.xyz.demoappretrofit.helper.Constants;
import com.example.xyz.demoappretrofit.helper.Webservice;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tejraj on 12-Jan-18.
 * this file runs on app start before app launch
 * also add name= .Application in manifest
 */

public class RetroFitApplication extends Application {
    private static Webservice mWebservice;
    private static RetroFitApplication instance;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //network code start
        //init http logger
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // init client
        client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

/*                      For the Authentication API calls we can use
                        where Util.getUID() = "admin"   &   Util.getApiIntermediate() = ":"   &   Util.getPwd(getApplicationContext() = "Your Password"
                        Util.getAuthKeyword() = "Authorization"   &   Constants.API_AUTH_TYPE = "Basic"   &    auth*/

//                          String auth = null;
//                        String value = Util.getUID() + Util.getApiIntermediate() + Util.getPwd(getApplicationContext());
//                        try {
//                            auth = Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
//                        } catch (UnsupportedEncodingException ignored) {}
//                        Request request2 = request.newBuilder().addHeader(Util.getAuthKeyword(), Constants.API_AUTH_TYPE + " " + auth).build();

                        Request request2 = request.newBuilder().build();
                        return chain.proceed(request2);
                    }
                }).connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mWebservice = mRetrofit.create(Webservice.class);
        //network code end
    }

    public static Webservice getWebservice() {
        return mWebservice;
    }

}
