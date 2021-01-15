package com.data.itsv.mapper;

import com.data.itsv.model.AuthOperationLog;

import java.util.List;

public interface AuthOperationLogMapper {
    List<AuthOperationLog> selectOperationLogList();
}
