package com.ebook.trian.pmo2.Utils;

import com.ebook.trian.pmo2.RetrofitClient.RetroFitApiClient;

public class UtilsApi {
    public static final String BASE_URL_API ="http://www.noobdev.biz/index.php/Api/";
    //public static final String BASE_URL_API ="http://www.noobjr.tk/index.php/";

    //deklarasi apiinterface
   public static InterfaceApi getApiSerivce() {
       return RetroFitApiClient.getClient(BASE_URL_API).create(InterfaceApi.class);

   }
}
