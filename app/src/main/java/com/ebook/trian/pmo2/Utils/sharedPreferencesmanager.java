package com.ebook.trian.pmo2.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPreferencesmanager {
    public static final String SP_PMO_APP = "SpPMO";

    public static final String SP_Id = "spId";
    public static final String SP_Username = "spPassword";
    public static final String SP_Password = "spPassword";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";


    static SharedPreferences sp;
    static SharedPreferences.Editor spEditor;

    public sharedPreferencesmanager(Context context){
        sp = context.getSharedPreferences(SP_PMO_APP,Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public static void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public static void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public static void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public  String getSPUsername(){
        return sp.getString(SP_Username, "");
    }
    public static String getSPId(){
        return sp.getString(SP_Id, "");
    }

    public String getSPPassword(){
        return sp.getString(SP_Password, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
