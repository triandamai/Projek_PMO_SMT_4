package com.ebook.trian.pmo2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.model.Model_Buku;
import com.ebook.trian.pmo2.Halaman_Detail_Buku;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.ViewHolder> {
    private static String Base_url_image ="https://www.noobdev.biz/assets/uploads/buku/";

    List<Model_Buku> bukuList;
    Context context;

    public AdapterBuku(List<Model_Buku> bukuList, Context context){
        this.bukuList = bukuList;
        this.context = context;
    }



    @Override
    public AdapterBuku.ViewHolder onCreateViewHolder(
             ViewGroup parent,
            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buku,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( AdapterBuku.ViewHolder holder, int position) {
        final Model_Buku modelBuku_ = bukuList.get(position);


        holder.tv_judul.setText(modelBuku_.getJudul());
        holder.tv_penerbit.setText(modelBuku_.getPenerbit());
        holder.tv_publisher.setText("by : "+ modelBuku_.getPublisher());

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
        return bukuList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView_Roboto_Medium tv_judul;
        public customfonts.MyTextView_Roboto_Italic tv_penerbit;
        public customfonts.MyTextView_Roboto_Light tv_publisher;
        public ImageView iv_cover;
        public CardView parent;

        public ViewHolder(View view){
            super(view);
            parent = (CardView) view.findViewById(R.id.cv_buku);
            tv_judul = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_buku_judul);
            tv_penerbit = (customfonts.MyTextView_Roboto_Italic) view.findViewById(R.id.tv_buku_penerbit);
            tv_publisher = (customfonts.MyTextView_Roboto_Light) view.findViewById(R.id.tv_buku_publisher);
            iv_cover = (ImageView) view.findViewById(R.id.iv_buku_cover);

        }

    }
}
