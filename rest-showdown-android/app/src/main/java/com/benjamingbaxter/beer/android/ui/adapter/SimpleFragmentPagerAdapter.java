package com.benjamingbaxter.beer.android.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.benjamingbaxter.beer.android.ui.tabs.TabFragmentProvider;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final TabFragmentProvider provider;

    public SimpleFragmentPagerAdapter(FragmentManager fm, TabFragmentProvider provider) {
        super(fm);
        this.provider = provider;
    }

    @Override
    public Fragment getItem(int position) {
        return provider.provide(position);
    }

    @Override
    public int getCount() {
        return provider.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return provider.getTitle(position);
    }
}
