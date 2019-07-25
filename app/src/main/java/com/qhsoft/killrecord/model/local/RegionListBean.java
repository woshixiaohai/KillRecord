package com.qhsoft.killrecord.model.local;

import java.util.List;

public class RegionListBean {


    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * local_id : 2
         * parent_id : 0
         * region_name : 北京
         * region_type : 1
         * agency_id : 0
         * son : [{"id":"37","local_id":"0","parent_id":"2","region_name":"北京市","region_type":"2","agency_id":"0","son":[{"id":"403","local_id":"0","parent_id":"37","region_name":"东城区","region_type":"3","agency_id":"0"},{"id":"404","local_id":"0","parent_id":"37","region_name":"西城区","region_type":"3","agency_id":"0"},{"id":"405","local_id":"0","parent_id":"37","region_name":"崇文区","region_type":"3","agency_id":"0"},{"id":"406","local_id":"0","parent_id":"37","region_name":"宣武区","region_type":"3","agency_id":"0"},{"id":"407","local_id":"0","parent_id":"37","region_name":"朝阳区","region_type":"3","agency_id":"0"},{"id":"408","local_id":"0","parent_id":"37","region_name":"丰台区","region_type":"3","agency_id":"0"},{"id":"409","local_id":"0","parent_id":"37","region_name":"石景山区","region_type":"3","agency_id":"0"},{"id":"410","local_id":"0","parent_id":"37","region_name":"海淀区","region_type":"3","agency_id":"0"},{"id":"411","local_id":"0","parent_id":"37","region_name":"门头沟区","region_type":"3","agency_id":"0"},{"id":"412","local_id":"0","parent_id":"37","region_name":"房山区","region_type":"3","agency_id":"0"},{"id":"413","local_id":"0","parent_id":"37","region_name":"通州区","region_type":"3","agency_id":"0"},{"id":"414","local_id":"0","parent_id":"37","region_name":"顺义区","region_type":"3","agency_id":"0"},{"id":"415","local_id":"0","parent_id":"37","region_name":"昌平区","region_type":"3","agency_id":"0"},{"id":"416","local_id":"0","parent_id":"37","region_name":"大兴区","region_type":"3","agency_id":"0"},{"id":"417","local_id":"0","parent_id":"37","region_name":"怀柔区","region_type":"3","agency_id":"0"},{"id":"418","local_id":"0","parent_id":"37","region_name":"平谷区","region_type":"3","agency_id":"0","son":[{"id":"4056","local_id":"0","parent_id":"418","region_name":"0-09","region_type":"4","agency_id":"0"}]},{"id":"419","local_id":"0","parent_id":"37","region_name":"密云县","region_type":"3","agency_id":"0"},{"id":"420","local_id":"0","parent_id":"37","region_name":"延庆县","region_type":"3","agency_id":"0"},{"id":"421","local_id":"0","parent_id":"37","region_name":"其它区","region_type":"3","agency_id":"0"}]}]
         */

        private String id;
        private String local_id;
        private String parent_id;
        private String region_name;
        private String region_type;
        private String agency_id;
        private List<DataBean> son;

        public List<DataBean> getSon() {
            return son;
        }

        public void setSon(List<DataBean> son) {
            this.son = son;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocal_id() {
            return local_id;
        }

        public void setLocal_id(String local_id) {
            this.local_id = local_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getRegion_type() {
            return region_type;
        }

        public void setRegion_type(String region_type) {
            this.region_type = region_type;
        }

        public String getAgency_id() {
            return agency_id;
        }

        public void setAgency_id(String agency_id) {
            this.agency_id = agency_id;
        }


    }


}
