package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class GoodListBean {

    /**
     * total : 1
     * rows : [{"id":"402880966618c82d016618f7017a0001","name":"岚谷熏鹅","barCode":"Q537946135337","icitemType":"肉制品","kfPeriod":"7","kfDateType":"天","factory":"武夷山岚谷有限公司"}]
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
         * id : 402880966618c82d016618f7017a0001
         * name : 岚谷熏鹅
         * barCode : Q537946135337
         * icitemType : 肉制品
         * kfPeriod : 7
         * kfDateType : 天
         * factory : 武夷山岚谷有限公司
         */

        private String id;
        private String name;
        private String barCode;
        private String icitemType;
        private String kfPeriod;
        private String kfDateType;
        private String factory;
        private String unitName;
        private String basicUnitName;
        private boolean isSelect;

        public String getBasicUnitName() {
            return basicUnitName;
        }

        public void setBasicUnitName(String basicUnitName) {
            this.basicUnitName = basicUnitName;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
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

        public String getIcitemType() {
            return icitemType;
        }

        public void setIcitemType(String icitemType) {
            this.icitemType = icitemType;
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

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
    }
}
