package com.cycloneboy.producerserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by CycloneBoy on 2018-12-21 22:36
 */
@Data
@AllArgsConstructor
public class BaseResponse {

    private String code;

    private String message;

    private Object value;

    public BaseResponse(){

    }

    public  BaseResponse(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getDesc();
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
