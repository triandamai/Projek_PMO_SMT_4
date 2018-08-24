package com.ebook.trian.pmo2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Model_Berita {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("notif")
    @Expose
    private String notif;
    @SerializedName("id_buku")
    @Expose
    private String idBuku;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("penulis")
    @Expose
    private String penulis;

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public Object getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    @Override
    public String toString() {
        return "Berita{" +
                "id ='"             +id+'\''+
                ",id_user ='"       +idUser+'\''+
                ",nama ='"          +nama+'\''+
                ",foto ='"          +foto+'\''+
                ",penulis ='"       +penulis+'\''+
                ",notif ='"         +notif+'\''+
                ",id_buku ='"       +idBuku+'\''+
                ",cover ='"         +cover+'\''+
                ",judul ='"         +judul+'\''+
                ",penulis ="        +penulis+
                '}';
    }


}
