package com.qhsoft.killrecord.model.remote.bean;

public class EntryRecoderDetailBean {

    /**
     * success : true
     * msg : 获取成功
     * obj : {"id":"402880b16758148d0167583464c60006","billno":"JC1543371191997","jcdate":1543363200000,"supplyname":"漳州市龙文区美之乡水果经营部","supplyid":"402880b1670f9e2901670fe28d21010d","phone":"15987654776","coroner":"张三","approachUse":"代宰","bruteName":"猪","bruteType":"猪","bruteId":"402880b1670c191b01670c20ef020022","fprovinceName":"福建省","fcityName":"漳州市","fcountyName":"龙文区","certificateNo":"123456789","isUniformity":"1","circleNo":"","approachQty":10,"dieQty":null,"result":"","diplomaSrc":""}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"approachQty":10,"approachUse":"代宰","billno":"JC1543371191997","bruteId":"402880b1670c191b01670c20ef020022","bruteName":"猪","bruteType":"猪","certificateNo":"123456789","circleNo":"","coroner":"张三","diplomaSrc":"","fcityName":"漳州市","fcountyName":"龙文区","fprovinceName":"福建省","id":"402880b16758148d0167583464c60006","isUniformity":"1","jcdate":1543363200000,"phone":"15987654776","result":"","supplyid":"402880b1670f9e2901670fe28d21010d","supplyname":"漳州市龙文区美之乡水果经营部"},"msg":"获取成功","success":true}
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
         * id : 402880b16758148d0167583464c60006
         * billno : JC1543371191997
         * jcdate : 1543363200000
         * supplyname : 漳州市龙文区美之乡水果经营部
         * supplyid : 402880b1670f9e2901670fe28d21010d
         * phone : 15987654776
         * coroner : 张三
         * approachUse : 代宰
         * bruteName : 猪
         * bruteType : 猪
         * bruteId : 402880b1670c191b01670c20ef020022
         * fprovinceName : 福建省
         * fcityName : 漳州市
         * fcountyName : 龙文区
         * certificateNo : 123456789
         * isUniformity : 1
         * circleNo :
         * approachQty : 10
         * dieQty : null
         * result :
         * diplomaSrc :
         */

        private String id;
        private String billno;
        private long jcdate;
        private String supplyname;
        private String supplyid;
        private String phone;
        private String coroner;
        private String approachUse;
        private String bruteName;
        private String bruteType;
        private String bruteId;
        private String fprovinceName;
        private String fcityName;
        private String fcountyName;
        private String certificateNo;
        private String isUniformity;
        private String circleNo;
        private int approachQty;
        private int dieQty;
        private String result;
        private String diplomaSrc;

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

        public long getJcdate() {
            return jcdate;
        }

        public void setJcdate(long jcdate) {
            this.jcdate = jcdate;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
        }

        public String getSupplyid() {
            return supplyid;
        }

        public void setSupplyid(String supplyid) {
            this.supplyid = supplyid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCoroner() {
            return coroner;
        }

        public void setCoroner(String coroner) {
            this.coroner = coroner;
        }

        public String getApproachUse() {
            return approachUse;
        }

        public void setApproachUse(String approachUse) {
            this.approachUse = approachUse;
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

        public String getBruteId() {
            return bruteId;
        }

        public void setBruteId(String bruteId) {
            this.bruteId = bruteId;
        }

        public String getFprovinceName() {
            return fprovinceName;
        }

        public void setFprovinceName(String fprovinceName) {
            this.fprovinceName = fprovinceName;
        }

        public String getFcityName() {
            return fcityName;
        }

        public void setFcityName(String fcityName) {
            this.fcityName = fcityName;
        }

        public String getFcountyName() {
            return fcountyName;
        }

        public void setFcountyName(String fcountyName) {
            this.fcountyName = fcountyName;
        }

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public String getIsUniformity() {
            return isUniformity;
        }

        public void setIsUniformity(String isUniformity) {
            this.isUniformity = isUniformity;
        }

        public String getCircleNo() {
            return circleNo;
        }

        public void setCircleNo(String circleNo) {
            this.circleNo = circleNo;
        }

        public int getApproachQty() {
            return approachQty;
        }

        public void setApproachQty(int approachQty) {
            this.approachQty = approachQty;
        }

        public int getDieQty() {
            return dieQty;
        }

        public void setDieQty(int dieQty) {
            this.dieQty = dieQty;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getDiplomaSrc() {
            return diplomaSrc;
        }

        public void setDiplomaSrc(String diplomaSrc) {
            this.diplomaSrc = diplomaSrc;
        }
    }
}
