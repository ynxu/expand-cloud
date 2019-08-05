package com.tonot.common.exception;

import com.tonot.common.api.RestCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;

/**
 * 基础自定义异常捕捉类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestException extends RuntimeException {

    private int code;
    private String message;

    public RestException(RestCode respCode){
        this.code = respCode.getCode();
        this.message = respCode.getMsg();
    }


    public RestCode.CodeEnum getEnumCode(){
        RestCode.CodeEnum code ;
        try {
            Constructor cstr = RestCode.CodeEnum.class.getDeclaredConstructor(String.class, int.class);
            ReflectionFactory reflection =
                    ReflectionFactory.getReflectionFactory();
            code =(RestCode.CodeEnum) reflection.newConstructorAccessor(cstr).newInstance(new Object[]{this.code, this.message});
        }catch (Exception e){

        }finally {
            code = RestCode.CodeEnum.UNKNOWN_ERROR;
        }
        return code;
    }


}
