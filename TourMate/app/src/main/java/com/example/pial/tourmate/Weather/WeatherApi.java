package com.example.pial.tourmate.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by BITM TRAINER-403 on 20/11/2016.
 */


public interface WeatherApi {
    @GET
    Call<WeatherResponse>getAllWeatherData(@Url String url);
}
