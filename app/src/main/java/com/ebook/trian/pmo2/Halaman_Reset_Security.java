package com.ebook.trian.pmo2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.Utils.sharedPreferencesmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Halaman_Reset_Security extends AppCompatActivity {
Button simpan;
EditText username_,password_,repassword;
Context mContext;
InterfaceApi mApiservice;
sharedPreferencesmanager sp;
ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_reset);

        simpan = findViewById(R.id.btn_reset_simpan);
        username_ = findViewById(R.id.txt_reset_username);
        password_ = findViewById(R.id.txt_reset_baru);
        repassword = findViewById(R.id.txt_reset_ulang);

        mContext = this;
        mApiservice = UtilsApi.getApiSerivce(); // inisialisasi class Utilsapi
        loading = ProgressDialog.show(mContext,null,"mendapatkan informasi user",true,false);

        getuser();
    }

    private void getuser() {
        String kondisi = "id";
    mApiservice.profilRequest(kondisi,sharedPreferencesmanager.getSPId())
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        loading.dismiss();
                        try {
                            JSONArray jsonRESULT = new JSONArray(response.body().string());

                            String username = jsonRESULT.getJSONObject(0).getString("username");
                            String password = jsonRESULT.getJSONObject(0).getString("password");

                                username_.setText(username);
                                password_.setText(password);



                        }catch (JSONException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(mContext,"gagal mengambil data",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(mContext,"gagal mengambil data",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            });
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("My Ebook ")
                .setMessage("Yakin mau keluar ?? perubahan sebelumnya akan dihapus")
                .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(mContext,Halaman_Profil.class));
                    }
                })
                .setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"tidak",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
