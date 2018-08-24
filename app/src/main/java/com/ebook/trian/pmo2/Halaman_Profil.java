package com.ebook.trian.pmo2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.Utils.sharedPreferencesmanager;
import com.ebook.trian.pmo2.auth.*;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Halaman_Profil extends AppCompatActivity implements View.OnClickListener {
private TextView btn_logout,langganan,nama,email,no_hp,ubah;
CardView reset,a,b,c,d,keluar;
LinearLayout about;
CircleImageView foto;
    InterfaceApi mApiservice;
    ProgressDialog loading;
    Context mContext;
    ProgressBar fotoload;
    String status = "1";
    ProgressBar pb;
    sharedPreferencesmanager p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_profil);

        about = findViewById(R.id.btn_about);
        keluar = findViewById(R.id.cv_profil_s4);
        btn_logout = findViewById(R.id.btn_profil_logout);

        pb = findViewById(R.id.pb_profil);

        nama = findViewById(R.id.txt_profil_nama);
        email = findViewById(R.id.txt_profil_email);
        no_hp = findViewById(R.id.txt_profil_nohp);
        ubah = findViewById(R.id.btn_profil_ubah);
        reset = findViewById(R.id.cv_reset);
        a = findViewById(R.id.cv_profil_2);
        b = findViewById(R.id.cv_profil_3);
        c = findViewById(R.id.cv_profil_s4);
        d = findViewById(R.id.cv_profil_1);
        sembunyi();
        mContext = this;
        mApiservice = UtilsApi.getApiSerivce(); // inisialisasi class Utilsapi

        pb.setVisibility(View.INVISIBLE);
        loading = ProgressDialog.show(mContext,null,"mendapatkan informasi user",true,true);
        detailuser();

        keluar.setOnClickListener(Halaman_Profil.this);
        ubah.setOnClickListener(Halaman_Profil.this);
        reset.setOnClickListener(Halaman_Profil.this);
        about.setOnClickListener(Halaman_Profil.this);



    }
    @Override
    public void onClick(View v) {
        if (v == keluar){
            sharedPreferencesmanager.saveSPBoolean(sharedPreferencesmanager.SP_SUDAH_LOGIN, false);
            pb.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login = new Intent(Halaman_Profil.this,login.class);
                    pb.setVisibility(View.GONE);
                    btn_logout.setVisibility(View.VISIBLE);
                    startActivity(login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            },2000);

        }else if(v == reset){
            startActivity(new Intent(mContext,Halaman_Reset_Security.class));
            finish();

        }else if(v == ubah){
            startActivity(new Intent(mContext,Halaman_Edit_Profil.class));
            finish();
        }else if(v == about){
            startActivity(new Intent(mContext,About.class));
            finish();
        }
    }
    private void sembunyi() {
        reset.setVisibility(View.INVISIBLE);
        a.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        d.setVisibility(View.INVISIBLE);
    }
    public void muncul(){
        reset.setVisibility(View.VISIBLE);
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailuser();
    }

    private void detailuser() {
        String id = sharedPreferencesmanager.getSPId();
        String kondisi = "id";
        mApiservice.profilRequest(kondisi,id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful()){
                            loading.dismiss();
                            muncul();
                            try{
                                JSONArray jsonRESULT = new JSONArray(response.body().string());





                                    String id = jsonRESULT.getJSONObject(0).getString("id").toString();
                                    String name = jsonRESULT.getJSONObject(0).getString("nama").toString();
                                    //String username = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("username");
                                    //String password = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("password");
                                    //String jk = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("jk");
                                    //String photo = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("foto").toString();
                                    String hp = jsonRESULT.getJSONObject(0).getString("no_hp").toString();
                                    String e_mail = jsonRESULT.getJSONObject(0).getString("email").toString();

                                    nama.setText(name);
                                    email.setText(e_mail);

                                   // Picasso.get()
                                    //        .load("https://www.noobdev.biz/assets/uploads/user/"+photo)
                                      //      .into(foto);

                                        no_hp.setText(hp);




                            }catch (JSONException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(mContext,"Gagal menghubungkan ke server ",Toast.LENGTH_LONG).show();
                            loading.dismiss();
                            btn_logout.setVisibility(View.VISIBLE);
                            status ="1";
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Halaman_Profil.this,Halaman_Main.class));
        loading.dismiss();
        finish();
    }


}
