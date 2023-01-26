package com.forum.common.exception;

import com.forum.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RuntimeCommonException extends RuntimeException {

    private ExceptionEnums enums;
}
