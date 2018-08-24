package com.ebook.trian.pmo2.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebook.trian.pmo2.Halaman_Detail_Buku;
import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.model.Hapus_response;
import com.ebook.trian.pmo2.model.Model_Koleksi;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.Handler;

public class AdapterKoleksi extends RecyclerView.Adapter<AdapterKoleksi.ViewHolder> {
    private static String Base_url_image ="https://www.noobdev.biz/assets/uploads/buku/";

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    List<Model_Koleksi> koleksiList;
    Context context;

    public AdapterKoleksi(List<Model_Koleksi> koleksiList, Context context){
        this.koleksiList = koleksiList;
        this.context = context;
    }



    @Override
    public AdapterKoleksi.ViewHolder onCreateViewHolder(
             ViewGroup parent,
            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_koleksi,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterKoleksi.ViewHolder holder, int position) {
        final Model_Koleksi modelKoleksi_ = koleksiList.get(position);




        holder.tv_judul.setText(modelKoleksi_.getJudul());
        //holder.tv_penerbit.setVisibility(View.INVISIBLE);
        //holder.tv_publish.setVisibility(View.INVISIBLE);
        Picasso.get().load(Base_url_image+modelKoleksi_.getCover())
                .placeholder(R.drawable.bg_gradientkuning)
                .into(holder.iv_cover);
        ((ViewHolder)holder).tv_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hapus_response hapus_response = new Hapus_response(modelKoleksi_.getId());
                hapus_response.hapus();
                hapus_response.getStatus();
                notifyDataSetChanged();
                koleksiList.clear();

            }
        });
        ((ViewHolder)holder).parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Halaman_Detail_Buku.class).putExtra("idBuku",modelKoleksi_.getIdBuku()).putExtra("status","koleksi"));
            }
        });


    }

    @Override
    public int getItemCount() {
        return koleksiList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView_Roboto_Medium tv_judul, tv_penerbit;
        public  TextView tv_hapus;
        public CardView parent;
        public customfonts.MyTextView_Roboto_Medium tv_publish;
        public ImageView iv_cover;

        public ViewHolder(View view){
            super(view);
            parent = (CardView) view.findViewById(R.id.cv_pkoleksi_parent);
            tv_judul = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_koleksi_Judul);
            //tv_penerbit = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_koleksi_penerbit);
            //tv_publish = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_koleksi_penerbit);
            iv_cover = (ImageView) view.findViewById(R.id.iv_koleksi_cover);
            tv_hapus = (TextView) view.findViewById(R.id.tv_hapus_koleksi);

        }

    }
}
