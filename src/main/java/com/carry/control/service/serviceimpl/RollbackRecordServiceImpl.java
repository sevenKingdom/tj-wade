package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.RollbackRecordMapper;
import com.carry.control.model.po.RollbackRecord;
import com.carry.control.service.RollbackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by songxianying on 17/8/27.
 */
@Service
public class RollbackRecordServiceImpl implements RollbackRecordService {

    @Autowired
    private RollbackRecordMapper rollbackRecordMapper;

    @Override
    public int addRollbackRecord(RollbackRecord rollbackRecord) {
        return rollbackRecordMapper.addRollbackRecord(rollbackRecord);
    }
    @Override
    public List<RollbackRecord> findByDepartment(String department ) {
        return rollbackRecordMapper.findByDepartment(department);
    }
}
