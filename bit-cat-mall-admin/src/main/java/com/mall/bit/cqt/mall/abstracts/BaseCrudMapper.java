package com.mall.bit.cqt.mall.abstracts;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 通用的CRUD访问接口
 * @param <T>
 */
@Repository
public interface BaseCrudMapper <T extends AbstractBaseEntity>{
    /**
     * 查询所有
     * @return
     */
    List<T> selectAll();

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<T> page(Map<String, Object> params);

    /**
     * 查询数据条数
     * @param entity
     * @return
     */
    int count(T entity);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 添加
     * @param entity
     */
    Integer add(T entity);

    /**
     * 删除
     * @param entity
     */
    void delete(T entity);

    /**
     * 修改
     * @param entity
     */
    void update(T entity);

}
