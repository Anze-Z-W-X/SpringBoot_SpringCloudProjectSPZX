package com.anze.spzx.manager.service;

import com.anze.spzx.model.dto.system.AssginRoleDto;
import com.anze.spzx.model.dto.system.LoginDto;
import com.anze.spzx.model.dto.system.SysUserDto;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);
}
