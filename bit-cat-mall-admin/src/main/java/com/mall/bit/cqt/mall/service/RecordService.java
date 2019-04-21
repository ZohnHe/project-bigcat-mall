package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.abstracts.BaseCrudService;
import com.mall.bit.cqt.mall.entity.Record;

import java.util.List;

public interface RecordService extends BaseCrudService<Record> {
    /**
     * 获取聊天记录
     * @param record
     * @return
     */
    List<Record> getRecordList(Record record);
}
