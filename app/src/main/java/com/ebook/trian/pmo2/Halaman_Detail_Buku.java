package com.ebook.trian.pmo2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.ebook.trian.pmo2.model.Tambah_koleksi;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Halaman_Detail_Buku extends AppCompatActivity implements View.OnClickListener {
    TextView penulis,judul,penerbit,sinopsis,rating,header_sinopsis;
    ImageView close,cover;
    ProgressBar mprogress;
    Button baca;

    LinearLayout btn_share,btn_favorite;

    Context mContext;
    InterfaceApi mApiservice;
    sharedPreferencesmanager sp;
    ProgressDialog loading;
    boolean koleksi = true;
    String idbuku = null, sjudul = null, ssinopsis = null, scover = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_detail_buku);
        judul = findViewById(R.id.tv_preview_judul);
        penerbit = findViewById(R.id.tv_preview_penerbit);
        penulis = findViewById(R.id.tv_preview_publisher);
        sinopsis = findViewById(R.id.tv_preview_sinopsis);
        cover = findViewById(R.id.iv_preview_cover);
        close = findViewById(R.id.btn_close);
        baca = findViewById(R.id.btn_preview_baca);


        btn_favorite = findViewById(R.id.ly_detail_star);

        mContext = this;
        mApiservice = UtilsApi.getApiSerivce(); // inisialisasi class Utilsapi
        //loading = ProgressDialog.show(mContext,null,"mendapatkan buku",true,false);


        detaibuku();

        close.setOnClickListener(Halaman_Detail_Buku.this);

        btn_favorite.setOnClickListener(Halaman_Detail_Buku.this);

        }
    @Override
    public void onClick(View v) {
        if (v == close){
            startActivity(new Intent(Halaman_Detail_Buku.this,Halaman_Main.class));
            finish();
        }else if(v == btn_favorite){
            loading = ProgressDialog.show(mContext, null, "menambahkan ke koleksi", true, true);


            final String st = getIntent().getExtras().getString("status");
            if (st == "" || st == null || st.length() <= 0){
                cek();
            }else {

                Toast.makeText(this,"Sudah ada ada dikoleksi",Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        }
    }
    public void cek(){
        mApiservice.cekKoleksi(idbuku,sharedPreferencesmanager.getSPId())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        loading.dismiss();
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULT = new JSONObject(response.body().string());
                                String hasi = jsonRESULT.getString("status").toString();
                                if (hasi.equals("200")){
                                    Toast.makeText(Halaman_Detail_Buku.this,"sudah ada di dftar koleksi",Toast.LENGTH_LONG).show();
                                }else {
                                    Tambah_koleksi tambah_koleksi = new Tambah_koleksi(sharedPreferencesmanager.getSPId(),
                                            idbuku,
                                            sjudul,
                                            ssinopsis,
                                            scover);


                                    tambah_koleksi.tambah_koleksi();
                                }


                            }catch (JSONException e){
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }


    private void detaibuku() {

       final String id = getIntent().getExtras().getString("idBuku");

        mApiservice.bukudetail("id",id)

                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful())
                        {
                            //loading.dismiss();
                            try {
                                JSONArray jsonObject = new JSONArray(response.body().string());
                                final String id_buku = jsonObject.getJSONObject(0).getString("id");
                                final String judul = jsonObject.getJSONObject(0).getString("judul");
                                String penulis = jsonObject.getJSONObject(0).getString("penulis");
                                String penerbit = jsonObject.getJSONObject(0).getString("penerbit");
                                final String sinopsis = jsonObject.getJSONObject(0).getString("sinopsis");
                                final String cover = jsonObject.getJSONObject(0).getString("cover");
                                final String file = jsonObject.getJSONObject(0).getString("file");
                                Halaman_Detail_Buku.this.judul.setText(judul);
                                Halaman_Detail_Buku.this.penulis.setText(penulis);
                                Halaman_Detail_Buku.this.penerbit.setText(penerbit);
                                Halaman_Detail_Buku.this.sinopsis.setText(sinopsis);

                               Halaman_Detail_Buku.this.idbuku = id_buku;
                                Halaman_Detail_Buku.this.ssinopsis = sinopsis;
                                Halaman_Detail_Buku.this.scover = cover;
                                Halaman_Detail_Buku.this.sjudul = judul;
                                Glide.with(mContext)
                                        .load("https://www.noobdev.biz/assets/uploads/buku/"+cover)
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                               // mprogress.setVisibility(View.GONE);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                               // mprogress.setVisibility(View.GONE);
                                                return false;
                                            }
                                        }).into(Halaman_Detail_Buku.this.cover);
                                baca.setVisibility(View.VISIBLE);
                                baca.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                                           startActivity(new Intent(Halaman_Detail_Buku.this,Halaman_Baca.class).putExtra("file","https://www.noobdev.biz/assets/uploads/buku/"+file));

                                    }
                                });

                            }catch (JSONException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                            t.getMessage();
                    }
                });
    }



}
