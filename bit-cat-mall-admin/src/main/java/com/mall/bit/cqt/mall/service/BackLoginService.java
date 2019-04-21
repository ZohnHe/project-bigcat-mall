package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.entity.User;

public interface BackLoginService {
    User Login(String LoginId);
    Integer add(User user);
}
