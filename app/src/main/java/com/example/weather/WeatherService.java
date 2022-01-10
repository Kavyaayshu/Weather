package com.example.weather;

import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

   //String  BASE_URL = "api.openweathermap.org/data/2.5/weather?lat=35&lon=139";

     String BaseUrl = "https://api.openweathermap.org/data/2.5/";
     String AppId = "84422c5f2b51c9fcde79fbd14842d291";


     @GET("weather")
     Call<WeatherResponse> getCurrentWeatherData(@Query("q") String city,
                                                @Query("appid") String app_id);
    //@GET("weather")
  //  Single<WeatherResponse> getCurrentWeather(
   //         @Query("q") String q,
  //          @Query("units") String units,
  //          @Query("lang") String lang,
  //          @Query("appid") String appId
 //   );


   // @GET("2.5/weather")
  //  Call<WeatherResponse> getWeather(@Query("lat") double lat,
                             //    @Query("lon") double lon,
                            //     @Query("units") String units,
                            //     @Query("appid") String appid);
}