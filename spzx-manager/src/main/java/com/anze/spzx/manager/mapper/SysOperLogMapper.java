package com.anze.spzx.manager.mapper;

import com.anze.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogMapper {
    public abstract void insert(SysOperLog sysOperLog);
}
