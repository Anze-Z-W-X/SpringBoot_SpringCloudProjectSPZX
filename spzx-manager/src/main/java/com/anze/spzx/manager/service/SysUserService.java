package com.anze.spzx.manager.service;

import com.anze.spzx.model.dto.system.LoginDto;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);
}
