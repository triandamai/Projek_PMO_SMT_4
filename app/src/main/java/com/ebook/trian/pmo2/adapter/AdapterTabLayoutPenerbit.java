package com.ebook.trian.pmo2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebook.trian.pmo2.halaman_pustaka.Pustaka_koleksi;
import com.ebook.trian.pmo2.halaman_pustaka.Pustaka_profil;

//packagefragment

/**
 * Created by trian on
 */

public class AdapterTabLayoutPenerbit extends FragmentPagerAdapter {

    private int numOfTabs;

    public AdapterTabLayoutPenerbit(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Pustaka_koleksi();
            case 1:
                return new Pustaka_profil();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
