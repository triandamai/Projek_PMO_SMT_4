package com.ebook.trian.pmo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Halaman_Profil.class));
        finish();
    }
}
