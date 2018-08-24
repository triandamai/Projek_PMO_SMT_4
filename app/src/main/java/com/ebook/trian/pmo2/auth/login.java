package com.ebook.trian.pmo2.auth;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.Halaman_Main;
import com.ebook.trian.pmo2.Utils.sharedPreferencesmanager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  login extends AppCompatActivity {
    Button btn_login;
    Button btn_register;
    EditText username,password;
    ProgressDialog loading;

    TextView eror;
    InterfaceApi mApiservice;
    Context mContext;

    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_login);

        logo = findViewById(R.id.logo);

        btn_login =  findViewById(R.id.btn_login);
        btn_register =  findViewById(R.id.btn_register);


        username = findViewById(R.id.txt_login_username);
        password = findViewById(R.id.txt_login_password);
        eror = findViewById(R.id.txt_login_eror);


        eror.setText("");
        eror.setVisibility(View.INVISIBLE);

      logo.setImageResource(R.drawable.ic_logo);
        btn_login.setText("Log In");
        btn_register.setText("Register");

        mContext = this;
        mApiservice = UtilsApi.getApiSerivce(); // inisialisasi class Utilsapi

        sharedPreferencesmanager sharedPreferencesmanager = new sharedPreferencesmanager(this);
        if (sharedPreferencesmanager.getSPSudahLogin()){
            startActivity(new Intent(login.this,Halaman_Main.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }else {
            initComponents();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initComponents() {


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (username.length() >=1  && password.length()>=1 ){
                  loading = ProgressDialog.show(mContext,null,"harap tunggu..",true,false);
                  requestlogin();
              }else {
                eror.setVisibility(View.VISIBLE);
                eror.setText("field tidak boleh kosong !!");
              }
            }

        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(login.this, com.ebook.trian.pmo2.auth.register.class);
                startActivity(register);
            }
        });
    }
    private void requestlogin() {
     mApiservice.loginRequest(username.getText().toString(),
                                password.getText().toString())
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call,
                                                           Response<ResponseBody> response) {
                                        if(response.isSuccessful()){
                                            loading.dismiss();
                                            try{
                                                JSONObject jsonRESULT = new JSONObject(response.body().string());
                                                String hasil =  jsonRESULT.getString("status");

                                                if(hasil.equals("200")) {
                                                    String id = jsonRESULT.getJSONObject("data").getString("id");
                                                    String nama = jsonRESULT.getJSONObject("data").getString("username");
                                                    String i ;
                                                    sharedPreferencesmanager.saveSPString(sharedPreferencesmanager.SP_Username, nama);
                                                    sharedPreferencesmanager.saveSPString(sharedPreferencesmanager.SP_Id, id);
                                                    sharedPreferencesmanager.saveSPBoolean(sharedPreferencesmanager.SP_SUDAH_LOGIN, true);
                                                    i = sharedPreferencesmanager.getSPId();
                                                    Toast.makeText(mContext, "berhasil", Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(mContext,Halaman_Main.class));
                                                    finish();
                                                }
                                                else if(hasil.equals("204")){
                                                    Toast.makeText(mContext, "not found", Toast.LENGTH_SHORT).show();

                                                }else {

                                                    Toast.makeText(mContext,"gagal",Toast.LENGTH_SHORT).show();

                                                }
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }else {
                                            Toast.makeText(mContext,"Gagal merespn server",Toast.LENGTH_LONG).show();

                                            loading.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(mContext,"Gagal menghubungkan ke server ",Toast.LENGTH_LONG).show();
                                    }
                                });

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        return super.onCreateView(parent, name, context, attrs);

    }
}
