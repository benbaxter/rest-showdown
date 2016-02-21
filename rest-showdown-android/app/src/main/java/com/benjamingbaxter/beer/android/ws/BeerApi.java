package com.benjamingbaxter.beer.android.ws;

import com.benjamingbaxter.beer.model.Beer;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import rx.Observable;

public interface BeerApi {

    String BASE_URL = "http://ontariobeerapi.ca:80/";

    @GET("beers")
    List<Beer> beer();

    @GET("beers")
    Call<List<Beer>> beerAsync();

    @GET("beers")
    Observable<List<Beer>> beerRx();

    @GET("beers")
    @Headers({"Content-Type: application/json"})
    List<Beer> beer(@Header("client-api-key") String apiKey);
}