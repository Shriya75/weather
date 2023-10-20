package com.farmwiseai.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherapi {
    @GET("forecast")
    Call<WeatherForecast> getWeatherForecast(
            @Query("q") String cityName,
            @Query("cnt") int days, // Add this parameter
            @Query("appid") String apiKey
    );
}
