package com.ebook.trian.pmo2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Koleksi {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_buku")
    @Expose
    private String idBuku;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("sinopsis")
    @Expose
    private String sinopsis;
    @SerializedName("cover")
    @Expose
    private String cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
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
