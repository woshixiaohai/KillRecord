package com.qhsoft.killrecord.model.remote.bean;

public class PeopleDetailBean {


    /**
     * success : true
     * msg : 获取成功
     * obj : {"id":"402880b1678100e70167818237160025","createName":"屠宰场","createBy":"屠宰场","createDate":1544066053000,"updateName":"屠宰场","updateBy":"屠宰场","updateDate":1544075776003,"name":"qqq","certificateType":"检验员","certificateCode":"","licenceNo":"1234","mobilePhone":"15900000000","telePhone":"11111111","homePhone":"22222222","sex":"1","graduateSchool":"测试学院","graduateDate":1433520000000,"birthday":837964800000,"nation":"汉","address":"","xueli":"本科","professional":"测试专业","professionalTitle":"测试职称","politicalStatus":"群众","workDate":1449331200000,"originalWorkUnit":"测试原单位","remark":"","companyId":"402880b16706cf72016706d132400001","idCard":"410883352564335753","streamName0":"","streamName1":"","projectCode":"7","licenceType":null,"testDate":null,"nowWorkUnit":null}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"address":"","birthday":837964800000,"certificateCode":"","certificateType":"检验员","companyId":"402880b16706cf72016706d132400001","createBy":"屠宰场","createDate":1544066053000,"createName":"屠宰场","graduateDate":1433520000000,"graduateSchool":"测试学院","homePhone":"22222222","id":"402880b1678100e70167818237160025","idCard":"410883352564335753","licenceNo":"1234","mobilePhone":"15900000000","name":"qqq","nation":"汉","originalWorkUnit":"测试原单位","politicalStatus":"群众","professional":"测试专业","professionalTitle":"测试职称","projectCode":"7","remark":"","sex":"1","streamName0":"","streamName1":"","telePhone":"11111111","updateBy":"屠宰场","updateDate":1544075776003,"updateName":"屠宰场","workDate":1449331200000,"xueli":"本科"},"msg":"获取成功","success":true}
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
         * id : 402880b1678100e70167818237160025
         * createName : 屠宰场
         * createBy : 屠宰场
         * createDate : 1544066053000
         * updateName : 屠宰场
         * updateBy : 屠宰场
         * updateDate : 1544075776003
         * name : qqq
         * certificateType : 检验员
         * certificateCode :
         * licenceNo : 1234
         * mobilePhone : 15900000000
         * telePhone : 11111111
         * homePhone : 22222222
         * sex : 1
         * graduateSchool : 测试学院
         * graduateDate : 1433520000000
         * birthday : 837964800000
         * nation : 汉
         * address :
         * xueli : 本科
         * professional : 测试专业
         * professionalTitle : 测试职称
         * politicalStatus : 群众
         * workDate : 1449331200000
         * originalWorkUnit : 测试原单位
         * remark :
         * companyId : 402880b16706cf72016706d132400001
         * idCard : 410883352564335753
         * streamName0 :
         * streamName1 :
         * projectCode : 7
         * licenceType : null
         * testDate : null
         * nowWorkUnit : null
         */

        private String id;
        private String createName;
        private String createBy;
        private long createDate;
        private String updateName;
        private String updateBy;
        private long updateDate;
        private String name;
        private String certificateType;
        private String certificateCode;
        private String licenceNo;
        private String mobilePhone;
        private String telePhone;
        private String homePhone;
        private String sex;
        private String graduateSchool;
        private long graduateDate;
        private long birthday;
        private String nation;
        private String address;
        private String xueli;
        private String professional;
        private String professionalTitle;
        private String politicalStatus;
        private long workDate;
        private String originalWorkUnit;
        private String remark;
        private String companyId;
        private String idCard;
        private String streamName0;
        private String streamName1;
        private String projectCode;
        private Object licenceType;
        private Object testDate;
        private Object nowWorkUnit;

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

        public String getCertificateType() {
            return certificateType;
        }

        public void setCertificateType(String certificateType) {
            this.certificateType = certificateType;
        }

        public String getCertificateCode() {
            return certificateCode;
        }

        public void setCertificateCode(String certificateCode) {
            this.certificateCode = certificateCode;
        }

        public String getLicenceNo() {
            return licenceNo;
        }

        public void setLicenceNo(String licenceNo) {
            this.licenceNo = licenceNo;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getTelePhone() {
            return telePhone;
        }

        public void setTelePhone(String telePhone) {
            this.telePhone = telePhone;
        }

        public String getHomePhone() {
            return homePhone;
        }

        public void setHomePhone(String homePhone) {
            this.homePhone = homePhone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getGraduateSchool() {
            return graduateSchool;
        }

        public void setGraduateSchool(String graduateSchool) {
            this.graduateSchool = graduateSchool;
        }

        public long getGraduateDate() {
            return graduateDate;
        }

        public void setGraduateDate(long graduateDate) {
            this.graduateDate = graduateDate;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getXueli() {
            return xueli;
        }

        public void setXueli(String xueli) {
            this.xueli = xueli;
        }

        public String getProfessional() {
            return professional;
        }

        public void setProfessional(String professional) {
            this.professional = professional;
        }

        public String getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(String professionalTitle) {
            this.professionalTitle = professionalTitle;
        }

        public String getPoliticalStatus() {
            return politicalStatus;
        }

        public void setPoliticalStatus(String politicalStatus) {
            this.politicalStatus = politicalStatus;
        }

        public long getWorkDate() {
            return workDate;
        }

        public void setWorkDate(long workDate) {
            this.workDate = workDate;
        }

        public String getOriginalWorkUnit() {
            return originalWorkUnit;
        }

        public void setOriginalWorkUnit(String originalWorkUnit) {
            this.originalWorkUnit = originalWorkUnit;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getStreamName0() {
            return streamName0;
        }

        public void setStreamName0(String streamName0) {
            this.streamName0 = streamName0;
        }

        public String getStreamName1() {
            return streamName1;
        }

        public void setStreamName1(String streamName1) {
            this.streamName1 = streamName1;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public Object getLicenceType() {
            return licenceType;
        }

        public void setLicenceType(Object licenceType) {
            this.licenceType = licenceType;
        }

        public Object getTestDate() {
            return testDate;
        }

        public void setTestDate(Object testDate) {
            this.testDate = testDate;
        }

        public Object getNowWorkUnit() {
            return nowWorkUnit;
        }

        public void setNowWorkUnit(Object nowWorkUnit) {
            this.nowWorkUnit = nowWorkUnit;
        }
    }
}
