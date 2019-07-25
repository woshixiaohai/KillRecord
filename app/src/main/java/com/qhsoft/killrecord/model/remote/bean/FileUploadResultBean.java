package com.qhsoft.killrecord.model.remote.bean;

/**
 * Description:
 * Author:lin
 * Date:2017-10-19
 */


public class FileUploadResultBean {

    /**
     * success : true
     * msg : 操作成功
     * obj : {"fileId":"402880c25f32ba45015f32bc403e0002","filePath":"upload\\files\\20171019114518t11AGqIi.jpg","fileName":"545bd70a945afe2ff9cf7.jpg"}
     * code : 200
     * attributes : null
     * jsonStr : {"obj":{"fileId":"402880c25f32ba45015f32bc403e0002","filePath":"upload\\files\\20171019114518t11AGqIi.jpg","fileName":"545bd70a945afe2ff9cf7.jpg"},"msg":"操作成功","success":true}
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
         * fileId : 402880c25f32ba45015f32bc403e0002
         * filePath : upload\files\20171019114518t11AGqIi.jpg
         * fileName : 545bd70a945afe2ff9cf7.jpg
         */

        private String fileId;
        private String filePath;
        private String fileName;
        private String localPath;

        public String getLocalPath() {
            return localPath;
        }

        public void setLocalPath(String localPath) {
            this.localPath = localPath;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}
