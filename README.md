# Simple-Retrofit-API-request-and-Data-Loading 

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Here I just add the project where create the API call to access data from database using retrofit library; which is leading library to access data on network. And display the accessed data in the List format..

# Flow

  - Create the Simple Android Studio Project with Empty Activity.
  - Create the Adapter and activity item to show normal lists in android app.
  - Now Create the App class extending Application, as Application class is a singleton that you can access from any activity or anywhere else you have a Context object.

> You can check the more details about Application class from   
> https://github.com/codepath/android_guides/wiki/Understanding-the-Android-Application-Class
> https://stackoverflow.com/questions/18002227/why-extend-an-application-class
> https://developer.android.com/reference/android/app/Application.html

- Add android:name=".YourApplication" i.e. class name extending the Application class in android. and class will be like public class YourApplication extends Application
- Init the Retrofit in Application class

```sh
 //network code start
 //init http logger
httpLoggingInterceptor = new HttpLoggingInterceptor();
httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

 // init client  
 client = new OkHttpClient.Builder() 
 .addInterceptor(httpLoggingInterceptor)  
 .addInterceptor(new Interceptor() {  
    @Override  public Response intercept(Chain chain) throws IOException 
    {    
        Request request = chain.request();  
        Request request2 = request.newBuilder().build();  
        return chain.proceed(request2);  
    }  
 })
 .connectTimeout(30, TimeUnit.SECONDS)  
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
```

- While Constants.API_BASE_URL is base url  Create the Webervice.class where you can call the API with parameters e.g. In case of GET Method:
> @GET("webservices/GetAllClientsDemoRetro.php") Call updateChatStatus(); 

- In case of POST method:
> @FormUrlEncoded  
@Headers({"Content-Type: application/x-www-form-urlencoded"})  @POST("webservices/GetAllClientsDemoRetro.php")  
Call updateChatStatus();   

-  You can See the more in details About Retrofit on Official API declaration here: http://square.github.io/retrofit/  We can parse the values with POJO i.e. Setter and Getter, using the Parceble class. Since parsing key name should be equal to the value we are receiving from the JSON response. POJO class should be declared like
> public class ClientData implements Parcelable {

- then declare the keys in the class, key values means 

```sh
public class ClientData implements Parcelable {  
    public String client_id;  
    public String company_name; 
    public String address_line;  
    public String city;  
    public String pincode;  
    public String state;  
    public String country; 
} 
```

- Now using Alt+Enter i.e. select the option Add Parceble Implementation and press enter. Then automatically parceble class will be added. 
- Also you have to add Setter and Getter method in class using Alt + Insert. 
>Note: Don’t add the Setter and Getter methods for CREATER: Creater<> method If you want to use different key that JSON response key, then you should use Serialization. 

-  When I was using same key then its is like public String client_id; But when I am using the Serialization, then I can use like 

> @Serializattion(“client_id”) 
public String ClientID;  

- Now last but not a list, We call the API using retrofit, and use the response to view the Item in list-

```sh
RetroFitApplication.getWebservice().updateChatStatus().enqueue(new Callback(){ 
    @Override  public void onResponse(Call call, Response response) { 
    Log.d("retrofilt success", "" + response.body());  
    if (response.body() != null) {  
        clientResponceData = response.body();  
        Gson gson = new Gson();  
        String body = gson.toJson(response.body());  
        Log.d("retrofilt success2", "clientData" + clientResponceData.getResponse()); 
        if (clientResponceData.getResponse() != null) {  
        initRV();  
        }  
    } else { 
    // Empty Client List  
        Toast.makeText(ClientList.this, "Empty List", Toast.LENGTH_SHORT).show();  
    }          
}          

@Override public void onFailure(Call call, Throwable t) { 
    Log.d("retrofilt error", "" + t); 
    Toast.makeText(ClientList.this, "No Internet Connection", Toast.LENGTH_SHORT).show(); 
    } 
});  
```

- By using the Construction in Adapter, we can use the values from the response. - Guys I added this repository to get the Entire idea of calling the API and get the response from server using the Retrofit Library. I write this entire documents in details with simple word.
