package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class ProductStoreListBean {

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
         * jcBillno : JC1543198070484
         * certificateNo : 777777777777
         * circleNo :
         * id : 402880b1674db1f901674dc6ac8a000a
         * bruteName : 猪
         * supplyname : 漳州市龙文区美之乡水果经营部
         * ruleDate : 2018-11-26 00:00:00.0
         * unitName : 公斤
         * invenQty : 1
         * billno : ZH1543198115159
         * productName : 五花猪
         */

        private String jcBillno;
        private String certificateNo;
        private String circleNo;
        private String id;
        private String bruteName;
        private String supplyname;
        private String ruleDate;
        private String unitName;
        private String invenQty;
        private String billno;
        private String productName;
        private boolean isSelect;
        private String animCertificateNo;
        private String zid;

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getAnimCertificateNo() {
            return animCertificateNo;
        }

        public void setAnimCertificateNo(String animCertificateNo) {
            this.animCertificateNo = animCertificateNo;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getJcBillno() {
            return jcBillno;
        }

        public void setJcBillno(String jcBillno) {
            this.jcBillno = jcBillno;
        }

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public String getCircleNo() {
            return circleNo;
        }

        public void setCircleNo(String circleNo) {
            this.circleNo = circleNo;
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

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
        }

        public String getRuleDate() {
            return ruleDate;
        }

        public void setRuleDate(String ruleDate) {
            this.ruleDate = ruleDate;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getInvenQty() {
            return invenQty;
        }

        public void setInvenQty(String invenQty) {
            this.invenQty = invenQty;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
