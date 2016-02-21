package com.benjamingbaxter.beer.controller;

import com.benjamingbaxter.beer.model.Beer;
import com.benjamingbaxter.beer.ws.BeerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import retrofit.*;

import java.util.List;


@RestController
public class BeerController {

    private static final String BASE_URL = "http://ontariobeerapi.ca:80/";
    private static final String API_KEY = "123456";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BeerApi beerApi;

    @RequestMapping("beer-template")
    public ResponseEntity<Beer[]> beerWithTemplate() {
        return restTemplate.getForEntity(BASE_URL + "beers/", Beer[].class);
    }

    @RequestMapping("beer-template-headers")
    public ResponseEntity<Beer[]> beerWithTemplateAndHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("client-api-key", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<Object>(null, headers);

        ResponseEntity<Beer[]> response = restTemplate.exchange(BASE_URL + "beers", HttpMethod.GET, entity, Beer[].class);
        if( response.getStatusCode().is4xxClientError() ) {
            //try to recover
        } else if ( response.getStatusCode().is5xxServerError() ) {
            //cry, cry deeply
        }
        return response;
    }

    @RequestMapping("beer-retrofit")
    public List<Beer> beerWithRetrofit() {
        return beerApi.beer();
    }

    @RequestMapping("beer-retrofit-rx")
    public List<Beer> beerWithRetrofitRx() {
        return beerApi.beerRx().toBlocking().first();
    }

    @RequestMapping("beer-retrofit-headers")
    public List<Beer> beerWithRetrofitHeaders() {
        return beerApi.beer(API_KEY);
    }

    @RequestMapping("beer-retrofit-async")
    public String beerWithRetrofitAsync() {

        Call<List<Beer>> call = beerApi.beerAsync();
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Response<List<Beer>> response, Retrofit retrofit) {
                if( response.isSuccess() ) {
                    //save beers to database
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //the network call failed, time to recover?
            }
        });
        return "queued";
    }
}
