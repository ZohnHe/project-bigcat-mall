package com.mall.bit.cqt.mall.mapper;

import com.mall.bit.cqt.mall.abstracts.BaseCrudMapper;
import com.mall.bit.cqt.mall.entity.Express;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressMapper extends BaseCrudMapper<Express> {
    Express getById(int uid);
}
