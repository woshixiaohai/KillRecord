package com.qhsoft.killrecord.model.remote.bean;


public class ShareGoodDetailBean {

    /**
     * success : true
     * msg : 操作成功
     * obj : {"id":"402880f26629565f016629cf0dd00009","createName":"生产测试企业","createBy":"5544321","createDate":1538299727000,"updateName":"生产测试企业","updateBy":"5544321","updateDate":1539049187047,"name":"测试2","barCode":"Q538299676226","kfPeriod":15,"kfDateType":"天","model":"20kg/bao","cultureNo":null,"supplyName":null,"factory":"境外测试","basicUnitName":"件","unitName":null,"packSpeci":"散装","coefficient":null,"ftypeName":"省内商品","icitemType":"粮食加工品","note":"1","photo":"","fever":0,"fsonId":"402880b1655f6e8e01655f6fc7a20002","type":"1","factoryId":"402880966618c82d01661905ce3f0009"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"barCode":"Q538299676226","basicUnitName":"件","createBy":"5544321","createDate":1538299727000,"createName":"生产测试企业","factory":"境外测试","factoryId":"402880966618c82d01661905ce3f0009","fever":0,"fsonId":"402880b1655f6e8e01655f6fc7a20002","ftypeName":"省内商品","icitemType":"粮食加工品","id":"402880f26629565f016629cf0dd00009","kfDateType":"天","kfPeriod":15,"model":"20kg/bao","name":"测试2","note":"1","packSpeci":"散装","photo":"","type":"1","updateBy":"5544321","updateDate":1539049187047,"updateName":"生产测试企业"},"msg":"操作成功","success":true}
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
         * id : 402880f26629565f016629cf0dd00009
         * createName : 生产测试企业
         * createBy : 5544321
         * createDate : 1538299727000
         * updateName : 生产测试企业
         * updateBy : 5544321
         * updateDate : 1539049187047
         * name : 测试2
         * barCode : Q538299676226
         * kfPeriod : 15
         * kfDateType : 天
         * model : 20kg/bao
         * cultureNo : null
         * supplyName : null
         * factory : 境外测试
         * basicUnitName : 件
         * unitName : null
         * packSpeci : 散装
         * coefficient : null
         * ftypeName : 省内商品
         * icitemType : 粮食加工品
         * note : 1
         * photo :
         * fever : 0
         * fsonId : 402880b1655f6e8e01655f6fc7a20002
         * type : 1
         * factoryId : 402880966618c82d01661905ce3f0009
         */

        private String id;
        private String createName;
        private String createBy;
        private long createDate;
        private String updateName;
        private String updateBy;
        private long updateDate;
        private String name;
        private String barCode;
        private int kfPeriod;
        private String kfDateType;
        private String model;
        private Object cultureNo;
        private Object supplyName;
        private String factory;
        private String basicUnitName;
        private Object unitName;
        private String packSpeci;
        private Object coefficient;
        private String ftypeName;
        private String icitemType;
        private String note;
        private String photo;
        private int fever;
        private String fsonId;
        private String type;
        private String factoryId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
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

        public Object getCultureNo() {
            return cultureNo;
        }

        public void setCultureNo(Object cultureNo) {
            this.cultureNo = cultureNo;
        }

        public Object getSupplyName() {
            return supplyName;
        }

        public void setSupplyName(Object supplyName) {
            this.supplyName = supplyName;
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

        public Object getUnitName() {
            return unitName;
        }

        public void setUnitName(Object unitName) {
            this.unitName = unitName;
        }

        public String getPackSpeci() {
            return packSpeci;
        }

        public void setPackSpeci(String packSpeci) {
            this.packSpeci = packSpeci;
        }

        public Object getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(Object coefficient) {
            this.coefficient = coefficient;
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

        public String getFsonId() {
            return fsonId;
        }

        public void setFsonId(String fsonId) {
            this.fsonId = fsonId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(String factoryId) {
            this.factoryId = factoryId;
        }
    }
}
