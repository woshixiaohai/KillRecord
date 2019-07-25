package com.qhsoft.killrecord.model.remote.bean;

public class ShareBruteDetailBean {

    /**
     * success : true
     * msg : 获取成功
     * obj : {"id":"402880b16754516c01675457f4ed0002","billno":"Q321586765","bruteName":"五花鸭","bruteType":"鸭","unitName":"只"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"billno":"Q321586765","bruteName":"五花鸭","bruteType":"鸭","id":"402880b16754516c01675457f4ed0002","unitName":"只"},"msg":"获取成功","success":true}
     */

    private boolean success;
    private String msg;
    private ObjBean obj;
    private int code;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ObjBean {
        /**
         * id : 402880b16754516c01675457f4ed0002
         * billno : Q321586765
         * bruteName : 五花鸭
         * bruteType : 鸭
         * unitName : 只
         */

        private String id;
        private String billno;
        private String bruteName;
        private String bruteType;
        private String unitName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public String getBruteName() {
            return bruteName;
        }

        public void setBruteName(String bruteName) {
            this.bruteName = bruteName;
        }

        public String getBruteType() {
            return bruteType;
        }

        public void setBruteType(String bruteType) {
            this.bruteType = bruteType;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
    }
}
