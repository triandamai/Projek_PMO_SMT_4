package com.ebook.trian.pmo2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;


public class Halaman_Baca extends AppCompatActivity   {
    WebView webView;
    ProgressBar progressBar;
    RemotePDFViewPager remotePDFViewPager ;
    PDFView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_baca);
        progressBar = findViewById(R.id.progressbar_viewer);



            String file = getIntent().getExtras().getString("file");
            //remotePDFViewPager = new RemotePDFViewPager(this, file, this);
            //progressBar = findViewById(R.id.progressbar_viewer);
        pdf = findViewById(R.id.pdfView);
        new DownloadPdf().execute(file);


    }


    private class DownloadPdf extends AsyncTask<String, Void,InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL uri = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection)uri.openConnection();
                if (urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (MalformedURLException e){
                e.printStackTrace();

            }catch (IOException e){
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdf.fromStream(inputStream).load();
            progressBar.setVisibility(View.GONE);
        }
    }

}
