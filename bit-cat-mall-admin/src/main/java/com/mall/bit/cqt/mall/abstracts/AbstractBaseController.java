package com.mall.bit.cqt.mall.abstracts;

import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.entity.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseController <T extends AbstractBaseEntity,S extends BaseCrudService<T>> {
    @Autowired
    protected S service;

    /**
     * 根据id获取对象信息
     * @param id
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
//    @ModelAttribute
//    public T getById(Long id) throws IllegalAccessException, InstantiationException {
//        if(id == null){
//            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
//            Class clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
//            T o = (T)clazz.newInstance();
//            return o;
//        }
//        else {
//            return service.getById(id);
//        }
//    }


}
