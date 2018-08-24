package com.ebook.trian.pmo2;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ebook.trian.pmo2.adapter.AdapterTabLayoutMain;

public class Halaman_Main extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ImageView search;
    TabLayout tabLayout;
    ViewPager viewPager;
    AdapterTabLayoutMain adapterTabLayoutMain;
    TabItem tabhome;
    TabItem tabepustaka;
    TabItem tabkoleksi;
    TabItem tabprofil;
    TabItem tabtimeline;

    LinearLayout btn_profil,btn_back,btn_search;

    ImageView iv_toolbar,iv_profil;
    TextView tv_toolbar;
    boolean klik =false;

    @Override
    public void onBackPressed() {

        if(klik==false){
            Toast.makeText(this, "Klik satu kali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    klik= true;
                }
            },1000);
        }else {
        finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_main);
        //toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);


        btn_profil = findViewById(R.id.btn_tab_toprofil);
        tv_toolbar = findViewById(R.id.tv_maintab_judul_toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabhome = findViewById(R.id.tabHome);
        //tabtimeline = findViewById(R.id.tabTimeline);
        tabepustaka = findViewById(R.id.tabEpustaka);
        tabkoleksi = findViewById(R.id.tabKoleksi);
        viewPager = findViewById(R.id.viewPager);
        tv_toolbar.setText("Kategori");


        btn_profil.setOnClickListener(Halaman_Main.this);



        adapterTabLayoutMain = new AdapterTabLayoutMain(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterTabLayoutMain);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


                if(tab.getPosition()==0){

                    //tab.setIcon(R.drawable.ic_buku_tosca);
                    tv_toolbar.setText("Kategori");

                }else if(tab.getPosition()==1){
                    tv_toolbar.setText("ePustaka");
                    //tab.setIcon(R.drawable.ic_notif_tosca);

                }else if (tab.getPosition()==2){
                    tv_toolbar.setText("Koleksi");
                    //tab.setIcon(R.drawable.ic_store_tosca);

                }else if(tab.getPosition()==3){
                    tv_toolbar.setText("Koleksi");
                    //tab.setIcon(R.drawable.ic_koleksi_tosca);

                }else {
                    tv_toolbar.setText("Kategori");
                    //tab.setIcon(R.drawable.ic_buku_tosca);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0){

                    //tab.setIcon(R.drawable.ic_buku_grey);

                }else if(tab.getPosition()==1){

                    //tab.setIcon(R.drawable.ic_notif_grey);
                }else if (tab.getPosition()==2){
                    //tab.setIcon(R.drawable.ic_store_grey);

                }else if(tab.getPosition()==3){

                    //tab.setIcon(R.drawable.ic_koleksi_grey);

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0){

                    //tab.setIcon(R.drawable.ic_buku_tosca);

                }else if(tab.getPosition()==1){
                    //tab.setIcon(R.drawable.ic_notif_tosca);

                }else if (tab.getPosition()==2){
                    //tab.setIcon(R.drawable.ic_store_tosca);

                }else if(tab.getPosition()==3){

                    //tab.setIcon(R.drawable.ic_koleksi_tosca);
                    //tv_toolbar.setText("Koleksiku");
                }
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

}

    @Override
    public void onClick(View v) {
        if(v == btn_profil){
            startActivity(new Intent(Halaman_Main.this,Halaman_Profil.class));
            finish();
        }
    }






}
