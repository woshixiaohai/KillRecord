package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class KillRangeListBean {

    /**
     * total : 1
     * rows : [{"id":"402880b1670af54001670afbdaf4002c","projectCode":"7","bruteName":"猪","unitName":"条","billno":"Q542077528971","bruteType":"猪"}]
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
         * id : 402880b1670af54001670afbdaf4002c
         * projectCode : 7
         * bruteName : 猪
         * unitName : 条
         * billno : Q542077528971
         * bruteType : 猪
         */

        private String id;
        private String projectCode;
        private String bruteName;
        private String unitName;
        private String billno;
        private String bruteType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getBruteName() {
            return bruteName;
        }

        public void setBruteName(String bruteName) {
            this.bruteName = bruteName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public String getBruteType() {
            return bruteType;
        }

        public void setBruteType(String bruteType) {
            this.bruteType = bruteType;
        }
    }
}
