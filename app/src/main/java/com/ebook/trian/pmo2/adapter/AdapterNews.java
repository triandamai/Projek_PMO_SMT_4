package com.ebook.trian.pmo2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ebook.trian.pmo2.Halaman_Detail_Buku;
import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.model.Model_Berita;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {
    private static String Base_url_image ="https://www.noobdev.biz/assets/uploads/buku/";


    List<Model_Berita> beritaList;
    Context context;

    public AdapterNews(List<Model_Berita> beritaList, Context context){
        this.beritaList = beritaList;
        this.context = context;
    }



    @Override
    public AdapterNews.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterNews.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        final Model_Berita buku_model = beritaList.get(position);
        holder.tv_nama.setText(buku_model.getNama());
        holder.tv_notif.setText(buku_model.getNotif());
        holder.tv_judul.setText(buku_model.getJudul());
        Picasso.get()
                .load(Base_url_image+buku_model.getCover())
                .placeholder(R.drawable.page_not_found)
                .into(holder.iv_cover);
        Picasso.get()
                .load(Base_url_image+buku_model.getFoto())
                .placeholder(R.drawable.page_not_found)
                .into(holder.iv_user);

        ((ViewHolder) holder).btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.startActivity(new Intent(context,Halaman_Detail_Buku.class).putExtra("idBuku", (String) buku_model.getIdBuku()));
                Toast.makeText(context,"id"+buku_model.getIdBuku(),Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return beritaList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView_Roboto_Regular tv_judul;
        public TextView    tv_nama,btn_lihat;
        public TextView      tv_notif;
        public ImageView iv_cover;
        public FrameLayout parent;
        public CircleImageView iv_user;
        public LinearLayout ly_parent,ly_head,ly_;

        public ViewHolder(View view){
            super(view);
            tv_judul = (customfonts.MyTextView_Roboto_Regular) view.findViewById(R.id.tv_ibuku_Judul);
            tv_nama = (TextView) view.findViewById(R.id.tv_ibuku_nama);
            tv_notif = (TextView) view.findViewById(R.id.tv_ibuku_notif);
            iv_cover = (ImageView) view.findViewById(R.id.iv_ibuku_cover);
            parent = (FrameLayout) view.findViewById(R.id.fl_item_news_parent);
            ly_parent = (LinearLayout) view.findViewById(R.id.ly_news_head);
            iv_user = (CircleImageView) view.findViewById(R.id.iv_ibuku_foto);
            btn_lihat =(TextView) view.findViewById(R.id.tv_lihat);

        }

    }
}
