package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.entity.User;

public interface LoginService {
    User Login(String LoginId, String password);
    Integer add(String username, String password);
}
