package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class LivestockListBean {

    /**
     * total : 1
     * rows : [{"jcBillno":"JC1543198070484","certificateNo":"333333333","id":"402880b167590282016759307717000f","bruteName":"猪 ","checkDate":"2018-11-28 00:00:00.0","fprovinceName":"福建省","supplyname":"漳州市龙文区美之乡水果经营部","aimQty":"3","billno":"SZ1543389596847","fcountyName":"龙文区","fcityName":"漳州市","bruteType":"猪","worryQty":"","unitName":"头"}]
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
         * certificateNo : 333333333
         * id : 402880b167590282016759307717000f
         * bruteName : 猪
         * checkDate : 2018-11-28 00:00:00.0
         * fprovinceName : 福建省
         * supplyname : 漳州市龙文区美之乡水果经营部
         * aimQty : 3
         * billno : SZ1543389596847
         * fcountyName : 龙文区
         * fcityName : 漳州市
         * bruteType : 猪
         * worryQty :
         * unitName : 头
         */

        private String jcBillno;
        private String certificateNo;
        private String id;
        private String bruteName;
        private String checkDate;
        private String fprovinceName;
        private String supplyname;
        private String aimQty;
        private String billno;
        private String fcountyName;
        private String fcityName;
        private String bruteType;
        private String worryQty;
        private String unitName;

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

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getFprovinceName() {
            return fprovinceName;
        }

        public void setFprovinceName(String fprovinceName) {
            this.fprovinceName = fprovinceName;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
        }

        public String getAimQty() {
            return aimQty;
        }

        public void setAimQty(String aimQty) {
            this.aimQty = aimQty;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public String getFcountyName() {
            return fcountyName;
        }

        public void setFcountyName(String fcountyName) {
            this.fcountyName = fcountyName;
        }

        public String getFcityName() {
            return fcityName;
        }

        public void setFcityName(String fcityName) {
            this.fcityName = fcityName;
        }

        public String getBruteType() {
            return bruteType;
        }

        public void setBruteType(String bruteType) {
            this.bruteType = bruteType;
        }

        public String getWorryQty() {
            return worryQty;
        }

        public void setWorryQty(String worryQty) {
            this.worryQty = worryQty;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
    }
}
