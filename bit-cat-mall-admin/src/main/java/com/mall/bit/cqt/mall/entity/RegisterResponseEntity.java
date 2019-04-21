package com.mall.bit.cqt.mall.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterResponseEntity implements Serializable {

    @ApiModelProperty("状态码 ：1-成功，2-用户名已存在，0或其他数字-信息已存在")
    private String success;

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("跳转地址")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
