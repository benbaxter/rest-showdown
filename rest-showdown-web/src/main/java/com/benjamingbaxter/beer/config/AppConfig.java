package com.benjamingbaxter.beer.config;

import com.benjamingbaxter.beer.retrofit.HttpLoggingInterceptor;
import com.benjamingbaxter.beer.ws.BeerApi;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import retrofit.GsonConverterFactory;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OkHttpClient httpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(logging);

        return httpClient;
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {

        return new Retrofit.Builder()
                .baseUrl(BeerApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()) //if you include the gson coverter module
                .addConverterFactory(JacksonConverterFactory.create()) //you can add your own object mapper if you prefer
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Bean
    public BeerApi beerApi(Retrofit retrofit) {
        return retrofit.create(BeerApi.class);
    }
}
