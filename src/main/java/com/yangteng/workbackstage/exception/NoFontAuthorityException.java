package com.yangteng.workbackstage.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class NoFontAuthorityException extends RuntimeException{
    private String message;
}
