package com.benjamingbaxter.beer.android.ui;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.benjamingbaxter.beer.android.retrofit.HttpLoggingInterceptor;
import com.benjamingbaxter.beer.android.ws.BeerApi;
import com.squareup.okhttp.OkHttpClient;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitFragment extends BeerFragment {

    private static final String LOG_TAG = RetrofitFragment.class.getSimpleName();

    public static Fragment newInstance() {
        return new RetrofitFragment();
    }


    @Override
    public void onResume() {
        super.onResume();

        loadBeers();
    }

    private void loadBeers() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BeerApi.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create()) //you can add your own object mapper if you prefer
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        BeerApi api = retrofit.create(BeerApi.class);
        api.beerRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((beers) -> {
                            adapter.setBeers(beers);
                }, (throwable -> {
                    Log.e(LOG_TAG, "Could not retrieve beers", throwable);
                    Snackbar.make(recyclerView, "Could not access Beer Api", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Try Again", (v) -> {
                                loadBeers();
                            })
                            .show();
                }));
    }
}
