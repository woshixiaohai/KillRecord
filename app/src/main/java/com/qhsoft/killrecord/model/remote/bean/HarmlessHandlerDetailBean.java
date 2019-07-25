package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class HarmlessHandlerDetailBean {

    /**
     * success : true
     * msg : 获取成功
     * obj : {"tBruteHarmHandentryList":[{"id":"402880b1675e354501675e366aba0003","fid":"402880b1675e354501675e366aae0002","outDate":1538092800000,"productType":"畜禽","testType":"待宰检验","productName":"猪 ","circleNo":"","listno":"DZ1543375413733","supplyname":"漳州市龙文区美之乡水果经营部","unqualifiedNote":"","handleQty":21,"unitName":"头","handleMode":"","entityId":null,"invId":"402880b167585ce501675860f7760004"}],"id":"402880b1675e354501675e366aae0002","fsonid":"402880b16706cf72016706d132400001","projectCode":"7","billno":"CL1543472304332","handleDate":1543449600000,"functionary":"李四","superviseName":"王五"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"billno":"CL1543472304332","fsonid":"402880b16706cf72016706d132400001","functionary":"李四","handleDate":1543449600000,"id":"402880b1675e354501675e366aae0002","projectCode":"7","superviseName":"王五","tBruteHarmHandentryList":[{"circleNo":"","fid":"402880b1675e354501675e366aae0002","handleMode":"","handleQty":21,"id":"402880b1675e354501675e366aba0003","invId":"402880b167585ce501675860f7760004","listno":"DZ1543375413733","outDate":1538092800000,"productName":"猪 ","productType":"畜禽","supplyname":"漳州市龙文区美之乡水果经营部","testType":"待宰检验","unitName":"头","unqualifiedNote":""}]},"msg":"获取成功","success":true}
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
         * tBruteHarmHandentryList : [{"id":"402880b1675e354501675e366aba0003","fid":"402880b1675e354501675e366aae0002","outDate":1538092800000,"productType":"畜禽","testType":"待宰检验","productName":"猪 ","circleNo":"","listno":"DZ1543375413733","supplyname":"漳州市龙文区美之乡水果经营部","unqualifiedNote":"","handleQty":21,"unitName":"头","handleMode":"","entityId":null,"invId":"402880b167585ce501675860f7760004"}]
         * id : 402880b1675e354501675e366aae0002
         * fsonid : 402880b16706cf72016706d132400001
         * projectCode : 7
         * billno : CL1543472304332
         * handleDate : 1543449600000
         * functionary : 李四
         * superviseName : 王五
         */

        private String id;
        private String fsonid;
        private String projectCode;
        private String billno;
        private long handleDate;
        private String functionary;
        private String superviseName;
        private List<TBruteHarmHandentryListBean> tBruteHarmHandentryList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public long getHandleDate() {
            return handleDate;
        }

        public void setHandleDate(long handleDate) {
            this.handleDate = handleDate;
        }

        public String getFunctionary() {
            return functionary;
        }

        public void setFunctionary(String functionary) {
            this.functionary = functionary;
        }

        public String getSuperviseName() {
            return superviseName;
        }

        public void setSuperviseName(String superviseName) {
            this.superviseName = superviseName;
        }

        public List<TBruteHarmHandentryListBean> getTBruteHarmHandentryList() {
            return tBruteHarmHandentryList;
        }

        public void setTBruteHarmHandentryList(List<TBruteHarmHandentryListBean> tBruteHarmHandentryList) {
            this.tBruteHarmHandentryList = tBruteHarmHandentryList;
        }

        public static class TBruteHarmHandentryListBean {
            /**
             * id : 402880b1675e354501675e366aba0003
             * fid : 402880b1675e354501675e366aae0002
             * outDate : 1538092800000
             * productType : 畜禽
             * testType : 待宰检验
             * productName : 猪
             * circleNo :
             * listno : DZ1543375413733
             * supplyname : 漳州市龙文区美之乡水果经营部
             * unqualifiedNote :
             * handleQty : 21
             * unitName : 头
             * handleMode :
             * entityId : null
             * invId : 402880b167585ce501675860f7760004
             */

            private String id;
            private String fid;
            private String outDate;
            private String productType;
            private String testType;
            private String productName;
            private String circleNo;
            private String listno;
            private String supplyname;
            private String unqualifiedNote;
            private double handleQty;
            private String unitName;
            private String handleMode;

            private String entityId;
            private String invId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }


            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public String getTestType() {
                return testType;
            }

            public void setTestType(String testType) {
                this.testType = testType;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getCircleNo() {
                return circleNo;
            }

            public void setCircleNo(String circleNo) {
                this.circleNo = circleNo;
            }

            public String getListno() {
                return listno;
            }

            public void setListno(String listno) {
                this.listno = listno;
            }

            public String getSupplyname() {
                return supplyname;
            }

            public void setSupplyname(String supplyname) {
                this.supplyname = supplyname;
            }

            public String getUnqualifiedNote() {
                return unqualifiedNote;
            }

            public void setUnqualifiedNote(String unqualifiedNote) {
                this.unqualifiedNote = unqualifiedNote;
            }

            public double getHandleQty() {
                return handleQty;
            }

            public void setHandleQty(double handleQty) {
                this.handleQty = handleQty;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getHandleMode() {
                return handleMode;
            }

            public void setHandleMode(String handleMode) {
                this.handleMode = handleMode;
            }



            public String getInvId() {
                return invId;
            }

            public void setInvId(String invId) {
                this.invId = invId;
            }



            public String getEntityId() {
                return entityId;
            }

            public void setEntityId(String entityId) {
                this.entityId = entityId;
            }

            public String getOutDate() {


                return outDate;
            }

            public void setOutDate(String outDate) {
                this.outDate = outDate;
            }
        }
    }
}
