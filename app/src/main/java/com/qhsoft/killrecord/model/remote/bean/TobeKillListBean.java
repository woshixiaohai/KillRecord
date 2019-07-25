package com.qhsoft.killrecord.model.remote.bean;

import java.io.Serializable;
import java.util.List;

public class TobeKillListBean {

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

    public static class RowsBean implements Serializable {
        /**
         * circleNo :
         * id : 402880b1674dde3401674de9ac3c0007
         * bruteName : 猪
         * fprovinceName : 福建省
         * supplyname : 漳州市龙文区美之乡水果经营部
         * invenQty : 8
         * billno : JC1543200398867
         * jcdate : 2018-11-26 00:00:00.0
         * fcountyName : 龙文区
         * fcityName : 漳州市
         * bruteType : 猪
         */

        private String circleNo;
        private String id;
        private String bruteName;
        private String fprovinceName;
        private String supplyname;
        private String invenQty;
        private String billno;
        private String jcdate;
        private String fcountyName;
        private String fcityName;
        private String bruteType;

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

        public String getJcdate() {
            return jcdate;
        }

        public void setJcdate(String jcdate) {
            this.jcdate = jcdate;
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
    }
}
