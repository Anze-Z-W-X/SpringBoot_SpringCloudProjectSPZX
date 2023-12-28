package com.anze.spzx.manager.service.impl;

import com.anze.spzx.manager.mapper.SysRoleMapper;
import com.anze.spzx.manager.mapper.SysRoleUserMapper;
import com.anze.spzx.manager.service.SysRoleService;
import com.anze.spzx.model.dto.system.SysRoleDto;
import com.anze.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 接口实现类
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper ;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize) ;
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto) ;
        PageInfo<SysRole> pageInfo = new PageInfo(sysRoleList) ;
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void delById(Long roleId) {
        sysRoleMapper.delete(roleId);
    }

    @Override
    public Map<String, Object> findAll(Long userId) {
        //1.查询所有角色
        List<SysRole> roleList = sysRoleMapper.findAll();
        //2.分配过的角色列表
        //根据userId查询用户分配过的角色id列表
        List<Long> roleIds = sysRoleUserMapper.selectRoleIdsByUserId(userId);
        HashMap<String , Object> map = new HashMap<>();
        map.put("allRoleList",roleList);
        map.put("sysUserRoles",roleIds);
        return map;
    }
}
