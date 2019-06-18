package com.paperScan.v1.pojo;

public interface IStatusMessage {
    String FILE_UPLOAD_ERROR = "文件上传错误";
    String FILE_UPLOAD_NULL ="上传图片为空文件" ;

    String getCode();
    String getMessage();

    enum SystemStatus implements IStatusMessage {

        SUCCESS("1000", "操作成功"), //请求成功
        ERROR("1001", "网络异常，请稍后重试~"),
        FILE_UPLOAD_NULL("1002","上传图片为空文件"),      //请求失败
        PARAM_ERROR("1003","参数错误"),;
        private String code;
        private String message;

        SystemStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }
        public String getCode() {
            return this.code;
        }
        public String getMessage() {
            return this.message;
        }
    }
}