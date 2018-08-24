package com.ebook.trian.pmo2;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ebook.trian.pmo2.Utils.InterfaceApi;
import com.ebook.trian.pmo2.Utils.UtilsApi;
import com.ebook.trian.pmo2.adapter.AdapterBuku;
import com.ebook.trian.pmo2.model.Model_Buku;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Halaman_Buku extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "fragment home";
    StaggeredGridLayoutManager layoutManager;
    RecyclerView rv;
    AdapterBuku adapter;
    List<Model_Buku> modelBuku = new ArrayList<>();
    InterfaceApi mApiservice;
    ProgressBar progres;
    ImageView btn_back;
    Window window;
    RelativeLayout btn_cari;
    LinearLayout paren_notfound;
    TextView pesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_buku);
        rv = (RecyclerView) findViewById(R.id.rv_buku);
        progres = (ProgressBar) findViewById(R.id.main_progress_buku);
        btn_back = (ImageView) findViewById(R.id.btn_buku_back);
        btn_cari = (RelativeLayout) findViewById(R.id.btn_buku_cari);
        paren_notfound = (LinearLayout) findViewById(R.id.ly_parent_buku);
        pesan = (TextView) findViewById(R.id.tv_buku_pesan);


        adapter = new AdapterBuku(modelBuku ,this);
        layoutManager = new StaggeredGridLayoutManager(2, GridLayout.VERTICAL);

        rv.setLayoutManager(layoutManager);
        buku();

        btn_cari.setOnClickListener(Halaman_Buku.this);
        btn_back.setOnClickListener(Halaman_Buku.this);

        window = this.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


            //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        }

    }

    private void buku() {
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiservice = UtilsApi.getApiSerivce();
        paren_notfound.setVisibility(View.INVISIBLE);

        getBukuList();




    }

    private void getBukuList() {

        String value = getIntent().getExtras().getString("value");
        mApiservice.getBuku("kategori",value)
                .enqueue(new Callback<List<Model_Buku>>() {
                    @Override
                    public void onResponse(Call<List<Model_Buku>> call, Response<List<Model_Buku>> response) {
                        progres.setVisibility(View.GONE);
                        if (response.isSuccessful()){

                            try{
                                if (response==null){
                                    paren_notfound.setVisibility(View.VISIBLE);
                                    pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");

                                }else {
                                    List<Model_Buku> buku_mode = response.body();
                                    modelBuku.addAll(buku_mode);
                                    adapter.notifyDataSetChanged();

                                }
                            }catch (Exception e){
                                paren_notfound.setVisibility(View.VISIBLE);
                                pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                            }
                        }else {
                            paren_notfound.setVisibility(View.VISIBLE);
                            pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                        }


                    }

                    @Override
                    public void onFailure(Call<List<Model_Buku>> call, Throwable t) {
                        progres.setVisibility(View.GONE);
                        paren_notfound.setVisibility(View.VISIBLE);
                        pesan.setText("Belum ada koleksi untuk saat ini anda dapat menambahkan nanti ..");
                        Log.e("ERROR: ", t.getMessage());
                    }

                });

    }

    @Override
    protected void onResume() {
        super.onResume();

        modelBuku.clear();

    }

    @Override
    public void onClick(View v) {

        if (v == btn_cari){
            startActivity(new Intent(Halaman_Buku.this,Halaman_Cari.class));
            finish();
        }else if(v == btn_back){
            startActivity(new Intent(Halaman_Buku.this,Halaman_Main.class));
            finish();
        }
    }
}
