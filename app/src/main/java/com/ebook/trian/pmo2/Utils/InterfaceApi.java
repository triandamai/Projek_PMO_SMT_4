package com.ebook.trian.pmo2.Utils;

import com.ebook.trian.pmo2.model.*;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by trian
 */

public interface InterfaceApi {

    @FormUrlEncoded
    @POST("Auth/login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String psddword);
    @FormUrlEncoded
    @POST("Auth/register")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("username") String username,
                                       @Field("password") String password,
                                       @Field("email") String email);
    @FormUrlEncoded
    @POST("Koleksi/cek")
    Call<ResponseBody> cekKoleksi(@Field("id_buku") String buku,
                                  @Field("id_user") String user);

    @FormUrlEncoded
    @POST("akun/kondisi")
    Call<ResponseBody> profilRequest(@Field("kondisi") String kondisi,
                                     @Field("value") String value);

    @FormUrlEncoded
    @POST("buku/kondisi")
    Call<ResponseBody> bukudetail(@Field("kondisi") String kondisi,
                                  @Field("value") String value);

    @FormUrlEncoded
    @POST("Akun/update")
    Call<ResponseBody> editprofilRequest(@Field("id") String id,
                                         @Field("nama") String nama,
                                         @Field("email") String email,
                                         @Field("jk")String jenis_kelamin,
                                         @Field("no_hp") String no_hp);
    @FormUrlEncoded
    @POST("koleksi/tambah")
    Call<ResponseBody> tambahKoleksi(
                                     @Field("id_user") String user,
                                     @Field("id_buku") String idbuku,
                                     @Field("judul") String judul,
                                     @Field("sinopsis") String sinopsis,
                                     @Field("cover") String cover);

    @FormUrlEncoded
    @POST("koleksi/delete")
    Call<List<Hapus_response>> postHapus(@Field("id") String id);

    @FormUrlEncoded
    @POST("buku/kondisi")
    Call<List<Model_Buku>> getBuku(@Field("kondisi")String kategori,
                                   @Field("value")String value);

    @FormUrlEncoded
    @POST("buku/kondisi")
    Call<List<Model_Buku_pustaka>> getBukuByPustaka(@Field("kondisi") String kondisi,
                                                    @Field("value") String value);

    @GET("koleksi")
    Call<List<Model_Koleksi>> getKoleksi(@Query("id_user") String id);

    @GET("berita")
    Call<List<Model_Berita>> getBerita();

    @FormUrlEncoded
    @POST("Akun/kondisi")
    Call<List<Model_Pustaka>> getPustaka(@Field("kondisi") String kondisi,
                                         @Field("value") String value);

    @GET("buku/cari")
    Call<List<Model_Buku_Cari>> getBukuCari(@Query("judul") CharSequence judul,
                                            @Query("penerbit") String penerbit);


}