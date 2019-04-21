package com.mall.bit.cqt.mall.mapper;


import com.mall.bit.cqt.mall.abstracts.BaseCrudMapper;
import com.mall.bit.cqt.mall.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper extends BaseCrudMapper<Record> {
    /**
     *  获取聊天记录
     * @param record
     * @return
     */
     List<Record> getRecordById(Record record);
}
