package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class ShareGoodListBean {

    /**
     * total : 1
     * rows : [{"model":"2KG","basicUnitName":"袋","ftypeName":"省内商品","state":"closed","type":"1","fever":"0","fsonId":"402880b1655f6e8e01655f6fc7a20002","id":"402880966613a390016614bc22c50030","name":"岚谷熏鹅","barCode":"Q537946135337","note":"","factoryId":"402880966613a39001661482a5520029","icitemType":"肉制品","photo":"/c_fs/files/20180926151604PHHj9zWa.jpg","kfPeriod":"7","kfDateType":"天","packSpeci":"预包装","factory":"武夷山岚谷有限公司"}]
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
         * model : 2KG
         * basicUnitName : 袋
         * ftypeName : 省内商品
         * state : closed
         * type : 1
         * fever : 0
         * fsonId : 402880b1655f6e8e01655f6fc7a20002
         * id : 402880966613a390016614bc22c50030
         * name : 岚谷熏鹅
         * barCode : Q537946135337
         * note :
         * factoryId : 402880966613a39001661482a5520029
         * icitemType : 肉制品
         * photo : /c_fs/files/20180926151604PHHj9zWa.jpg
         * kfPeriod : 7
         * kfDateType : 天
         * packSpeci : 预包装
         * factory : 武夷山岚谷有限公司
         */

        private String model;
        private String basicUnitName;
        private String ftypeName;
        private String state;
        private String type;
        private String fever;
        private String fsonId;
        private String id;
        private String name;
        private String barCode;
        private String note;
        private String factoryId;
        private String icitemType;
        private String photo;
        private String kfPeriod;
        private String kfDateType;
        private String packSpeci;
        private String factory;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getBasicUnitName() {
            return basicUnitName;
        }

        public void setBasicUnitName(String basicUnitName) {
            this.basicUnitName = basicUnitName;
        }

        public String getFtypeName() {
            return ftypeName;
        }

        public void setFtypeName(String ftypeName) {
            this.ftypeName = ftypeName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFever() {
            return fever;
        }

        public void setFever(String fever) {
            this.fever = fever;
        }

        public String getFsonId() {
            return fsonId;
        }

        public void setFsonId(String fsonId) {
            this.fsonId = fsonId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(String factoryId) {
            this.factoryId = factoryId;
        }

        public String getIcitemType() {
            return icitemType;
        }

        public void setIcitemType(String icitemType) {
            this.icitemType = icitemType;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getKfPeriod() {
            return kfPeriod;
        }

        public void setKfPeriod(String kfPeriod) {
            this.kfPeriod = kfPeriod;
        }

        public String getKfDateType() {
            return kfDateType;
        }

        public void setKfDateType(String kfDateType) {
            this.kfDateType = kfDateType;
        }

        public String getPackSpeci() {
            return packSpeci;
        }

        public void setPackSpeci(String packSpeci) {
            this.packSpeci = packSpeci;
        }

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }
    }
}
