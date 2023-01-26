package com.forum.vo;

import com.forum.common.enums.ExceptionEnums;
import lombok.Data;

@Data
public class ExceptionResult {
    private int status;
    private String msg;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums enums){
        this.status = enums.getCode();
        this.msg = enums.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
