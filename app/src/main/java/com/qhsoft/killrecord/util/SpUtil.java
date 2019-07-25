package com.qhsoft.killrecord.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;


    public SpUtil(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sp.edit();

    }

    public void setUserName(String userName) {
        editor.putString("username", userName);
        editor.commit();

    }

    public String getUserName() {
        return sp.getString("username", "");
    }

    public void setPassword(String pwd) {
        editor.putString("password", pwd);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }

    public void setDomain(String domain){
        editor.putString("domain", domain);
        editor.commit();


    }
    public String getDomain(){
        return sp.getString("domain", "www.shypym.cn");
    }

    public void setIp(String ip){
        editor.putString("ip", ip);
        editor.commit();


    }
    public String getIp(){
        return sp.getString("ip", "218.86.97.162");
    }
    public void setPort(String port){
        editor.putString("port", port);
        editor.commit();


    }
    public String getPort(){
        return sp.getString("port", "9005");
    }

    public void setRemember(boolean remember){
        editor.putBoolean("remember", remember);
        editor.commit();
    }
    public boolean getRemember(){
        return sp.getBoolean("remember",true);
    }


    public void setContext(String context){
        editor.putString("context", context);
        editor.commit();


    }
    public String getContext(){
        return sp.getString("context", "");
    }
    public String getBaseUrl(){
        if(getDomain()!=null&&!getDomain().equals("")){
            return "http://"+getDomain()+"/";

        }else {
            return "http://"+getIp()+":"
                    +getPort()+"/"
                    +getContext()+"/";
        }



    }




}
