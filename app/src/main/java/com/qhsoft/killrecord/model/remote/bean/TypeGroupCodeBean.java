package com.qhsoft.killrecord.model.remote.bean;

import java.io.Serializable;
import java.util.List;

public class TypeGroupCodeBean implements Serializable {

    /**
     * success : true
     * msg : 操作成功
     * obj : {"id":"402880af5e94492c015e944f7e320002","typegroupname":"处理状态","typegroupcode":"OA_DEAL_STATE","projectCode":"JGPT","userCustom":1,"tstypes":[{"id":"402880af5e94492c015e94501de80004","typename":"未处理","typecode":"0","tstype":null,"tstypes":[],"tstypegroup":null},{"id":"402880af5e94492c015e945045cb0006","typename":"已处理","typecode":"1","tstype":null,"tstypes":[],"tstypegroup":null}]}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"id":"402880af5e94492c015e944f7e320002","projectCode":"JGPT","tSTypes":[{"id":"402880af5e94492c015e94501de80004","tSTypes":[],"typecode":"0","typename":"未处理"},{"id":"402880af5e94492c015e945045cb0006","tSTypes":[],"typecode":"1","typename":"已处理"}],"typegroupcode":"OA_DEAL_STATE","typegroupname":"处理状态","userCustom":1},"msg":"操作成功","success":true}
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



    public static class ObjBean implements Serializable {
        /**
         * id : 402880af5e94492c015e944f7e320002
         * typegroupname : 处理状态
         * typegroupcode : OA_DEAL_STATE
         * projectCode : JGPT
         * userCustom : 1
         * tstypes : [{"id":"402880af5e94492c015e94501de80004","typename":"未处理","typecode":"0","tstype":null,"tstypes":[],"tstypegroup":null},{"id":"402880af5e94492c015e945045cb0006","typename":"已处理","typecode":"1","tstype":null,"tstypes":[],"tstypegroup":null}]
         */

        private String id;
        private String typegroupname;
        private String typegroupcode;
        private String projectCode;
        private int userCustom;
        private List<TstypesBean> tstypes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypegroupname() {
            return typegroupname;
        }

        public void setTypegroupname(String typegroupname) {
            this.typegroupname = typegroupname;
        }

        public String getTypegroupcode() {
            return typegroupcode;
        }

        public void setTypegroupcode(String typegroupcode) {
            this.typegroupcode = typegroupcode;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public int getUserCustom() {
            return userCustom;
        }

        public void setUserCustom(int userCustom) {
            this.userCustom = userCustom;
        }

        public List<TstypesBean> getTstypes() {
            return tstypes;
        }

        public void setTstypes(List<TstypesBean> tstypes) {
            this.tstypes = tstypes;
        }

        public static class TstypesBean implements Serializable {
            /**
             * id : 402880af5e94492c015e94501de80004
             * typename : 未处理
             * typecode : 0
             * tstype : null
             * tstypes : []
             * tstypegroup : null
             */

            private String id;
            private String typename;
            private String typecode;
            private Object tstype;
            private Object tstypegroup;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getTypecode() {
                return typecode;
            }

            public void setTypecode(String typecode) {
                this.typecode = typecode;
            }

            public Object getTstype() {
                return tstype;
            }

            public void setTstype(Object tstype) {
                this.tstype = tstype;
            }

            public Object getTstypegroup() {
                return tstypegroup;
            }

            public void setTstypegroup(Object tstypegroup) {
                this.tstypegroup = tstypegroup;
            }


        }
    }

}