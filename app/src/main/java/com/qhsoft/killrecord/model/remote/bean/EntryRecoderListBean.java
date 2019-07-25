package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class EntryRecoderListBean {

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
         * certificateNo : 333333333
         * id : 402880b1674db1f901674dc60e4f0004
         * approachQty : 12
         * bruteName : 猪
         * supplyname : 漳州市龙文区美之乡水果经营部
         * billno : JC1543198070484
         * jcdate : 2018-11-26 00:00:00.0
         */

        private String certificateNo;
        private String id;
        private String approachQty;
        private String bruteName;
        private String supplyname;
        private String billno;
        private String jcdate;
        private String circleNo;
        private String bruteType;

        public String getCircleNo() {
            return circleNo;
        }

        public void setCircleNo(String circleNo) {
            this.circleNo = circleNo;
        }

        public String getBruteType() {
            return bruteType;
        }

        public void setBruteType(String bruteType) {
            this.bruteType = bruteType;
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

        public String getApproachQty() {
            return approachQty;
        }

        public void setApproachQty(String approachQty) {
            this.approachQty = approachQty;
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
    }
}
