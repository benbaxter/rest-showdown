package com.benjamingbaxter.beer.android.ui.tabs;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.benjamingbaxter.beer.android.MainActivity;
import com.benjamingbaxter.beer.android.R;
import com.benjamingbaxter.beer.android.ui.RetrofitFragment;
import com.benjamingbaxter.beer.android.ui.SpringFragment;

public class RestExampleTabFragmentProvider implements TabFragmentProvider {

    enum Tab {
        REST_TEMPLATE("Rest Template", R.drawable.ic_spring_logo, () -> SpringFragment.newInstance()),
        RETROFIT("Retrofit", R.drawable.ic_square_logo, () -> RetrofitFragment.newInstance());

        private String title;
        private @DrawableRes int icon;
        private Provider provider;

        private Tab(String title, @DrawableRes int icon, Provider provider) {
            this.title = title;
            this.icon = icon;
            this.provider = provider;
        }

        public Fragment provide() {
            return provider.provide();
        }
    }

    interface Provider {
        Fragment provide();
    }

    @Override
    public Fragment provide(int position) {
        return Tab.values()[position].provide();
    }

    @Override
    public String getTitle(int position) {
        return Tab.values()[position].title;
    }

    @Override
    public @DrawableRes int getIcon(int position) {
        return Tab.values()[position].icon;
    }

    @Override
    public int getCount() {
        return Tab.values().length;
    }
}
