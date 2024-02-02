package com.anze.spzx.manager.service.impl;

import com.anze.spzx.common.exception.AnzeException;
import com.anze.spzx.manager.mapper.SysMenuMapper;
import com.anze.spzx.manager.mapper.SysRoleMenuMapper;
import com.anze.spzx.manager.service.SysMenuService;
import com.anze.spzx.model.entity.system.SysMenu;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.utils.BeanCopyUtil;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.anze.spzx.model.vo.system.SysMenuVo;
import com.anze.spzx.utils.AuthContextUtil;
import com.anze.spzx.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
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
        // 新添加一个菜单，那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu) ;
    }

    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {
        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());

        if (parentMenu != null) {

            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu);

        }
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

    @Override
    public List<SysMenuVo> findMenusByUserId() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        //根据userId获取菜单
        List<SysMenu> syMenuList = sysMenuMapper.findMenusByUserId(userId);
        //封装要求数据格式
        List<SysMenu> sysMenuList = MenuHelper.buildTree(syMenuList);
        List<SysMenuVo> sysMenuVos = buildMenus(sysMenuList);
        return sysMenuVos;
    }
    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
