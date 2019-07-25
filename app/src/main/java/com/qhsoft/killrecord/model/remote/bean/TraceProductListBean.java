package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class TraceProductListBean {

    /**
     * total : 1
     * rows : [{"meatCert":"123456789","scope":"浙江省杭州市上城区","city":"杭州市","id":"402880b1673f90f501673fad7ef800e3","zid":"402880b1673f90f501673fad83ee00e4","province":"浙江省","deliveryQty":"5","animalCert":"333333333","supplyname":"漳州市龙文区美之乡水果经营部","purchaser":"赵莺婕","enterDate":"2018-11-23 00:00:00.0","unitName":"公斤","town":"上城区","billno":"CC1542961554267","productName":"豪猪"}]
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
         * meatCert : 123456789
         * scope : 浙江省杭州市上城区
         * city : 杭州市
         * id : 402880b1673f90f501673fad7ef800e3
         * zid : 402880b1673f90f501673fad83ee00e4
         * province : 浙江省
         * deliveryQty : 5
         * animalCert : 333333333
         * supplyname : 漳州市龙文区美之乡水果经营部
         * purchaser : 赵莺婕
         * enterDate : 2018-11-23 00:00:00.0
         * unitName : 公斤
         * town : 上城区
         * billno : CC1542961554267
         * productName : 豪猪
         */

        private String meatCert;
        private String scope;
        private String city;
        private String id;
        private String zid;
        private String province;
        private String deliveryQty;
        private String animalCert;
        private String supplyname;
        private String purchaser;
        private String enterDate;
        private String unitName;
        private String town;
        private String billno;
        private String productName;

        public String getMeatCert() {
            return meatCert;
        }

        public void setMeatCert(String meatCert) {
            this.meatCert = meatCert;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
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

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDeliveryQty() {
            return deliveryQty;
        }

        public void setDeliveryQty(String deliveryQty) {
            this.deliveryQty = deliveryQty;
        }

        public String getAnimalCert() {
            return animalCert;
        }

        public void setAnimalCert(String animalCert) {
            this.animalCert = animalCert;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
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

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
