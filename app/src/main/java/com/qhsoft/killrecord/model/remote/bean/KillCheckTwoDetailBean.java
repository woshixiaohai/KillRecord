package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class KillCheckTwoDetailBean {


    /**
     * success : true
     * msg : 获取成功
     * obj : {"tBruteRuleAftentryList":[{"id":"402880b1675d21aa01675d2284570002","fid":"402880b1675d21aa01675d22844f0001","productName":"豪猪","szBillno":"SZ1543384983555","unitName":"公斤","isQualified":"1","productQty":10,"certificateNo":"12345657","noQualified":""}],"id":"402880b1675d21aa01675d22844f0001","billno":"ZH1543455559140","ruleDate":1543449600000,"checkName":"张三","szBillno":"SZ1543384983555","szId":"402880b167593af901675983bbf10006","result":""}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"billno":"ZH1543455559140","checkName":"张三","id":"402880b1675d21aa01675d22844f0001","result":"","ruleDate":1543449600000,"szBillno":"SZ1543384983555","szId":"402880b167593af901675983bbf10006","tBruteRuleAftentryList":[{"certificateNo":"12345657","id":"402880b1675d21aa01675d2284570002","isQualified":"1","noQualified":"","productName":"豪猪","productQty":10,"szBillno":"SZ1543384983555","unitName":"公斤"}]},"msg":"获取成功","success":true}
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
         * tBruteRuleAftentryList : [{"id":"402880b1675d21aa01675d2284570002","fid":"402880b1675d21aa01675d22844f0001","productName":"豪猪","szBillno":"SZ1543384983555","unitName":"公斤","isQualified":"1","productQty":10,"certificateNo":"12345657","noQualified":""}]
         * id : 402880b1675d21aa01675d22844f0001
         * billno : ZH1543455559140
         * ruleDate : 1543449600000
         * checkName : 张三
         * szBillno : SZ1543384983555
         * szId : 402880b167593af901675983bbf10006
         * result :
         */

        private String id;
        private String billno;
        private long ruleDate;
        private String checkName;
        private String szBillno;
        private String szId;
        private String result;
        private String fsonid;
        private String projectCode;



        private List<TBruteRuleAftentryListBean> tBruteRuleAftentryList;
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

        public long getRuleDate() {
            return ruleDate;
        }

        public void setRuleDate(long ruleDate) {
            this.ruleDate = ruleDate;
        }

        public String getCheckName() {
            return checkName;
        }

        public void setCheckName(String checkName) {
            this.checkName = checkName;
        }

        public String getSzBillno() {
            return szBillno;
        }

        public void setSzBillno(String szBillno) {
            this.szBillno = szBillno;
        }

        public String getSzId() {
            return szId;
        }

        public void setSzId(String szId) {
            this.szId = szId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public List<TBruteRuleAftentryListBean> getTBruteRuleAftentryList() {
            return tBruteRuleAftentryList;
        }

        public void setTBruteRuleAftentryList(List<TBruteRuleAftentryListBean> tBruteRuleAftentryList) {
            this.tBruteRuleAftentryList = tBruteRuleAftentryList;
        }

        public static class TBruteRuleAftentryListBean {
            /**
             * id : 402880b1675d21aa01675d2284570002
             * fid : 402880b1675d21aa01675d22844f0001
             * productName : 豪猪
             * szBillno : SZ1543384983555
             * unitName : 公斤
             * isQualified : 1
             * productQty : 10
             * certificateNo : 12345657
             * noQualified :
             */

            private String id;
            private String fid;
            private String productName;
            private String szBillno;
            private String unitName;
            private String isQualified;
            private int productQty;
            private String certificateNo;
            private String noQualified;

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

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getSzBillno() {
                return szBillno;
            }

            public void setSzBillno(String szBillno) {
                this.szBillno = szBillno;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getIsQualified() {
                return isQualified;
            }

            public void setIsQualified(String isQualified) {
                this.isQualified = isQualified;
            }

            public int getProductQty() {
                return productQty;
            }

            public void setProductQty(int productQty) {
                this.productQty = productQty;
            }

            public String getCertificateNo() {
                return certificateNo;
            }

            public void setCertificateNo(String certificateNo) {
                this.certificateNo = certificateNo;
            }

            public String getNoQualified() {
                return noQualified;
            }

            public void setNoQualified(String noQualified) {
                this.noQualified = noQualified;
            }
        }
    }
}
