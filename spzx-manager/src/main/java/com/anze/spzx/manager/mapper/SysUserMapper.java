package com.anze.spzx.manager.mapper;

import com.anze.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser selectByUserName(String userName);
}
