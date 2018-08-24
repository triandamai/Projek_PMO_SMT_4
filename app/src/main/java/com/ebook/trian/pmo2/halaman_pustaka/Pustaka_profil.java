package com.ebook.trian.pmo2.halaman_pustaka;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.Utils.sharedPreferencesmanager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pustaka_profil extends Fragment {
    EditText nama;
    Button simpan;

    Context mContext;
    InterfaceApi mApiservice;
    sharedPreferencesmanager sp;
    ProgressDialog loading;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pustaka_profil,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        mApiservice = UtilsApi.getApiSerivce(); // inisialisasi class Utilsapi
       // loading = ProgressDialog.show(mContext,null,"mendapatkan informasi user",true,false);
        detailuser();

    }

    private void detailuser() {
        String kondisi = "id";
        mApiservice.profilRequest(kondisi,sharedPreferencesmanager.getSPId())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful())
                        {


                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
