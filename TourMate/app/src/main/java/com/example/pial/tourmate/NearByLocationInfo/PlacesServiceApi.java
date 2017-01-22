package com.example.pial.tourmate.NearByLocationInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by BITM TRAINER-403 on 19/11/2016.
 */

public interface PlacesServiceApi {
    @GET()
    Call<NearByLocationResponse>getAllResponse(@Url String urlString);
}
