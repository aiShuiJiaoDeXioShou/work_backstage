package com.yangteng.workbackstage.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MethodFormatException extends RuntimeException{
    private String message;
    private String MethodPath;
    private String MethodName;
}
