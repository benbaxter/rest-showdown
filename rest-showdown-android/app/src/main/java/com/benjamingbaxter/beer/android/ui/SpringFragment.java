package com.benjamingbaxter.beer.android.ui;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.benjamingbaxter.beer.android.ws.BeerApi;
import com.benjamingbaxter.beer.model.Beer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpringFragment extends BeerFragment {

    public static Fragment newInstance() {
        return new SpringFragment();
    }

    @Override
    public void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, List<Beer>>() {

            @Override
            protected List<Beer> doInBackground(Void... params) {
                RestTemplate template = new RestTemplate();

                ResponseEntity<Beer[]> reponse = template.getForEntity(BeerApi.BASE_URL + "beers", Beer[].class);
                if( reponse.getStatusCode().is2xxSuccessful() ) {
                    return Arrays.asList(reponse.getBody());
                }
                return Collections.emptyList();
            }

            @Override
            protected void onPostExecute(List<Beer> beers) {
                adapter.setBeers(beers);
            }
        }.execute();
    }
}
