package com.mall.bit.cqt.mall.abstracts;

import com.mall.bit.cqt.mall.entity.PageInfo;

import java.util.List;

/**
 * 通用的CRUD业务逻辑接口
 * @param <T>
 */

public interface BaseCrudService<T extends AbstractBaseEntity> {
    /**
     *  查询所有数据
     * @return
     */
    List<T> selectAll();

    PageInfo<T> page(T entity, int start, int length, int draw);

    int count(T entity);

    void save(T entity);

    T getById(Long id);

    void delete(T entity);


}
