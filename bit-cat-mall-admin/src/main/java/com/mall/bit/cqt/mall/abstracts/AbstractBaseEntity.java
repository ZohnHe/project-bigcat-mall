package com.mall.bit.cqt.mall.abstracts;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public abstract class AbstractBaseEntity implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("创建时间")
    private Date created;
    @ApiModelProperty("修改时间")
    private Date updated;

    /**
     * 添加和修改的判断
     * @param entity
     * @return
     */
    public boolean nowInsert(AbstractBaseEntity entity){
        Date currentData = new Date();
        entity.setUpdated(currentData);
        //添加
        if(entity.getId()==null){
            entity.setCreated(currentData);
            return true;
        }
        return false;
    }

}
