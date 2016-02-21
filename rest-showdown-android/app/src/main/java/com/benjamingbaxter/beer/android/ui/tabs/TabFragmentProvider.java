package com.benjamingbaxter.beer.android.ui.tabs;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

public interface TabFragmentProvider {

    Fragment provide(int position);
    String getTitle(int position);
    @DrawableRes int getIcon(int position);
    int getCount();
}
