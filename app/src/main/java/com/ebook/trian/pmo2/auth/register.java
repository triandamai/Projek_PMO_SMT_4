package com.ebook.trian.pmo2.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
Button btn_login,btn_register;
EditText nama,username,password,email;

    ProgressDialog loading;

    Context mContext;
    InterfaceApi mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        nama = findViewById(R.id.txt_register_nama);
        username = findViewById(R.id.txt_register_username);
        password = findViewById(R.id.txt_register_password);
        email = findViewById(R.id.txt_register_email);

        mContext = this;
        mApiService = UtilsApi.getApiSerivce();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.length() <= 3 || password.length() <= 3) {
                Toast.makeText(mContext,"username dan password tidak boleh kurang dari 4",Toast.LENGTH_SHORT).show();
                } else{
                    loading = ProgressDialog.show(mContext, null, "Harap tunggu..", true, false);
                requestRegister();
            }
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(register.this, com.ebook.trian.pmo2.auth.login.class);
                startActivity(login);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void requestRegister() {
        mApiService.registerRequest(nama.getText().toString(),
                username.getText().toString(),
                password.getText().toString(),
                email.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug","onResponse :BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                String hasil = jsonRESULTS.getString("status");
                                String error_message = jsonRESULTS.getString("message");
                                if(hasil.equals("200")){
                                    Toast.makeText(mContext,"Berhasil registrasi", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext,login.class));
                                    finish();
                                }else {

                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                               Toast.makeText(mContext, "username sudah ada", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
