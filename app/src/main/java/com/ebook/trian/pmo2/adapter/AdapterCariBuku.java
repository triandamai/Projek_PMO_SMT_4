package com.ebook.trian.pmo2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ebook.trian.pmo2.Halaman_Cari;
import com.ebook.trian.pmo2.Halaman_Detail_Buku;
import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.model.Model_Buku;
import com.ebook.trian.pmo2.model.Model_Buku_Cari;
import com.ebook.trian.pmo2.model.Model_Buku_pustaka;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCariBuku extends RecyclerView.Adapter<AdapterCariBuku.ViewHolder> {
    private static String Base_url_image ="https://www.noobdev.biz/assets/uploads/buku/";

    List<Model_Buku_Cari> cariList;
    Context context;

    public AdapterCariBuku(List<Model_Buku_Cari> cariList, Context context){
        this.cariList = cariList;
        this.context = context;
    }



    @Override
    public AdapterCariBuku.ViewHolder onCreateViewHolder(
             ViewGroup parent,
            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cari,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCariBuku.ViewHolder holder, int position) {
        final Model_Buku_Cari modelBuku_ = cariList.get(position);


        holder.tv_judul.setText(modelBuku_.getJudul());
        holder.tv_penerbit.setText(modelBuku_.getPenerbit());

        Picasso.get()
                .load(Base_url_image+ modelBuku_.getCover())
                .into(holder.iv_cover);

        ((ViewHolder)holder).parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Halaman_Detail_Buku.class).putExtra("idBuku",modelBuku_.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cariList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView_Roboto_Medium tv_judul,tv_penerbit;

        public ImageView iv_cover;
        public FrameLayout parent;

        public ViewHolder(View view){
            super(view);
            parent = (FrameLayout) view.findViewById(R.id.fl_item_cari_parent);
            tv_judul = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_item_cari_judul);
            tv_penerbit = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_item_cari_penerbit);

            iv_cover = (ImageView) view.findViewById(R.id.iv_item_cari_cover);

        }

    }
}
