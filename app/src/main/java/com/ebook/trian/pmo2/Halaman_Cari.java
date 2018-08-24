package com.ebook.trian.pmo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.adapter.AdapterCariBuku;
import com.ebook.trian.pmo2.model.Model_Buku;
import com.ebook.trian.pmo2.model.Model_Buku_Cari;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Halaman_Cari extends AppCompatActivity implements SearchView.OnQueryTextListener{
    AdapterCariBuku adapter;
    List<Model_Buku_Cari> cariList = new ArrayList<>();
    InterfaceApi mApiservice;
    RecyclerView rv;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_cari);
        rv = (RecyclerView) findViewById(R.id.rv_cari_buku);
        searchView = (SearchView) findViewById(R.id.sv_buku);

        searchView.setOnQueryTextListener(Halaman_Cari.this);

        adapter = new  AdapterCariBuku(cariList,Halaman_Cari.this);
        mApiservice = UtilsApi.getApiSerivce();
        //aksiCari();
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        aksiCari(query);
        cariList.clear();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        aksiCari(newText);
        cariList.clear();
        return true;
    }
    private void aksiCari(String isi) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Halaman_Cari.this);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiservice.getBukuCari(isi, (String) isi)
                .enqueue(new Callback<List<Model_Buku_Cari>>() {
                    @Override
                    public void onResponse(Call<List<Model_Buku_Cari>> call, Response<List<Model_Buku_Cari>> response) {
                        if (response.isSuccessful()) {
                            try {
                                List<Model_Buku_Cari> cariModel = response.body();
                                cariList.addAll(cariModel);
                                adapter.notifyDataSetChanged();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(Halaman_Cari.this,"gagal ",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Model_Buku_Cari>> call, Throwable t) {
                    t.printStackTrace();
                    }
                });

    }
}
