package com.anze.spzx.manager.service;

import com.anze.spzx.model.entity.system.SysMenu;
import com.anze.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void removeById(Long id);

    List<SysMenuVo> findMenusByUserId();
}
