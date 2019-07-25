package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class OutrecordListBean {

    /**
     * total : 1
     * rows : [{"phone":"15980073273","scope":"浙江省杭州市上城区","purchaser":"赵莺婕","enterDate":"2018-11-23 00:00:00.0","city":"杭州市","id":"402880b1673f90f501673fad7ef800e3","province":"浙江省","town":"上城区","billno":"CC1542961554267"}]
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
         * phone : 15980073273
         * scope : 浙江省杭州市上城区
         * purchaser : 赵莺婕
         * enterDate : 2018-11-23 00:00:00.0
         * city : 杭州市
         * id : 402880b1673f90f501673fad7ef800e3
         * province : 浙江省
         * town : 上城区
         * billno : CC1542961554267
         */

        private String phone;
        private String scope;
        private String purchaser;
        private String enterDate;
        private String city;
        private String id;
        private String province;
        private String town;
        private String billno;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getPurchaser() {
            return purchaser;
        }

        public void setPurchaser(String purchaser) {
            this.purchaser = purchaser;
        }

        public String getEnterDate() {
            return enterDate;
        }

        public void setEnterDate(String enterDate) {
            this.enterDate = enterDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }
    }
}
