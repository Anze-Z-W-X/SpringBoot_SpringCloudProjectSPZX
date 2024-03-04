package com.anze.spzx.manager.service.impl;

import com.anze.spzx.common.log.service.AsyncOperLogService;
import com.anze.spzx.manager.mapper.SysOperLogMapper;
import com.anze.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Async      // 异步执行保存日志操作
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
