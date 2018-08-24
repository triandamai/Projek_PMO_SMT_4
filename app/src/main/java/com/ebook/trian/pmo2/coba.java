package com.ebook.trian.pmo2;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class coba extends AppCompatActivity {
PDFView pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);
        pdf = findViewById(R.id.pdfView);

        new DownloadPdf().execute("https://www.noobdev.biz/assets/uploads/buku/buku_1531623842.pdf");

    }


private class DownloadPdf extends AsyncTask<String, Void,InputStream>{

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
    }
}
}
