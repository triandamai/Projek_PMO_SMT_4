package com.ebook.trian.pmo2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class Halaman_Edit_Profil extends AppCompatActivity  {
EditText nama,email,password,username,foto,j_k,h_p;
Button simpan;

Context mContext;
InterfaceApi mApiservice;
sharedPreferencesmanager sp;
ProgressDialog loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_edit_profil);

        nama = findViewById(R.id.txt_edit_nama);
        email = findViewById(R.id.txt_edit_email);
        h_p = findViewById(R.id.txt_edit_hp);
        j_k = findViewById(R.id.txt_edit_jk);
        simpan = findViewById(R.id.btn_edit_simpan);

        mContext = this;
        mApiservice = UtilsApi.getApiSerivce(); // inisialisasi class Utilsapi
        loading = ProgressDialog.show(mContext,null,"mendapatkan informasi user",true,false);
        edituser();


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanuser();
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


    private void simpanuser() {

            loading = ProgressDialog.show(mContext,null,"harap tunggu..",true,false);

            mApiservice.editprofilRequest(sharedPreferencesmanager.getSPId(),
                    nama.getText().toString(),
                    email.getText().toString(),
                    j_k.getText().toString(),
                    h_p.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                loading.dismiss();
                                try{
                                    JSONObject jsonRESULT= new JSONObject(response.body().string());
                                    String hasil = jsonRESULT.getString("hasil");
                                    if (hasil.equals("200")){
                                        Toast.makeText(mContext,"berhasil update data"+h_p.getText(),Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(mContext,Halaman_Profil.class));
                                        finish();
                                    }else {
                                        Toast.makeText(mContext,"gagal",Toast.LENGTH_SHORT).show();
                                    }

                                }catch (JSONException e){
                                    e.printStackTrace();

                                }catch (IOException e){
                                    e.printStackTrace();

                                }
                            }else {
                                Toast.makeText(mContext,"server eror",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(mContext,"gagal koneksi server",Toast.LENGTH_SHORT).show();
                        }
                    });

    }


    private void edituser() {
        String id = sharedPreferencesmanager.getSPId();
        String kondisi = "id";
        mApiservice.profilRequest(kondisi,id)
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    loading.dismiss();
                    if(response.isSuccessful()){
                        try {

                            JSONArray jsonRESULT= new JSONArray(response.body().string());

                                //String id = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("id");
                                String name = jsonRESULT.getJSONObject(0).getString("nama");
                                //String username = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("username");
                                //String password = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("password");
                                String jk = jsonRESULT.getJSONObject(0).getString("jk");
                                //String photo = jsonRESULT.getJSONArray("data").getJSONObject(0).getString("foto");
                                String hp = jsonRESULT.getJSONObject(0).getString("no_hp");
                                String e_mail = jsonRESULT.getJSONObject(0).getString("email");
                                Toast.makeText(mContext,"berhasil",Toast.LENGTH_SHORT).show();
                                h_p.setText(hp);
                                loading.dismiss();
                                nama.setText(name);
                                email.setText(e_mail);
                                j_k.setText(jk);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }else {
                        loading.dismiss();
                        Toast.makeText(mContext,"gagal mendapatkan server",Toast.LENGTH_SHORT).show();
                        bersiheditex();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(mContext,"gagal terhubung",Toast.LENGTH_SHORT).show();

                    bersiheditex();
                }
            });
    }

    private void bersiheditex() {
        nama.setText("");
        email.setText("");
        j_k.setText("");
        h_p.setText("");
    }


}
