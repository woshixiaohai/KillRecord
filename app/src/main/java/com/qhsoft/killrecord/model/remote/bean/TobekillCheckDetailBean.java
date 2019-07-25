package com.qhsoft.killrecord.model.remote.bean;

public class TobekillCheckDetailBean {

    /**
     * success : true
     * msg : 获取成功
     * obj : {"id":"402880b16759028201675907c5710004","billno":"DZ1543384983544","checkDate":1543363200000,"jcBillno":"JC1543198070484","supplyname":"漳州市龙文区美之乡水果经营部","bruteName":"猪 ","bruteQty":12,"checkName":"张三","circleNo":"","bearQty":2,"bearNote":"","slowQty":null,"slowNote":"","shiftNo":"","shiftQty":null,"verdict":"","jcId":"402880b1674db1f901674dc60e4f0004","type":"0","bruteType":"猪"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"bearNote":"","bearQty":2,"billno":"DZ1543384983544","bruteName":"猪 ","bruteQty":12,"bruteType":"猪","checkDate":1543363200000,"checkName":"张三","circleNo":"","id":"402880b16759028201675907c5710004","jcBillno":"JC1543198070484","jcId":"402880b1674db1f901674dc60e4f0004","shiftNo":"","slowNote":"","supplyname":"漳州市龙文区美之乡水果经营部","type":"0","verdict":""},"msg":"获取成功","success":true}
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
         * id : 402880b16759028201675907c5710004
         * billno : DZ1543384983544
         * checkDate : 1543363200000
         * jcBillno : JC1543198070484
         * supplyname : 漳州市龙文区美之乡水果经营部
         * bruteName : 猪
         * bruteQty : 12
         * checkName : 张三
         * circleNo :
         * bearQty : 2
         * bearNote :
         * slowQty : null
         * slowNote :
         * shiftNo :
         * shiftQty : null
         * verdict :
         * jcId : 402880b1674db1f901674dc60e4f0004
         * type : 0
         * bruteType : 猪
         */

        private String id;
        private String billno;
        private long checkDate;
        private String jcBillno;
        private String supplyname;
        private String bruteName;
        private int bruteQty;
        private String checkName;
        private String circleNo;
        private int bearQty;
        private String bearNote;
        private int slowQty;
        private String slowNote;
        private String shiftNo;
        private int shiftQty;
        private String verdict;
        private String jcId;
        private String type;
        private String bruteType;

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

        public long getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(long checkDate) {
            this.checkDate = checkDate;
        }

        public String getJcBillno() {
            return jcBillno;
        }

        public void setJcBillno(String jcBillno) {
            this.jcBillno = jcBillno;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
        }

        public String getBruteName() {
            return bruteName;
        }

        public void setBruteName(String bruteName) {
            this.bruteName = bruteName;
        }

        public int getBruteQty() {
            return bruteQty;
        }

        public void setBruteQty(int bruteQty) {
            this.bruteQty = bruteQty;
        }

        public String getCheckName() {
            return checkName;
        }

        public void setCheckName(String checkName) {
            this.checkName = checkName;
        }

        public String getCircleNo() {
            return circleNo;
        }

        public void setCircleNo(String circleNo) {
            this.circleNo = circleNo;
        }

        public int getBearQty() {
            return bearQty;
        }

        public void setBearQty(int bearQty) {
            this.bearQty = bearQty;
        }

        public String getBearNote() {
            return bearNote;
        }

        public void setBearNote(String bearNote) {
            this.bearNote = bearNote;
        }

        public int getSlowQty() {
            return slowQty;
        }

        public void setSlowQty(int slowQty) {
            this.slowQty = slowQty;
        }

        public int getShiftQty() {
            return shiftQty;
        }

        public void setShiftQty(int shiftQty) {
            this.shiftQty = shiftQty;
        }

        public String getSlowNote() {
            return slowNote;
        }

        public void setSlowNote(String slowNote) {
            this.slowNote = slowNote;
        }

        public String getShiftNo() {
            return shiftNo;
        }

        public void setShiftNo(String shiftNo) {
            this.shiftNo = shiftNo;
        }



        public String getVerdict() {
            return verdict;
        }

        public void setVerdict(String verdict) {
            this.verdict = verdict;
        }

        public String getJcId() {
            return jcId;
        }

        public void setJcId(String jcId) {
            this.jcId = jcId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBruteType() {
            return bruteType;
        }

        public void setBruteType(String bruteType) {
            this.bruteType = bruteType;
        }
    }
}
