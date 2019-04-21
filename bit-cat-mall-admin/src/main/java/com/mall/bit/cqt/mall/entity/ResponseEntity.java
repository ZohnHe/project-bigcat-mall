package com.mall.bit.cqt.mall.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseEntity implements Serializable {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("状态码 ：1-成功，2-账号或密码错误，0-验证码错误")
    private String success;

    @ApiModelProperty("返回消息")
    private String msg;
    @ApiModelProperty("缓存ID")
    private String sessionId;
    @ApiModelProperty("跳转地址")
    private String url;

}
