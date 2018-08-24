package com.ebook.trian.pmo2.RetrofitClient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created  on 22-Mar-17.
 */

public class RetroFitApiClient {

    private static Retrofit retrofit=null;





    public static Retrofit getClient(String baseUrl){
        //CertificatePinner certificatePinner = new CertificatePinner.Builder().add("noobdev.biz","cca52_8f791_fe2325e4237ed4bcfc0348f60c942f4c").build();



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
        //.certificatePinner(certificatePinner)
        .build();


        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
