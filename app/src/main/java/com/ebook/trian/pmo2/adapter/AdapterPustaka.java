package com.ebook.trian.pmo2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.halaman_pustaka.Halaman_Pustaka;
import com.ebook.trian.pmo2.model.Model_Pustaka;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterPustaka extends RecyclerView.Adapter<AdapterPustaka.ViewHolder> {
    private static String Base_url_image ="http://www.noobdev.biz/assets/uploads/user/";

    List<Model_Pustaka> pustakaList;
    Context context;

    public AdapterPustaka(List<Model_Pustaka> pustakaList, Context context){
        this.pustakaList = pustakaList;
        this.context = context;
    }



    @Override
    public AdapterPustaka.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemm_pustaka,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPustaka.ViewHolder holder, int position) {
        final Model_Pustaka modelPustaka_ = pustakaList.get(position);

     final Dialog myDialog = new Dialog(context);
     myDialog.setContentView(R.layout.item_pustaka);
        TextView nama = myDialog.findViewById(R.id.tv_namaprofil);
        TextView email = myDialog.findViewById(R.id.tv_emailprofil);
        CircleImageView foto = myDialog.findViewById(R.id.iv_fotoprofil);

        nama.setText(modelPustaka_.getNama());
        email.setText(modelPustaka_.getEmail());
        Picasso.get().load(Base_url_image+modelPustaka_.getFoto()).into(foto);

        holder.tv_nama.setText(modelPustaka_.getNama());

        Picasso.get().load(Base_url_image+modelPustaka_.getFoto()).into(holder.iv_cover);

        ((ViewHolder)holder).ly_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.startActivity(new Intent(context, Halaman_Pustaka.class).putExtra("id_pustaka",modelPustaka_.getId()));
                myDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return pustakaList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView_Roboto_Medium tv_nama, tv_email, tv_no_hp;
        public CircleImageView iv_cover;
        public LinearLayout ly_parent;

        public ViewHolder(View view){
            super(view);
            tv_nama = (customfonts.MyTextView_Roboto_Medium) view.findViewById(R.id.tv_pustaka_nama);
            ly_parent = (LinearLayout) view.findViewById(R.id.ly_item_pustaka);
            //tv_email = (TextView) view.findViewById(R.id.tv_ipustaka_email);
            //tv_no_hp = (TextView) view.findViewById(R.id.tv_ipustaka_nohp);
            iv_cover = (CircleImageView) view.findViewById(R.id.iv_pustaka_cover);

        }

    }
}
