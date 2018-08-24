package com.ebook.trian.pmo2.halaman_pustaka;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.adapter.AdapterTabLayoutPenerbit;

public class Halaman_Pustaka extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    AdapterTabLayoutPenerbit adapterTabLayoutPenerbit;
    TabItem tabprofil;
    TabItem tabkoleksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_pustaka);

        tabLayout = findViewById(R.id.tablayout);
        tabprofil = findViewById(R.id.tabprofil);
        tabkoleksi = findViewById(R.id.tabKoleksi);
        viewPager = findViewById(R.id.viewPager);

        adapterTabLayoutPenerbit = new AdapterTabLayoutPenerbit(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterTabLayoutPenerbit);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
