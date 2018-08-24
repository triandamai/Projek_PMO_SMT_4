package com.ebook.trian.pmo2.halaman_fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ebook.trian.pmo2.R;
import com.ebook.trian.pmo2.Halaman_Buku;
import com.ebook.trian.pmo2.coba;

public class fragment_home extends Fragment {
    private static final String TAG = "fragment home";
    Button btn;
    RecyclerView rv;
    CardView bisnis,fashion,design,sosmed;

    private OnFragmentInteractionListener listener;

    public static fragment_home newInstance(){
        return new fragment_home();
    }


    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_home,container,false);
    sosmed = (CardView) view.findViewById(R.id.cv_sosmed);

        fashion = (CardView) view.findViewById(R.id.cv_fashion);

        bisnis = (CardView) view.findViewById(R.id.cv_bisnis);

        design = (CardView) view.findViewById(R.id.cv_design);

        sosmed.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(),Halaman_Buku.class).putExtra("value","Sosial Media"));
                }
                 });
        design.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(),Halaman_Buku.class).putExtra("value","design"));
        }
             });
        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Halaman_Buku.class).putExtra("value","Fashion"));
            }
        });
        bisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Halaman_Buku.class).putExtra("value","Bisnis"));
            }
        });


        return view;
    }




    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public interface OnFragmentInteractionListener{
        public void onFragmentInteraction(Uri uri);
    }


}
