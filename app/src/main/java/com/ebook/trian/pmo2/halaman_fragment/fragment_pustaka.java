package com.ebook.trian.pmo2.halaman_fragment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import com.ebook.trian.pmo2.adapter.AdapterPustaka;
import com.ebook.trian.pmo2.model.Model_Pustaka;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_pustaka extends Fragment {
    private static final String TAG = "Fragment news";
    RecyclerView rv;
    SwipeRefreshLayout refreshLayout;
    AdapterPustaka adapter;
    List<Model_Pustaka> modelPustaka_ = new ArrayList<>();
    InterfaceApi mApiservice;
    ProgressBar progres;
    LinearLayout paren_notfound;
    TextView pesan;

    public static fragment_pustaka newInstance(){
        return new fragment_pustaka();
    }


    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pustaka,container,false);
        progres = (ProgressBar) view.findViewById(R.id.main_progress_pustaka);
        paren_notfound = (LinearLayout) view.findViewById(R.id.ly_parent_pustaka);
        pesan = (TextView) view.findViewById(R.id.tv_pustaka_pesan);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);


        rv = (RecyclerView) view.findViewById(R.id.rv_epustaka);
        adapter = new AdapterPustaka(modelPustaka_,view.getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        rv.setLayoutManager(layoutManager);
        pustaka();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                pustaka();
                modelPustaka_.clear();
            }
        });
        refreshLayout.setColorSchemeResources(R.color.blue,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        return view;
    }

    private void pustaka() {
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiservice = UtilsApi.getApiSerivce();
        paren_notfound.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPustakaList();
            }
        },500);

    }

    private void getPustakaList() {
        mApiservice.getPustaka("level","member")
                .enqueue(new Callback<List<Model_Pustaka>>() {
                    @Override
                    public void onResponse(Call<List<Model_Pustaka>> call, Response<List<Model_Pustaka>> response) {
                        progres.setVisibility(View.GONE);
                        if (response.isSuccessful()){
                            try {
                                if (response == null){
                                    paren_notfound.setVisibility(View.VISIBLE);
                                    pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");

                                }else {
                                    List<Model_Pustaka> pustaka = response.body();
                                    modelPustaka_.addAll(pustaka);
                                    adapter.notifyDataSetChanged();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else {
                            paren_notfound.setVisibility(View.VISIBLE);
                            pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model_Pustaka>> call, Throwable t) {
                        progres.setVisibility(View.GONE);
                        paren_notfound.setVisibility(View.VISIBLE);
                        pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                        Log.e("ERROR: ", t.getMessage());

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        modelPustaka_.clear();
    }
}
