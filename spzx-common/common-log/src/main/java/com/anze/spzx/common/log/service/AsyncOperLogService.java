package com.anze.spzx.common.log.service;

import com.anze.spzx.model.entity.system.SysOperLog;

public interface AsyncOperLogService {
    void saveSysOperLog(SysOperLog sysOperLog);
}
