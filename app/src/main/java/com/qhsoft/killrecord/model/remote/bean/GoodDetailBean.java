package com.qhsoft.killrecord.model.remote.bean;

public class GoodDetailBean {
    /**
     * success : true
     * msg : 获取成功
     * obj : {"ID":"40286681669fc3d201669fe993050075","FBarCode":"fdsadf","FName":"ffdsa","FModel":null,"FKFPeriod":10,"FKFDateType":"天","FCultureNo":null,"FSupplyName":null,"FFactory":"可口可乐股份有限公司","FUnitName":null,"FCoefficient":1,"FBasicUnitName":"袋","FSonID":"402880b1655f6e8e01655f6fc7a20002","FNote":"10","FCreateTime":1540281176837,"VERSION":null,"ficitemType":"包装材料","pinyin":null,"isUpload":null,"fbakBarCode":null,"ftypeName":null,"projectCode":3,"fever":1,"factory_id":null,"pack_speci":"散装","photo":"\\c_fs\\files\\201810231552466e1dIGmB.jpg","net":"d"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"ID":"40286681669fc3d201669fe993050075","FBarCode":"fdsadf","FName":"ffdsa","FKFPeriod":10,"FKFDateType":"天","FFactory":"可口可乐股份有限公司","FCoefficient":1.0000000000,"FBasicUnitName":"袋","FSonID":"402880b1655f6e8e01655f6fc7a20002","FNote":"10","FCreateTime":1540281176837,"ficitemType":"包装材料","projectCode":3,"fever":1,"pack_speci":"散装","photo":"\\c_fs\\files\\201810231552466e1dIGmB.jpg","net":"d"},"msg":"获取成功","success":true}
     */

    private boolean success;
    private String msg;
    private ObjBean obj;
    private int code;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public static class ObjBean {

        /**
         * id : 402880b1668b04e801668b0810a80003
         * name : 测试
         * barCode : Q1231564
         * model : 1231
         * kfPeriod : 1
         * kfDateType : 天
         * cultureNo : 测试
         * supplyName : 测试
         * factory :
         * factoryId : null
         * unitName :
         * coefficient : 1
         * basicUnitName : 个
         * icitemType :
         * note : 测试测试
         * photo :
         * fever : 0
         * packSpeci :
         * ftypeName :
         */

        private String id;
        private String name;
        private String barCode;
        private String model;
        private int kfPeriod;
        private String kfDateType;
        private String cultureNo;
        private String supplyName;
        private String factory;
        private Object factoryId;
        private String unitName;
        private int coefficient;
        private String basicUnitName;
        private String icitemType;
        private String note;
        private String photo;
        private int fever;
        private String packSpeci;
        private String ftypeName;

        private String agriType;

        private String productPlace;
        private String singlePrice;
        private String keepFresh;
        private String transportMode;

        public String getAgriType() {
            return agriType;
        }

        public void setAgriType(String agriType) {
            this.agriType = agriType;
        }

        public String getProductPlace() {
            return productPlace;
        }

        public void setProductPlace(String productPlace) {
            this.productPlace = productPlace;
        }

        public String getSinglePrice() {
            return singlePrice;
        }

        public void setSinglePrice(String singlePrice) {
            this.singlePrice = singlePrice;
        }

        public String getKeepFresh() {
            return keepFresh;
        }

        public void setKeepFresh(String keepFresh) {
            this.keepFresh = keepFresh;
        }

        public String getTransportMode() {
            return transportMode;
        }

        public void setTransportMode(String transportMode) {
            this.transportMode = transportMode;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getKfPeriod() {
            return kfPeriod;
        }

        public void setKfPeriod(int kfPeriod) {
            this.kfPeriod = kfPeriod;
        }

        public String getKfDateType() {
            return kfDateType;
        }

        public void setKfDateType(String kfDateType) {
            this.kfDateType = kfDateType;
        }

        public String getCultureNo() {
            return cultureNo;
        }

        public void setCultureNo(String cultureNo) {
            this.cultureNo = cultureNo;
        }

        public String getSupplyName() {
            return supplyName;
        }

        public void setSupplyName(String supplyName) {
            this.supplyName = supplyName;
        }

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public Object getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(Object factoryId) {
            this.factoryId = factoryId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public String getBasicUnitName() {
            return basicUnitName;
        }

        public void setBasicUnitName(String basicUnitName) {
            this.basicUnitName = basicUnitName;
        }

        public String getIcitemType() {
            return icitemType;
        }

        public void setIcitemType(String icitemType) {
            this.icitemType = icitemType;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getFever() {
            return fever;
        }

        public void setFever(int fever) {
            this.fever = fever;
        }

        public String getPackSpeci() {
            return packSpeci;
        }

        public void setPackSpeci(String packSpeci) {
            this.packSpeci = packSpeci;
        }

        public String getFtypeName() {
            return ftypeName;
        }

        public void setFtypeName(String ftypeName) {
            this.ftypeName = ftypeName;
        }
    }
}
