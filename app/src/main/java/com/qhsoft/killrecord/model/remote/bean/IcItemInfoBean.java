package com.qhsoft.killrecord.model.remote.bean;

public class IcItemInfoBean {
    /**
     * success : true
     * msg : 获取成功
     * obj : {"id":"40286681661f18f401662485ca240093","name":"油葱赤饼","barCode":"6970306597891","kfPeriod":15,"kfDateType":"天","model":"600g/包","factory":"上杭县临城果菜加工厂","basicUnitName":"包","packSpeci":"预包装","ftypeName":"省内商品","icitemType":"糕点","note":"","photo":"","fever":0,"factoryId":"40286681661f18f40166247ce460008e"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"barCode":"6970306597891","basicUnitName":"包","factory":"上杭县临城果菜加工厂","factoryId":"40286681661f18f40166247ce460008e","fever":0,"ftypeName":"省内商品","icitemType":"糕点","id":"40286681661f18f401662485ca240093","kfDateType":"天","kfPeriod":15,"model":"600g/包","name":"油葱赤饼","note":"","packSpeci":"预包装","photo":""},"msg":"获取成功","success":true}
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
         * id : 40286681661f18f401662485ca240093
         * name : 油葱赤饼
         * barCode : 6970306597891
         * kfPeriod : 15
         * kfDateType : 天
         * model : 600g/包
         * factory : 上杭县临城果菜加工厂
         * basicUnitName : 包
         * packSpeci : 预包装
         * ftypeName : 省内商品
         * icitemType : 糕点
         * note :
         * photo :
         * fever : 0
         * factoryId : 40286681661f18f40166247ce460008e
         */

        private String id;
        private String name;
        private String barCode;
        private int kfPeriod;
        private String kfDateType;
        private String model;
        private String factory;
        private String basicUnitName;
        private String packSpeci;
        private String ftypeName;
        private String icitemType;
        private String note;
        private String photo;
        private int fever;
        private String factoryId;


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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getBasicUnitName() {
            return basicUnitName;
        }

        public void setBasicUnitName(String basicUnitName) {
            this.basicUnitName = basicUnitName;
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

        public String getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(String factoryId) {
            this.factoryId = factoryId;
        }
    }
}
