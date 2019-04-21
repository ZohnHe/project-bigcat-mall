package com.mall.bit.cqt.mall.mapper;

import com.mall.bit.cqt.mall.abstracts.BaseCrudMapper;
import com.mall.bit.cqt.mall.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface UserMapper extends BaseCrudMapper<User> {
    User getByLoginId(User user);
    Integer add(User user);
    void update(User user);
    User getByUsername(String username);

}
