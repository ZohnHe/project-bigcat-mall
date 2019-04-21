package com.mall.bit.cqt.mall.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class AccountEntity {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空!")
    private String loginId;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空!")
    private String loginPwd;

    @ApiModelProperty("记住用户名密码")
    private Boolean remember;

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
