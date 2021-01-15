package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthOperationLogMapper;
import com.data.itsv.model.AuthOperationLog;
import com.data.itsv.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:34 2018/4/22
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    AuthOperationLogMapper authOperationLogMapper;

    @Override
    public List<AuthOperationLog> getOperationList() {
        return authOperationLogMapper.selectOperationLogList();
    }
}
