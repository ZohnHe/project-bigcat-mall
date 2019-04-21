package com.mall.bit.cqt.mall.abstracts.impl;
import com.mall.bit.cqt.mall.abstracts.AbstractBaseEntity;
import com.mall.bit.cqt.mall.abstracts.BaseCrudMapper;
import com.mall.bit.cqt.mall.abstracts.BaseCrudService;
import com.mall.bit.cqt.mall.entity.PageInfo;
import com.mall.bit.cqt.mall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractBaseCrudServiceImpl <T extends AbstractBaseEntity,M extends BaseCrudMapper<T>> implements BaseCrudService<T> {
    @Autowired
    protected M mapper;

    @Override
    public void save(T entity){
        //添加
        if(entity.nowInsert(entity)){
            mapper.add(entity);
        }
        //修改
        else {
            mapper.update(entity);
        }
    }

    /**
     * 分页
     * @param entity
     * @param start
     * @param length
     * @param draw
     * @return
     */
    @Override
    public PageInfo<T> page(T entity, int start, int length, int draw) {
        Map<String,Object> params = new HashMap<>();
        //将获取的值传入一个对象（遵循驼峰命名）
        params.put(StringUtils.toLowerCaseFirstOne(entity.getClass().getSimpleName()),entity);
        params.put("start",start);
        params.put("length",length);

        int count = mapper.count(entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(mapper.page(params));

        return pageInfo;
    }

    @Override
    public int count(T entity) {
        return mapper.count(entity);
    }

    @Override
    public T getById(Long id) {
        return mapper.getById(id);
    }

    @Override
    public void delete(T entity) {
         mapper.delete(entity);
    }

    @Override
    public List<T> selectAll(){
        return mapper.selectAll();
    }

}
