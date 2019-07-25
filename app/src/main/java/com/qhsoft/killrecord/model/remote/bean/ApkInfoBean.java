package com.qhsoft.killrecord.model.remote.bean;

public class ApkInfoBean  {


    /**
     * success : false
     * msg : 当前已是最新版本
     * obj : null
     * code : 200
     * attributes : null
     * jsonStr : {"msg":"当前已是最新版本","success":false}
     */

    private boolean success;
    private String msg;
    private Object obj;
    private int code;
    private AttributesBean attributes;
    private String jsonStr;

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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public static class AttributesBean {
        /**
         * streamPath : /c_fs/files/20190313111625t5tCY273.apk
         * version : V1.0.2
         */

        private String streamPath;
        private String version;

        public String getStreamPath() {
            return streamPath;
        }

        public void setStreamPath(String streamPath) {
            this.streamPath = streamPath;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
