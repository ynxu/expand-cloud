package com.tonot.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Response<T>", description = "同意请求返回类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    @ApiModelProperty(name = "code", value = "返回结果码", dataType = "int")
    private int code;
    @ApiModelProperty(name = "msg", value = "返回结果描述", dataType = "String")
    private String msg="success";

    @ApiModelProperty(name = "data", value = "返回体", dataType = "T")
    private T data;

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(RestCode.CodeEnum restCode) {
        this.code = restCode.getCode();
        this.msg = restCode.getMsg();
    }

    public RestResponse(RestCode.CodeEnum restCode, T data) {
        this.code = restCode.getCode();
        this.msg = restCode.getMsg();
        this.data = data;
    }

    public void setCodeEnum(RestCode.CodeEnum code){
        this.code = code.getCode();
        this.msg = code.getMsg();
    }


}
