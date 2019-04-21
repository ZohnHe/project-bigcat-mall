package com.mall.bit.cqt.mall.common;

/**
 * 全局返回编码定义
 */
public enum ResponseCode {
    FAIL("500", "网络异常"),
    NOT_FOUND("404", "页面未找到"),
    OK("200", "正常");

    private String code;
    private String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }


}
