package com.tonot.common.exception;

import com.tonot.common.api.RestCode;
import com.tonot.common.api.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandler {


    /**
     * 自定义异常捕捉器
     *
     * @param request
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RestException.class)
    @ResponseBody
    public RestResponse handlerRestException(HttpServletRequest request, RestException e) {
        RestResponse response;
        RestCode.CodeEnum respCode = e.getEnumCode();
        respCode = Optional.ofNullable(respCode).orElse(RestCode.CodeEnum.UNKNOWN_ERROR);
        log.error("ExceptionHandler.handlerRestException code:{},msg:{}", respCode.getCode(), respCode.getMsg());
        response = new RestResponse(respCode.getCode(), respCode.getMsg());
        return response;
    }

    /**
     * 字段验证异常捕捉器
     *
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    @ResponseBody
    public RestResponse handerValidateException(ValidationException exception) {
        StringBuilder msg = new StringBuilder();
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                msg.append(item.getMessage());
            }
        }
        return new RestResponse(RestCode.CodeEnum.PARAM_ERROR.getCode(), msg.toString());
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<List<FieldError>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private RestResponse<List<FieldError>> processFieldErrors(List<FieldError> fieldErrors) {
        RestCode restCode = new RestCode(RestCode.CodeEnum.PARAM_ERROR);
        List<FieldError> errors=new ArrayList<>(fieldErrors.size());
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            errors.add(new FieldError(fieldError.getField(), fieldError.getDefaultMessage(),""));
        }

        return new RestResponse<>(restCode.getCode(),restCode.getMsg(),errors);
    }

    /**
     * 默认异常捕捉
     *
     * @param request
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse<String> handlerException(HttpServletRequest request, RuntimeException e) {
        RestResponse response;
        log.error("ExceptionHandler.handlerException:{}", e);
        response = new RestResponse(RestCode.CodeEnum.ERROR, e.getMessage());
        return response;
    }
}
