package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.abstracts.impl.AbstractBaseCrudServiceImpl;
import com.mall.bit.cqt.mall.entity.Record;
import com.mall.bit.cqt.mall.mapper.RecordMapper;
import com.mall.bit.cqt.mall.service.RecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl extends AbstractBaseCrudServiceImpl<Record, RecordMapper> implements RecordService {


    @Override
    public List<Record> getRecordList(Record record) {

        List<Record> recordList = mapper.getRecordById(record);
        return recordList;
    }
}
