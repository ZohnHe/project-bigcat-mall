package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.abstracts.BaseCrudService;
import com.mall.bit.cqt.mall.entity.User;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/19 14:41
 * @Version 1.0.0
 */
public interface UserService extends BaseCrudService<User> {
    User getByLoginId(User user);
    Integer add(User user);
    void update(User user);
}
