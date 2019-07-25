package com.qhsoft.killrecord.model.remote.bean;

/**
 * Description:
 * Author:lin
 * Date:2017-10-24
 */


public class FileDownloadResultBean {


    /**
     * success : true
     * msg : 操作成功
     * obj : {"fileName":"ADD_H.png","filePath":"upload/files/20161110150217F84LLIqH.png","fileData":"H4sIAAAAAAAAAAEvAtD9iVBORw0KGgoAAAANSUhEUgAAABMAAAASCAYAAAC5DOVpAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAAA7DAAAOwwHHb6hkAAABk0lEQVQ4y6XUu2sWQRQF8N+3+cQmqEEEYRACphCx0NIHRCSgf4CvZosIWqWw2Tp/wFQSKwUfDBYWJmlsBEEhWGgriGg7IlgoIYKPxFh838Ky2ShJTrOzZ+49e8+dvdPTgZDiaVzFFMbRR8YSFrCQy+pPO6/XEjmM27jg33iLmVxWL5tk0RA6hTcdQrO4iNUGdwzPQ4rXNoiFFCewiLGOKuZyWT3BhxY/gjshxfPtyu7jwCaW6t6sduyN4EFIcRSKkOIUztg+DmKmruzSDoRqXGFw5Oca5Hf8agWuD5/L+NrgC+wdro+HFPf3Qoo/sBs3c1nd2ko5IcWTeDV8PVF0VLIVrDfWP3shxXc4MiRW8LuVMJ7LajmkuISjm9iEsT5eN8RGO75eT8ke3f8hvM9l9a3Aox3YrPEQilxWzwwGeLv4jLnaN0zjyybBdUy/Y28N07msVpr9qAf9Kfa1EmYNbonHLcE13Mhlda/d3FpwAndx9j/WPuJ6LqsXTbLXFRlSnMRlTOIQduGTwcnPYzGX1YbB/wu5RmVIVmfuUQAAAABJRU5ErkJggkEtbVAvAgAA"}
     * attributes : null
     * jsonStr : {"msg":"操作成功","success":true,"obj":{"fileName":"ADD_H.png","filePath":"upload/files/20161110150217F84LLIqH.png","fileData":"H4sIAAAAAAAAAAEvAtD9iVBORw0KGgoAAAANSUhEUgAAABMAAAASCAYAAAC5DOVpAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAAA7DAAAOwwHHb6hkAAABk0lEQVQ4y6XUu2sWQRQF8N+3+cQmqEEEYRACphCx0NIHRCSgf4CvZosIWqWw2Tp/wFQSKwUfDBYWJmlsBEEhWGgriGg7IlgoIYKPxFh838Ky2ShJTrOzZ+49e8+dvdPTgZDiaVzFFMbRR8YSFrCQy+pPO6/XEjmM27jg33iLmVxWL5tk0RA6hTcdQrO4iNUGdwzPQ4rXNoiFFCewiLGOKuZyWT3BhxY/gjshxfPtyu7jwCaW6t6sduyN4EFIcRSKkOIUztg+DmKmruzSDoRqXGFw5Oca5Hf8agWuD5/L+NrgC+wdro+HFPf3Qoo/sBs3c1nd2ko5IcWTeDV8PVF0VLIVrDfWP3shxXc4MiRW8LuVMJ7LajmkuISjm9iEsT5eN8RGO75eT8ke3f8hvM9l9a3Aox3YrPEQilxWzwwGeLv4jLnaN0zjyybBdUy/Y28N07msVpr9qAf9Kfa1EmYNbonHLcE13Mhlda/d3FpwAndx9j/WPuJ6LqsXTbLXFRlSnMRlTOIQduGTwcnPYzGX1YbB/wu5RmVIVmfuUQAAAABJRU5ErkJggkEtbVAvAgAA"}}
     */

    private boolean success;
    private String msg;
    private ObjBean obj;
    private Object attributes;
    private String jsonStr;

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

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public static class ObjBean {
        /**
         * fileName : ADD_H.png
         * filePath : upload/files/20161110150217F84LLIqH.png
         * fileData : H4sIAAAAAAAAAAEvAtD9iVBORw0KGgoAAAANSUhEUgAAABMAAAASCAYAAAC5DOVpAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAAA7DAAAOwwHHb6hkAAABk0lEQVQ4y6XUu2sWQRQF8N+3+cQmqEEEYRACphCx0NIHRCSgf4CvZosIWqWw2Tp/wFQSKwUfDBYWJmlsBEEhWGgriGg7IlgoIYKPxFh838Ky2ShJTrOzZ+49e8+dvdPTgZDiaVzFFMbRR8YSFrCQy+pPO6/XEjmM27jg33iLmVxWL5tk0RA6hTcdQrO4iNUGdwzPQ4rXNoiFFCewiLGOKuZyWT3BhxY/gjshxfPtyu7jwCaW6t6sduyN4EFIcRSKkOIUztg+DmKmruzSDoRqXGFw5Oca5Hf8agWuD5/L+NrgC+wdro+HFPf3Qoo/sBs3c1nd2ko5IcWTeDV8PVF0VLIVrDfWP3shxXc4MiRW8LuVMJ7LajmkuISjm9iEsT5eN8RGO75eT8ke3f8hvM9l9a3Aox3YrPEQilxWzwwGeLv4jLnaN0zjyybBdUy/Y28N07msVpr9qAf9Kfa1EmYNbonHLcE13Mhlda/d3FpwAndx9j/WPuJ6LqsXTbLXFRlSnMRlTOIQduGTwcnPYzGX1YbB/wu5RmVIVmfuUQAAAABJRU5ErkJggkEtbVAvAgAA
         */

        private String fileName;
        private String filePath;
        private String fileData;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileData() {
            return fileData;
        }

        public void setFileData(String fileData) {
            this.fileData = fileData;
        }
    }
}
