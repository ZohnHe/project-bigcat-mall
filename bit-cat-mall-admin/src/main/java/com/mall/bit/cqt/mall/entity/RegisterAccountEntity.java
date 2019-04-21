package com.mall.bit.cqt.mall.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterAccountEntity {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空!")
    private String register_username;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空!")
    private String register_password;

    public String getRegister_username() {
        return register_username;
    }

    public void setRegister_username(String register_username) {
        this.register_username = register_username;
    }

    public String getRegister_password() {
        return register_password;
    }

    public void setRegister_password(String register_password) {
        this.register_password = register_password;
    }
}
