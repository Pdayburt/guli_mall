package com.anatkh.serviceBase.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code;
    private String msg;
}
