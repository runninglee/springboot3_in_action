package com.sp3.chapter9.exception;



import com.sp3.chapter9.util.api.ResultJson;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(GraceException.class)
    public ResultJson<Object> handler(GraceException e) {
        return ResultJson.failed(e.getMessage(), e.code);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson<Object> handler(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getFieldErrors();
        return ResultJson.validateFailed(errors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultJson<Object> handler(NoHandlerFoundException e) {
        return ResultJson.failed("请求路由不存在", 404);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResultJson<Object> handler(NoResourceFoundException e) {
        return ResultJson.failed("请求路由不存在", 404);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultJson<Object> handler(DataIntegrityViolationException e) {
        if (e.getMessage().contains("Duplicate entry")) {
            return ResultJson.failed("数据重复,禁止重复添加");
        } else {
            return ResultJson.failed(e.getMessage());
        }
    }

    @ExceptionHandler(Throwable.class)
    public ResultJson<Object> handler(Exception e) {
        return ResultJson.failed(e.getMessage());
    }
}
