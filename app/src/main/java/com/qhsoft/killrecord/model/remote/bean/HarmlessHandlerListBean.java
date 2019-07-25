package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class HarmlessHandlerListBean {

    /**
     * total : 1
     * rows : [{"superviseName":"","id":"402880b1673fbe3901673fc5bc980041","functionary":"李四","handleDate":"2018-11-23 00:00:00.0","billno":"CL1542963154070"}]
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
         * superviseName :
         * id : 402880b1673fbe3901673fc5bc980041
         * functionary : 李四
         * handleDate : 2018-11-23 00:00:00.0
         * billno : CL1542963154070
         */

        private String superviseName;
        private String id;
        private String functionary;
        private String handleDate;
        private String billno;

        public String getSuperviseName() {
            return superviseName;
        }

        public void setSuperviseName(String superviseName) {
            this.superviseName = superviseName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFunctionary() {
            return functionary;
        }

        public void setFunctionary(String functionary) {
            this.functionary = functionary;
        }

        public String getHandleDate() {
            return handleDate;
        }

        public void setHandleDate(String handleDate) {
            this.handleDate = handleDate;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }
    }
}
