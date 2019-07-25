package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class KillCheckListBean {

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
         * isSuccess : 0
         * circleNo :
         * id : 402880b167590282016759307717000f
         * bruteName : 猪
         * checkDate : 2018-11-28 00:00:00.0
         * supplyname : 漳州市龙文区美之乡水果经营部
         * bruteType : 猪
         * worryQty :
         * aimQty : 3
         * billno : SZ1543389596847
         * zhId : 402880b1675d21aa01675d22844f0001
         */

        private String isSuccess;
        private String circleNo;
        private String id;
        private String bruteName;
        private String checkDate;
        private String supplyname;
        private String bruteType;
        private String worryQty;
        private String aimQty;
        private String billno;
        private String zhId;
        private String zhBillno;

        public String getZhBillno() {
            return zhBillno;
        }

        public void setZhBillno(String zhBillno) {
            this.zhBillno = zhBillno;
        }

        public String getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(String isSuccess) {
            this.isSuccess = isSuccess;
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

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
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

        public String getZhId() {
            return zhId;
        }

        public void setZhId(String zhId) {
            this.zhId = zhId;
        }
    }
}
