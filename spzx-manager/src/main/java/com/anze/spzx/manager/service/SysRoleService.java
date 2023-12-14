package com.anze.spzx.manager.service;

import com.anze.spzx.model.dto.system.SysRoleDto;
import com.anze.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);
}
