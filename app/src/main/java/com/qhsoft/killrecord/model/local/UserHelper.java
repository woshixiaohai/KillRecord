package com.qhsoft.killrecord.model.local;


import com.qhsoft.killrecord.model.remote.bean.LoginBean;

/**
 * Description:
 * Author:lin
 * Date:2017-09-12
 */

public class UserHelper {
    private static LoginBean mLoginBean;

    public static LoginBean getLoginBean() {
        return mLoginBean;
    }

    public static void initializeUserBean(LoginBean loginBean) {
        UserHelper.mLoginBean = loginBean;

    }
    public static String getSessionId(){
        if(mLoginBean!=null&&mLoginBean.getObj()!=null){
            return mLoginBean.getObj().getSessionId();
        }
        return "";

    }


}
