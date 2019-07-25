package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class OutrecordDetailBean {

    /**
     * success : true
     * msg : 获取成功
     * obj : {"tBruteEnterRecentryList":[{"id":"402880b1675dfe7c01675dff0d2b0003","productName":"豪猪","jcBillno":"JC1543198070484","circleNo":"","fid":"402880b1675dfe7c01675dff0d1f0002","unitName":"公斤","deliveryQty":5,"supplyname":"漳州市龙文区美之乡水果经营部","animalCert":"333333333","meatCert":"342234132","zid":null,"invId":"402880b1675d7d7701675dfabbd90007"}],"id":"402880b1675dfe7c01675dff0d1f0002","billno":"CC1543469978388","enterDate":1543449600000,"purchaser":"赵莺婕","phone":"15980073273","province":"浙江省","city":"杭州市","town":"上城区","scope":"浙江省杭州市上城区","animalCert":"1","meatCert":"1","fsonid":"402880b16706cf72016706d132400001","projectCode":"7"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"animalCert":"1","billno":"CC1543469978388","city":"杭州市","enterDate":1543449600000,"id":"402880b1675dfe7c01675dff0d1f0002","meatCert":"1","phone":"15980073273","province":"浙江省","purchaser":"赵莺婕","scope":"浙江省杭州市上城区","tBruteEnterRecentryList":[{"animalCert":"333333333","circleNo":"","deliveryQty":5,"fid":"402880b1675dfe7c01675dff0d1f0002","id":"402880b1675dfe7c01675dff0d2b0003","invId":"402880b1675d7d7701675dfabbd90007","jcBillno":"JC1543198070484","meatCert":"342234132","productName":"豪猪","supplyname":"漳州市龙文区美之乡水果经营部","unitName":"公斤"}],"town":"上城区"},"msg":"获取成功","success":true}
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
         * tBruteEnterRecentryList : [{"id":"402880b1675dfe7c01675dff0d2b0003","productName":"豪猪","jcBillno":"JC1543198070484","circleNo":"","fid":"402880b1675dfe7c01675dff0d1f0002","unitName":"公斤","deliveryQty":5,"supplyname":"漳州市龙文区美之乡水果经营部","animalCert":"333333333","meatCert":"342234132","zid":null,"invId":"402880b1675d7d7701675dfabbd90007"}]
         * id : 402880b1675dfe7c01675dff0d1f0002
         * billno : CC1543469978388
         * enterDate : 1543449600000
         * purchaser : 赵莺婕
         * phone : 15980073273
         * province : 浙江省
         * city : 杭州市
         * town : 上城区
         * scope : 浙江省杭州市上城区
         * animalCert : 1
         * meatCert : 1
         * fsonid : 402880b16706cf72016706d132400001
         * projectCode : 7
         */

        private String id;
        private String billno;
        private long enterDate;
        private String purchaser;
        private String phone;
        private String province;
        private String city;
        private String town;
        private String scope;
        private String animalCert;
        private String meatCert;
        private String fsonid;
        private String projectCode;
        private List<TBruteEnterRecentryListBean> tBruteEnterRecentryList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public long getEnterDate() {
            return enterDate;
        }

        public void setEnterDate(long enterDate) {
            this.enterDate = enterDate;
        }

        public String getPurchaser() {
            return purchaser;
        }

        public void setPurchaser(String purchaser) {
            this.purchaser = purchaser;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getAnimalCert() {
            return animalCert;
        }

        public void setAnimalCert(String animalCert) {
            this.animalCert = animalCert;
        }

        public String getMeatCert() {
            return meatCert;
        }

        public void setMeatCert(String meatCert) {
            this.meatCert = meatCert;
        }

        public String getFsonid() {
            return fsonid;
        }

        public void setFsonid(String fsonid) {
            this.fsonid = fsonid;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public List<TBruteEnterRecentryListBean> getTBruteEnterRecentryList() {
            return tBruteEnterRecentryList;
        }

        public void setTBruteEnterRecentryList(List<TBruteEnterRecentryListBean> tBruteEnterRecentryList) {
            this.tBruteEnterRecentryList = tBruteEnterRecentryList;
        }

        public static class TBruteEnterRecentryListBean {
            /**
             * id : 402880b1675dfe7c01675dff0d2b0003
             * productName : 豪猪
             * jcBillno : JC1543198070484
             * circleNo :
             * fid : 402880b1675dfe7c01675dff0d1f0002
             * unitName : 公斤
             * deliveryQty : 5
             * supplyname : 漳州市龙文区美之乡水果经营部
             * animalCert : 333333333
             * meatCert : 342234132
             * zid : null
             * invId : 402880b1675d7d7701675dfabbd90007
             */

            private String id;
            private String productName;
            private String jcBillno;
            private String circleNo;
            private String fid;
            private String unitName;
            private double deliveryQty;
            private String supplyname;
            private String animalCert;
            private String meatCert;
            private Object zid;
            private String invId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getJcBillno() {
                return jcBillno;
            }

            public void setJcBillno(String jcBillno) {
                this.jcBillno = jcBillno;
            }

            public String getCircleNo() {
                return circleNo;
            }

            public void setCircleNo(String circleNo) {
                this.circleNo = circleNo;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public double getDeliveryQty() {
                return deliveryQty;
            }

            public void setDeliveryQty(double deliveryQty) {
                this.deliveryQty = deliveryQty;
            }

            public String getSupplyname() {
                return supplyname;
            }

            public void setSupplyname(String supplyname) {
                this.supplyname = supplyname;
            }

            public String getAnimalCert() {
                return animalCert;
            }

            public void setAnimalCert(String animalCert) {
                this.animalCert = animalCert;
            }

            public String getMeatCert() {
                return meatCert;
            }

            public void setMeatCert(String meatCert) {
                this.meatCert = meatCert;
            }

            public Object getZid() {
                return zid;
            }

            public void setZid(Object zid) {
                this.zid = zid;
            }

            public String getInvId() {
                return invId;
            }

            public void setInvId(String invId) {
                this.invId = invId;
            }
        }
    }
}
