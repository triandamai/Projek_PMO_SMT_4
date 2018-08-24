package com.ebook.trian.pmo2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//packagefragment
import com.ebook.trian.pmo2.halaman_fragment.fragment_koleksi;
import com.ebook.trian.pmo2.halaman_fragment.fragment_home;
import com.ebook.trian.pmo2.halaman_fragment.fragment_news;
import com.ebook.trian.pmo2.halaman_fragment.fragment_pustaka;

/**
 *
 */

public class AdapterTabLayoutMain extends FragmentPagerAdapter {

    private int numOfTabs;
    FragmentManager f;

    public AdapterTabLayoutMain(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new fragment_home().newInstance();
            //case 1:
                //return new fragment_news().newInstance();
            case 1:
                return new fragment_pustaka().newInstance();
            case 2:
                return new fragment_koleksi().newInstance();
            default:
                return new fragment_home().newInstance();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
