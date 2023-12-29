package com.anze.spzx.manager.service.impl;

import com.anze.spzx.manager.mapper.SysRoleMenuMapper;
import com.anze.spzx.manager.service.SysMenuService;
import com.anze.spzx.manager.service.SysRoleMenuService;
import com.anze.spzx.model.dto.system.AssginMenuDto;
import com.anze.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有菜单
        List<SysMenu> sysMenuList = sysMenuService.findNodes();
        //查询角色分配id列表
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        HashMap<String , Object> map = new HashMap<>();
        map.put("sysMenuList",sysMenuList);
        map.put("roleMenuIds",roleMenuIds);
        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        //删除角色之前分配过的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        //保存分配数据
        List<Map<String ,Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo!=null && menuInfo.size()>0){//角色分配了菜单
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
