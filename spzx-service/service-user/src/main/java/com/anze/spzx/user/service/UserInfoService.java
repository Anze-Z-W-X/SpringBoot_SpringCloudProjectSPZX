package com.anze.spzx.user.service;

import com.anze.spzx.model.dto.h5.UserLoginDto;
import com.anze.spzx.model.dto.h5.UserRegisterDto;
import com.anze.spzx.model.vo.h5.UserInfoVo;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    Object login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
