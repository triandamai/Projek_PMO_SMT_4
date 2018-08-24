package com.ebook.trian.pmo2.halaman_pustaka;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.adapter.AdapterPenerbit_koleksi;
import com.ebook.trian.pmo2.model.Model_Buku_pustaka;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.getIntent;

public class Pustaka_koleksi extends Fragment {
    private static final String TAG = "fragment home";
    RecyclerView rv;
    AdapterPenerbit_koleksi adapter;
    List<Model_Buku_pustaka> modelBuku = new ArrayList<>();
    InterfaceApi mApiservice;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                            ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pustaka_koleksi,container,false);
        rv = (RecyclerView) view.findViewById(R.id.rv_penerbit_koleksi);
        adapter = new AdapterPenerbit_koleksi(modelBuku,view.getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiservice = UtilsApi.getApiSerivce();
        getKoleksiList();
        return  view;
    }

    private void getKoleksiList() {
        String id = getActivity().getIntent().getExtras().getString("id_pustaka");
        mApiservice.getBukuByPustaka("id_pustaka",id)
                .enqueue(new Callback<List<Model_Buku_pustaka>>() {
                    @Override
                    public void onResponse(Call<List<Model_Buku_pustaka>> call, Response<List<Model_Buku_pustaka>> response) {

                        if (response.isSuccessful()){

                            try{
                                if (response==null){
                                    Toast.makeText(getActivity(),"kosong",Toast.LENGTH_SHORT).show();
                                }else {
                                    List<Model_Buku_pustaka> buku_mode = response.body();
                                    modelBuku.addAll(buku_mode);
                                    adapter.notifyDataSetChanged();


                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(getActivity(),"gagal mendapat respose",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<List<Model_Buku_pustaka>> call, Throwable t) {

                        Toast.makeText(getActivity(),"gagal fetch data home"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ERROR: ", t.getMessage());
                    }

                });

    }

    @Override
    public void onResume() {
        super.onResume();
        modelBuku.clear();
    }
}
