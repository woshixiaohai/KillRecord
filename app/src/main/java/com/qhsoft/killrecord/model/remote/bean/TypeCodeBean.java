package com.qhsoft.killrecord.model.remote.bean;

/**
 * Description:
 * Author:江亲金
 * Date:2017-09-20
 */

public class TypeCodeBean {


    /**
     * success : true
     * msg : 操作成功
     * obj : 食品生产企业
     * code : 200
     * attributes : null
     * jsonStr : {"obj":"食品生产企业","msg":"操作成功","success":true}
     */

    private boolean success;
    private String msg;
    private String obj;
    private int code;
    private Object attributes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }
}
