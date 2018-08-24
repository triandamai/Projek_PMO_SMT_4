package com.ebook.trian.pmo2.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambah_koleksi {
    InterfaceApi mApiservice = UtilsApi.getApiSerivce();

    String id;
    String id_user;
    String id_buku;
    String judul;
    String sinopsis;


    public Tambah_koleksi(String id_user, String id_buku, String judul, String sinopsis, String cover) {
        this.id = id;
        this.id_user = id_user;
        this.id_buku = id_buku;
        this.judul = judul;
        this.sinopsis = sinopsis;
        this.cover = cover;
    }
    public void tambah_koleksi(){
        mApiservice.tambahKoleksi(id_user,id_buku,judul,sinopsis,cover)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.getMessage();
                    }
                });
    }
    String cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_buku() {
        return id_buku;
    }

    public void setId_buku(String id_buku) {
        this.id_buku = id_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }



}
