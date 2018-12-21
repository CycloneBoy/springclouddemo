package com.cycloneboy.producerserver.domain;


public enum ExceptionEnum {

    /**
     * 成功
     */
    HTTP_SUCCESS("0","success"),

    /**
     * 失败
     */
    HTTP_FAILED("1","failed");

    private String code;

    private String desc;

    ExceptionEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ExceptionEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
