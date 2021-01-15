package com.data.itsv.service;


import com.data.itsv.model.AuthOperationLog;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:30 2018/4/22
 */
public interface OperationLogService {

    /**
     * description TODO
     *
     * @return java.util.List<com.usthe.bootshiro.domain.bo.AuthOperationLog>
     */
    List<AuthOperationLog> getOperationList();
}
