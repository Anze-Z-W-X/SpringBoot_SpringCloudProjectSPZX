package com.anze.spzx.manager.mapper;

import com.anze.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    List<SysMenu> findAll();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    int selectCountById(Long id);

    void removeById(Long id);

    List<SysMenu> findMenusByUserId(Long userId);

    SysMenu selectById(Long id);
}
