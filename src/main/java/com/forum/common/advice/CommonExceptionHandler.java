package com.forum.common.advice;

import com.forum.common.exception.RuntimeCommonException;
import com.forum.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handleException(RuntimeCommonException e){
        return ResponseEntity.status(e.getEnums().getCode())
                .body(new ExceptionResult(e.getEnums()));
    }
}
