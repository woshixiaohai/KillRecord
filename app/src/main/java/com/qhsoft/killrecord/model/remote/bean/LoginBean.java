package com.qhsoft.killrecord.model.remote.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Author:lin
 * Date:2017-09-12
 */

public class LoginBean implements Serializable {


    /**
     * obj : {"sonId":"402880b1655f6e8e01655f6fc7a20002","menus":[{"name":"共享信息库","children":[{"name":"商品共享库"},{"name":"经营者共享库"}]},{"name":"基础信息管理","children":[{"name":"原辅料"},{"name":"商品"},{"name":"生产商管理"},{"name":"供货商管理"},{"name":"购货商管理"},{"name":"生产设备"},{"name":"检验方案"},{"name":"人员信息"}]},{"name":"台账管理","children":[{"name":"即时库存"},{"name":"原辅料进货单"},{"name":"原辅料退货单"},{"name":"商品销货单"},{"name":"商品退货单"},{"name":"领用投用单"},{"name":"产品检验单"},{"name":"商品进仓单"},{"name":"现场零售"},{"name":"现场退货"},{"name":"商品处理台账"},{"name":"原辅料处理台账"}]},{"name":"质检管理","children":[{"name":"质检报告"},{"name":"企业自检报告"}]},{"name":"召回管理","children":[{"name":"食安风险分析"},{"name":"食品召回计划"},{"name":"召回措施报告"},{"name":"召回进展报告"},{"name":"召回总结报告"},{"name":"食品召回公告"},{"name":"召回食品处理"},{"name":"不良品销毁记录"}]},{"name":"追溯管理","children":[{"name":"商品追溯"},{"name":"原辅料追溯"}]},{"name":"安全控制","children":[{"name":"关键工序"},{"name":"供应商评价"},{"name":"员工健康检查档案"},{"name":"生产留样记录"}]},{"name":"系统设置","children":[{"name":"账号信息管理"},{"name":"省级数据上传"},{"name":"子账号管理"},{"name":"证照管理"},{"name":"整改通知"},{"name":"行政处罚"},{"name":"帮助中心","children":[{"name":"操作手册"},{"name":"常见问题"}]}]}],"roleCodes":"scba","departName":"信州区","projectCode":"3","gridId":"","sessionId":"E2668DF40FEFCC168A86B3150EDC8E61","userId":"402880b1655f6e8e01655f6fc7b20003","departId":"402866815e4a7032015e4c5b9d4a0033","userName":"5544321","userRealName":"生产测试企业","lastLoginTime":1539833394010}
     * code : 200
     * attributes : null
     * jsonStr :
     */

    private ObjBean obj;
    private int code;
    private boolean success;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
         * sonId : 402880b1655f6e8e01655f6fc7a20002
         * menus : [{"name":"共享信息库","children":[{"name":"商品共享库"},{"name":"经营者共享库"}]},{"name":"基础信息管理","children":[{"name":"原辅料"},{"name":"商品"},{"name":"生产商管理"},{"name":"供货商管理"},{"name":"购货商管理"},{"name":"生产设备"},{"name":"检验方案"},{"name":"人员信息"}]},{"name":"台账管理","children":[{"name":"即时库存"},{"name":"原辅料进货单"},{"name":"原辅料退货单"},{"name":"商品销货单"},{"name":"商品退货单"},{"name":"领用投用单"},{"name":"产品检验单"},{"name":"商品进仓单"},{"name":"现场零售"},{"name":"现场退货"},{"name":"商品处理台账"},{"name":"原辅料处理台账"}]},{"name":"质检管理","children":[{"name":"质检报告"},{"name":"企业自检报告"}]},{"name":"召回管理","children":[{"name":"食安风险分析"},{"name":"食品召回计划"},{"name":"召回措施报告"},{"name":"召回进展报告"},{"name":"召回总结报告"},{"name":"食品召回公告"},{"name":"召回食品处理"},{"name":"不良品销毁记录"}]},{"name":"追溯管理","children":[{"name":"商品追溯"},{"name":"原辅料追溯"}]},{"name":"安全控制","children":[{"name":"关键工序"},{"name":"供应商评价"},{"name":"员工健康检查档案"},{"name":"生产留样记录"}]},{"name":"系统设置","children":[{"name":"账号信息管理"},{"name":"省级数据上传"},{"name":"子账号管理"},{"name":"证照管理"},{"name":"整改通知"},{"name":"行政处罚"},{"name":"帮助中心","children":[{"name":"操作手册"},{"name":"常见问题"}]}]}]
         * roleCodes : scba
         * departName : 信州区
         * projectCode : 3
         * gridId :
         * sessionId : E2668DF40FEFCC168A86B3150EDC8E61
         * userId : 402880b1655f6e8e01655f6fc7b20003
         * departId : 402866815e4a7032015e4c5b9d4a0033
         * userName : 5544321
         * userRealName : 生产测试企业
         * lastLoginTime : 1539833394010
         */

        private String sonId;
        private String roleCodes;
        private String departName;
        private String projectCode;
        private String gridId;
        private String sessionId;
        private String userId;
        private String departId;
        private String userName;
        private String userRealName;
        private long lastLoginTime;
        private List<MenusBean> menus;

        public String getSonId() {
            return sonId;
        }

        public void setSonId(String sonId) {
            this.sonId = sonId;
        }

        public String getRoleCodes() {
            return roleCodes;
        }

        public void setRoleCodes(String roleCodes) {
            this.roleCodes = roleCodes;
        }

        public String getDepartName() {
            return departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getGridId() {
            return gridId;
        }

        public void setGridId(String gridId) {
            this.gridId = gridId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDepartId() {
            return departId;
        }

        public void setDepartId(String departId) {
            this.departId = departId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserRealName() {
            return userRealName;
        }

        public void setUserRealName(String userRealName) {
            this.userRealName = userRealName;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public List<MenusBean> getMenus() {
            return menus;
        }

        public void setMenus(List<MenusBean> menus) {
            this.menus = menus;
        }

        public static class MenusBean implements Serializable{
            /**
             * name : 共享信息库
             * children : [{"name":"商品共享库"},{"name":"经营者共享库"}]
             */

            private String name;
            private List<ChildrenBean> children;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean implements Serializable {
                /**
                 * name : 商品共享库
                 */

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
