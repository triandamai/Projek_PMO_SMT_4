package com.ebook.trian.pmo2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ebook.trian.pmo2.auth.login;

public class Halaman_Splash_Screen extends AppCompatActivity {
ImageView logo;
TextView tidakada,upps;
ProgressBar loading;
    boolean connected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_splash_screen);
        logo = findViewById(R.id.iv_splash);
        loading = findViewById(R.id.pb_splash);
        tidakada = findViewById(R.id.tv_splash);
        tidakada.setVisibility(View.INVISIBLE);
        upps = findViewById(R.id.tv_upps);
        upps.setText("My Ebook");
        cek();

    }

    private void klik() {
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                logo.setImageResource(R.drawable.ic_logo);
                tidakada.setVisibility(View.GONE);
                upps.setText("My Ebook");
                cek();
            }
        });

        tidakada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                logo.setImageResource(R.drawable.ic_logo);
                tidakada.setVisibility(View.GONE);
                upps.setText("My Ebook");
                cek();
            }
        });
    }

    private void cek() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                                          if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED||
                                                  connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
                                              loading.setVisibility(View.GONE);
                                              startActivity( new Intent(Halaman_Splash_Screen.this,login.class));
                                              finish();
                                              connected = true;
                                          }else {
                                              loading.setVisibility(View.GONE);
                                              logo.setImageResource(R.drawable.no_wifi);
                                              upps.setText("Uuups.!");
                                              tidakada.setVisibility(View.VISIBLE);
                                              connected = false;
                                              klik();
                                          }
                                      }
                                  },2000
        );

    }
}
