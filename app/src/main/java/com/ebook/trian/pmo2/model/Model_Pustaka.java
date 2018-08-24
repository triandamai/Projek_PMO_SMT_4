package com.ebook.trian.pmo2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Model_Pustaka {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("jk")
    @Expose
    private Object jk;
    @SerializedName("no_hp")
    @Expose
    private Object noHp;
    @SerializedName("foto")
    @Expose
    private Object foto;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("level")
    @Expose
    private Object level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getJk() {
        return jk;
    }

    public void setJk(Object jk) {
        this.jk = jk;
    }

    public Object getNoHp() {
        return noHp;
    }

    public void setNoHp(Object noHp) {
        this.noHp = noHp;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }
}
