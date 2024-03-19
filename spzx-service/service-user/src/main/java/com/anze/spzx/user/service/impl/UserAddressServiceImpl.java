package com.anze.spzx.user.service.impl;

import com.anze.spzx.common.util.AuthContextUtil;
import com.anze.spzx.model.entity.user.UserAddress;
import com.anze.spzx.user.mapper.UserAddressMapper;
import com.anze.spzx.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }
}