package com.qhsoft.killrecord.model.remote.bean;

import java.util.List;

public class TobekillCheckListBean {

    /**
     * total : 1
     * rows : [{"circleNo":"","id":"402880b1674dde3401674de9ac3c0007","bruteName":"猪 ","fprovinceName":"福建省","supplyname":"漳州市龙文区美之乡水果经营部","invenQty":"8","billno":"JC1543200398867","jcdate":"2018-11-26 00:00:00.0","fcountyName":"龙文区","fcityName":"漳州市","bruteType":"猪"}]
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
         * updateBy : 屠宰场
         * checkName : 张三
         * projectCode : 7
         * fsonid : 402880b16706cf72016706d132400001
         * bruteQty : 21
         * jcBillno : JC1543200398867
         * state : closed
         * slowQty :
         * circleNo :
         * createName : 屠宰场
         * createBy : 屠宰场
         * updateDate : 2018-11-29 15:05:24.53
         * bearNote :
         * id : 402880b167585ce501675860f7760004
         * bruteName : 猪
         * updateName : 屠宰场
         * slowNote :
         * shiftQty :
         * verdict :
         * checkDate : 2018-11-28 00:00:00.0
         * supplyname : 漳州市龙文区美之乡水果经营部
         * shiftNo :
         * bearQty : 21
         * createDate : 2018-11-28 11:23:42.0
         * billno : DZ1543375413733
         */

        private String updateBy;
        private String checkName;
        private String projectCode;
        private String fsonid;
        private String bruteQty;
        private String jcBillno;
        private String state;
        private String slowQty;
        private String circleNo;
        private String createName;
        private String createBy;
        private String updateDate;
        private String bearNote;
        private String id;
        private String bruteName;
        private String updateName;
        private String slowNote;
        private String shiftQty;
        private String verdict;
        private String checkDate;
        private String supplyname;
        private String shiftNo;
        private String bearQty;
        private String createDate;
        private String billno;

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getCheckName() {
            return checkName;
        }

        public void setCheckName(String checkName) {
            this.checkName = checkName;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getFsonid() {
            return fsonid;
        }

        public void setFsonid(String fsonid) {
            this.fsonid = fsonid;
        }

        public String getBruteQty() {
            return bruteQty;
        }

        public void setBruteQty(String bruteQty) {
            this.bruteQty = bruteQty;
        }

        public String getJcBillno() {
            return jcBillno;
        }

        public void setJcBillno(String jcBillno) {
            this.jcBillno = jcBillno;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSlowQty() {
            return slowQty;
        }

        public void setSlowQty(String slowQty) {
            this.slowQty = slowQty;
        }

        public String getCircleNo() {
            return circleNo;
        }

        public void setCircleNo(String circleNo) {
            this.circleNo = circleNo;
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getBearNote() {
            return bearNote;
        }

        public void setBearNote(String bearNote) {
            this.bearNote = bearNote;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBruteName() {
            return bruteName;
        }

        public void setBruteName(String bruteName) {
            this.bruteName = bruteName;
        }

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }

        public String getSlowNote() {
            return slowNote;
        }

        public void setSlowNote(String slowNote) {
            this.slowNote = slowNote;
        }

        public String getShiftQty() {
            return shiftQty;
        }

        public void setShiftQty(String shiftQty) {
            this.shiftQty = shiftQty;
        }

        public String getVerdict() {
            return verdict;
        }

        public void setVerdict(String verdict) {
            this.verdict = verdict;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getSupplyname() {
            return supplyname;
        }

        public void setSupplyname(String supplyname) {
            this.supplyname = supplyname;
        }

        public String getShiftNo() {
            return shiftNo;
        }

        public void setShiftNo(String shiftNo) {
            this.shiftNo = shiftNo;
        }

        public String getBearQty() {
            return bearQty;
        }

        public void setBearQty(String bearQty) {
            this.bearQty = bearQty;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }
    }
}
