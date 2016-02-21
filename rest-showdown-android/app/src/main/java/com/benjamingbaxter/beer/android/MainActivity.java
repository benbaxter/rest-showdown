package com.benjamingbaxter.beer.android;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.benjamingbaxter.beer.android.ui.adapter.SimpleFragmentPagerAdapter;
import com.benjamingbaxter.beer.android.ui.tabs.RestExampleTabFragmentProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private SimpleFragmentPagerAdapter pagerAdapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        RestExampleTabFragmentProvider tabProvider = new RestExampleTabFragmentProvider();
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), tabProvider);

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        for(int i = 0; i < tabLayout.getTabCount(); ++i) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if( tab != null ) {
                tab.setIcon(tabProvider.getIcon(i));
                tab.setText(null);
            }
        }
    }
}
