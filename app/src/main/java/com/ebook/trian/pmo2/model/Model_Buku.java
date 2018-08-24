package com.ebook.trian.pmo2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Model_Buku {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("sinopsis")
    @Expose
    private String sinopsis;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("penulis")
    @Expose
    private String penulis;
    @SerializedName("penerbit")
    @Expose
    private String penerbit;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("id_pustaka")
    @Expose
    private String idPustaka;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIdPustaka() {
        return idPustaka;
    }

    public void setIdPustaka(String idPustaka) {
        this.idPustaka = idPustaka;
    }
    @Override
    public String toString() {
        return "Buku{" +
                "id ='"         +id+'\''+
                ",judul ='"      +judul+'\''+
                ",sinopsis ='"   +sinopsis+'\''+
                ",cover ='"      +cover+'\''+
                ",penulis ='"    +penulis+'\''+
                ",penerbit ='"   +penerbit+'\''+
                ",rating ='"     +rating+'\''+
                ",tahun ='"      +tahun+'\''+
                ",kategori ='"   +kategori+'\''+
                ",file ='"       +file+'\''+
                ",publisher ="   +publisher+
                '}';
    }
}
