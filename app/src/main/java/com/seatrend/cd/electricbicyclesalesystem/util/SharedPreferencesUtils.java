package com.seatrend.cd.electricbicyclesalesystem.util;

import android.content.Context;

import com.seatrend.cd.electricbicyclesalesystem.common.Constants;
import com.seatrend.cd.electricbicyclesalesystem.common.MyApplication;


/**
 * Created by seatrend on 2018/8/22.
 */

public class SharedPreferencesUtils {
    public static void setIsFirst(boolean isfirst){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putBoolean(Constants.Companion.getIS_FIRST(),isfirst).apply();
    }
    public static boolean getIsFirst(){
        boolean b = MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getBoolean(Constants.Companion.getIS_FIRST(), true);
        return b;
    }

    public static void setIpAddress(String ip){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getIP_K(),ip).apply();
    }
    public static String getIpAddress(){
      return   MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
               .getString(Constants.Companion.getIP_K(),"192.168.0.46");
    }

    public static void setPort(String port){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getPORT_K(),port).apply();
    }
    public static String getPort(){
       return MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
               .getString(Constants.Companion.getPORT_K(),"8080");
    }

    public static void setSynCode(boolean b){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putBoolean(Constants.Companion.getSYN_CODE(),b).apply();
    }
    public static boolean getSynCode(){
        return MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getBoolean(Constants.Companion.getSYN_CODE(),false);
    }
    public static void setAdmain(String admain){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getADMAIN(),admain).apply();
    }
    public static String getAdmain(){
        return MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getString(Constants.Companion.getADMAIN(),"system");
    }

    public static void setTjqssj(String time){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getTJQSSJ(),time).apply();
    }
    public static String getTjqssj(){
        return MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getString(Constants.Companion.getTJQSSJ(),"2019-01-01");
    }

    public static void setTjzzsj(String time){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getTJZZSJ(),time).apply();
    }
    public static String getTjzzsj(){
        return MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getString(Constants.Companion.getTJZZSJ(),"2019-12-31");
    }
}
