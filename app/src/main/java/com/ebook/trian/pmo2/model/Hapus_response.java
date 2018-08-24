package com.ebook.trian.pmo2.model;

import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hapus_response {
    InterfaceApi mApiService = UtilsApi.getApiSerivce();
    String id ;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public Hapus_response(String id){
      this.id = id;
    }

    public void hapus(){
        mApiService.postHapus(id)
                .enqueue(new Callback<List<Hapus_response>>() {
                    @Override
                    public void onResponse(Call<List<Hapus_response>> call, Response<List<Hapus_response>> response) {
                        if (response.isSuccessful()){

                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Hapus_response>> call, Throwable t) {

                    }
                });

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
