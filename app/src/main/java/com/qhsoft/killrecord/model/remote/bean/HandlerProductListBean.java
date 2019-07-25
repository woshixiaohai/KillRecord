package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class HandlerProductListBean {

    /**
     * total : 1
     * rows : [{"circleNo":"","id":"402880b1674dde3401674de9ac3c0007","bruteName":"猪 ","fprovinceName":"福建省","supplyname":"漳州市龙文区美之乡水果经营部","invenQty":"8","billno":"JC1543200398867","jcdate":"2018-11-26 00:00:00.0","fcountyName":"龙文区","fcityName":"漳州市","bruteType":"猪"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {




        /**
         * text : 猪 -JC1544402861309
         * testType : 进场检验
         * projectCode : 7
         * fsonid : 402880b16706cf72016706d132400001
         * state : closed
         * animType : 畜禽
         * circleNo : 40
         * bearNote : 待宰前死亡数量
         * id : 402880b16795801b0167958fd8880017
         * bruteName : 猪
         * bearInvenQty : 5.00
         * unitName : 头
         * supplyname : 上杭县古田镇少梅副产品经营部
         * billno : JC1544402861309
         * jcdate : 2018-12-10 08:00:00.0
         * bruteType : 猪
         */

        private String text;
        private String testType;
        private String projectCode;
        private String fsonid;
        private String state;
        private String animType;
        private String circleNo;
        private String bearNote;
        private String id;
        private String bruteName;
        private String bearInvenQty;
        private String unitName;
        private String supplyname;
        private String billno;
        private String jcdate;
        private String bruteType;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getFsonid() {
            return fsonid;
        }

        public void setFsonid(String fsonid) {
            this.fsonid = fsonid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAnimType() {
            return animType;
        }

        public void setAnimType(String animType) {
            this.animType = animType;
        }

        public String getCircleNo() {
            return circleNo;
        }

        public void setCircleNo(String circleNo) {
            this.circleNo = circleNo;
        }

        public String getBearNote() {
            return bearNote;
        }

        public void setBearNote(String bearNote) {
            this.bearNote = bearNote;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBruteName() {
            return bruteName;
        }

        public void setBruteName(String bruteName) {
            this.bruteName = bruteName;
        }

        public String getBearInvenQty() {
            return bearInvenQty;
        }

        public void setBearInvenQty(String bearInvenQty) {
            this.bearInvenQty = bearInvenQty;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public String getJcdate() {
            return jcdate;
        }

        public void setJcdate(String jcdate) {
            this.jcdate = jcdate;
        }

        public String getBruteType() {
            return bruteType;
        }

        public void setBruteType(String bruteType) {
            this.bruteType = bruteType;
        }
    }
}
