package com.anze.spzx.manager.service.impl;

import com.anze.spzx.common.exception.AnzeException;
import com.anze.spzx.manager.mapper.SysMenuMapper;
import com.anze.spzx.manager.service.SysMenuService;
import com.anze.spzx.model.entity.system.SysMenu;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.anze.spzx.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        //1.先查询所有的菜单，返回list
        List<SysMenu> list = sysMenuMapper.findAll();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        //2.调用工具类的方法，把list封装成前端要求的Element Plus需要的树形格式
        List<SysMenu> treeList = MenuHelper.buildTree(list);
        return treeList;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        //根据当前菜单id来查看待删除菜单下是否有子菜单
        int count = sysMenuMapper.selectCountById(id);
        if(count>0){
            throw new AnzeException(ResultCodeEnum.NODE_ERROR);
        }

        sysMenuMapper.removeById(id);
    }
}
