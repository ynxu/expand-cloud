package com.tonot.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestCode {

    public static enum CodeEnum {
        SUCCESS(200, "成功"),
        ERROR(700, "失败"),
        UNKNOWN_ERROR(701, "未知错误"),
        UPLOAD_FILE_FAILD(702, "上传文件失败"),
        PARAM_ERROR(703, "参数不合法"),
        ;
        private int code;
        private String msg;

        CodeEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    private int code;
    private String msg;

    public RestCode(CodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

}
