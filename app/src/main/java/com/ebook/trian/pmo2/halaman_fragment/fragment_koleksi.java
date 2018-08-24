package com.ebook.trian.pmo2.halaman_fragment;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.Utils.sharedPreferencesmanager;
import com.ebook.trian.pmo2.adapter.AdapterKoleksi;
import com.ebook.trian.pmo2.model.Model_Koleksi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_koleksi extends Fragment  {
private static final String TAG = "fragment home";
    List<Model_Koleksi> koleksiList;
    SwipeRefreshLayout refreshLayout;
    RecyclerView rv;
    AdapterKoleksi adapter;
    List<Model_Koleksi> modelKoleksi_ = new ArrayList<>();
    InterfaceApi mApiservice;
    ProgressBar progres;
    sharedPreferencesmanager p;
    LinearLayout paren_notfound;
    TextView pesan;

    public static fragment_koleksi newInstance(){
        return new fragment_koleksi();
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_koleksi,container,false);
        progres = (ProgressBar) view.findViewById(R.id.main_progress_koleksi);

        paren_notfound = (LinearLayout) view.findViewById(R.id.ly_parent_koleksi);
        pesan = (TextView) view.findViewById(R.id.tv_koleksi_pesan);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);

        rv = (RecyclerView) view.findViewById(R.id.rv_koleksi);


        adapter = new AdapterKoleksi(modelKoleksi_,view.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(layoutManager);
        koleksi();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                koleksi();
                modelKoleksi_.clear();
            }
        });
        refreshLayout.setColorSchemeResources(R.color.blue,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);



        return view;

    }

    private void koleksi() {
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiservice = UtilsApi.getApiSerivce();
        paren_notfound.setVisibility(View.INVISIBLE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getKoleksiList();
            }
        },500);
    }

    private void getKoleksiList() {
        mApiservice.getKoleksi(sharedPreferencesmanager.getSPId())
                .enqueue(new Callback<List<Model_Koleksi>>() {
                    @Override
                    public void onResponse(Call<List<Model_Koleksi>> call, Response<List<Model_Koleksi>> response) {
                        progres.setVisibility(View.GONE);
                        if (response.isSuccessful()){

                            try {
                                if (response == null) {
                                    paren_notfound.setVisibility(View.VISIBLE);
                                    pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                                }
                                else {
                                    Model_Koleksi model_koleksi = new Model_Koleksi();
                                    List<Model_Koleksi> koleksi = response.body();
                                    paren_notfound.setVisibility(View.GONE);
                                    pesan.setVisibility(View.GONE);
                                    modelKoleksi_.addAll(koleksi);
                                    adapter.notifyDataSetChanged();

                                    if(response.body().isEmpty()){
                                        paren_notfound.setVisibility(View.VISIBLE);
                                        pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                                    }else {
                                        paren_notfound.setVisibility(View.GONE);
                                        pesan.setVisibility(View.GONE);
                                    }

                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                paren_notfound.setVisibility(View.VISIBLE);
                                pesan.setText("Terjadi kesalahan");
                            }
                        }else {
                            paren_notfound.setVisibility(View.VISIBLE);
                            pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model_Koleksi>> call, Throwable t) {
                        progres.setVisibility(View.GONE);

                        Log.e("ERROR: ", t.getMessage());
                        paren_notfound.setVisibility(View.VISIBLE);
                        pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        modelKoleksi_.clear();
    }
}
