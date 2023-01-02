package com.anatkh.serviceBase.exception;



import com.anatkh.commonUtil.emum.BizCodeEnum;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.commonUtil.utils.RT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RT exceptionHandler(Exception e){

        e.printStackTrace();
        log.error(e.getMessage());
        return RT.error().msg(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, Object> argumentException = new HashMap<>();
        bindingResult.getFieldErrors()
                .stream().forEach(item->{
                    String field = item.getField();
                    String defaultMessage = item.getDefaultMessage();
                    argumentException.put(field,defaultMessage);
                });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data",argumentException);
    }

    @ExceptionHandler(ArithmeticException.class)
    public RT arithmeticException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return RT.error().msg("ArithmeticException已被专用处理器捕获");
    }

    @ExceptionHandler(GuliException.class)
    public RT guliExceptionHandler(GuliException e) {
        e.printStackTrace();
        return RT.error().code(e.getCode()).data(e.getMsg());
    }

}
