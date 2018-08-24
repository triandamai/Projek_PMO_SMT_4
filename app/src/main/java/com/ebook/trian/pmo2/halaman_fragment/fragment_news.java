package com.ebook.trian.pmo2.halaman_fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.ebook.trian.pmo2.adapter.AdapterNews;
import com.ebook.trian.pmo2.model.Model_Berita;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_news extends Fragment {
    private static final String TAG = "Fragment news";
    RecyclerView rv;
    AdapterNews adapter;
    List<Model_Berita> modelBerita = new ArrayList<>();
    InterfaceApi mApiservice;
    ProgressBar progres;
    LinearLayout paren_notfound;
    TextView pesan;

    private OnFragmentInteractionListener listener;

    public static fragment_news newInstance(){
        return new fragment_news();
    }



    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        progres = (ProgressBar) view.findViewById(R.id.main_progress_news);
        paren_notfound = (LinearLayout) view.findViewById(R.id.ly_parent_news);
        pesan = (TextView) view.findViewById(R.id.tv_news_pesan);

        rv = (RecyclerView) view.findViewById(R.id.rv_news);
        adapter = new AdapterNews(modelBerita,view.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiservice = UtilsApi.getApiSerivce();
        paren_notfound.setVisibility(View.INVISIBLE);
        getNewsList();

        return view;
    }
    private void getNewsList() {

    mApiservice.getBerita()
            .enqueue(new Callback<List<Model_Berita>>() {
                @Override
                public void onResponse(Call<List<Model_Berita>> call, Response<List<Model_Berita>> response) {
                    progres.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        try {
                            if (response == null){
                                paren_notfound.setVisibility(View.VISIBLE);
                                pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");

                            }else {
                                List<Model_Berita> berita = response.body();
                                modelBerita.addAll(berita);
                                adapter.notifyDataSetChanged();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        paren_notfound.setVisibility(View.VISIBLE);
                        pesan.setText("Kami kesulitan menghubungi server \n Silahkan coba beberapa saat lagi");
                    }

                }

                @Override
                public void onFailure(Call<List<Model_Berita>> call, Throwable t) {
                    progres.setVisibility(View.GONE);
                    paren_notfound.setVisibility(View.VISIBLE);
                    pesan.setText("Kami kesulitan menghubungi server \n Silahkan coba beberapa saat lagi");
                    Log.e("ERROR: ", t.getMessage());
                }
            });
    }

    @Override
    public void onResume() {
        super.onResume();
        modelBerita.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    public interface OnFragmentInteractionListener{
        public void onFragmentInteraction(Uri uri);
    }
}

