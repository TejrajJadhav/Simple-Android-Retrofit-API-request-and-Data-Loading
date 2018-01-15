package com.example.xyz.demoappretrofit.helper;

import com.example.xyz.demoappretrofit.bean.Client;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Tejraj on 12-Jan-18.
 */

public interface Webservice {
// same like ur request interface

//  get method call like this

//    @FormUrlEncoded
//    @Headers({"Content-Type: application/x-www-form-urlencoded"})
//    @POST("testing_onthefield/webservices/GetAllClientsDemoRetro.php")
//        Call<Client> updateChatStatus();

//    @GET("testing_onthefield/webservices/GetAllClientsDemoRetro.php")
//    Call<Client> updateChatStatus();


  @GET("bins/vh6w5")
  Call<Client> updateChatStatus();

}
