package com.qhsoft.killrecord.model.remote.bean;

public class KillCheckOneDetailBean {

    /**
     * success : true
     * msg : 获取成功
     * obj : {"id":"402880b167593af90167597620c40004","createName":"屠宰场","createBy":"屠宰场","createDate":1543394173123,"updateName":null,"updateBy":null,"updateDate":null,"fsonid":"402880b16706cf72016706d132400001","projectCode":"7","billno":"SZ1543384983555","checkDate":1543363200000,"jcBillno":"JC1543198070484","supplyname":"漳州市龙文区美之乡水果经营部","bruteName":"猪 ","bruteQty":12,"checkName":"张三","circleNo":"","bearQty":null,"bearNote":"","slowQty":null,"slowNote":"","shiftNo":"","shiftQty":null,"verdict":"","jcId":"402880b1674db1f901674dc60e4f0004","type":"1","bruteType":"猪","aimQty":3,"worryQty":null,"worryNote":""}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"aimQty":3,"bearNote":"","billno":"SZ1543384983555","bruteName":"猪 ","bruteQty":12,"bruteType":"猪","checkDate":1543363200000,"checkName":"张三","circleNo":"","id":"402880b167593af90167597620c40004","jcBillno":"JC1543198070484","jcId":"402880b1674db1f901674dc60e4f0004","shiftNo":"","slowNote":"","supplyname":"漳州市龙文区美之乡水果经营部","type":"1","verdict":"","worryNote":""},"msg":"获取成功","success":true}
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
         * id : 402880b167593af90167597620c40004
         * createName : 屠宰场
         * createBy : 屠宰场
         * createDate : 1543394173123
         * updateName : null
         * updateBy : null
         * updateDate : null
         * fsonid : 402880b16706cf72016706d132400001
         * projectCode : 7
         * billno : SZ1543384983555
         * checkDate : 1543363200000
         * jcBillno : JC1543198070484
         * supplyname : 漳州市龙文区美之乡水果经营部
         * bruteName : 猪
         * bruteQty : 12
         * checkName : 张三
         * circleNo :
         * bearQty : null
         * bearNote :
         * slowQty : null
         * slowNote :
         * shiftNo :
         * shiftQty : null
         * verdict :
         * jcId : 402880b1674db1f901674dc60e4f0004
         * type : 1
         * bruteType : 猪
         * aimQty : 3
         * worryQty : null
         * worryNote :
         */

        private String id;
        private String createName;
        private String createBy;
        private long createDate;
        private Object updateName;
        private Object updateBy;
        private Object updateDate;
        private String fsonid;
        private String projectCode;
        private String billno;
        private long checkDate;
        private String jcBillno;
        private String supplyname;
        private String bruteName;
        private int bruteQty;
        private String checkName;
        private String circleNo;
        private Object bearQty;
        private String bearNote;
        private Object slowQty;
        private String slowNote;
        private String shiftNo;
        private Object shiftQty;
        private String verdict;
        private String jcId;
        private String type;
        private String bruteType;
        private int aimQty;
        private Object worryQty;
        private String worryNote;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateName() {
            return updateName;
        }

        public void setUpdateName(Object updateName) {
            this.updateName = updateName;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public String getFsonid() {
            return fsonid;
        }

        public void setFsonid(String fsonid) {
            this.fsonid = fsonid;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
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

        public Object getBearQty() {
            return bearQty;
        }

        public void setBearQty(Object bearQty) {
            this.bearQty = bearQty;
        }

        public String getBearNote() {
            return bearNote;
        }

        public void setBearNote(String bearNote) {
            this.bearNote = bearNote;
        }

        public Object getSlowQty() {
            return slowQty;
        }

        public void setSlowQty(Object slowQty) {
            this.slowQty = slowQty;
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

        public Object getShiftQty() {
            return shiftQty;
        }

        public void setShiftQty(Object shiftQty) {
            this.shiftQty = shiftQty;
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

        public int getAimQty() {
            return aimQty;
        }

        public void setAimQty(int aimQty) {
            this.aimQty = aimQty;
        }

        public Object getWorryQty() {
            return worryQty;
        }

        public void setWorryQty(Object worryQty) {
            this.worryQty = worryQty;
        }

        public String getWorryNote() {
            return worryNote;
        }

        public void setWorryNote(String worryNote) {
            this.worryNote = worryNote;
        }
    }
}
