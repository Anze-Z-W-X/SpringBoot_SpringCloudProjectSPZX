import request from '@/utils/request'

// 分页查询角色数据
export const GetSysRoleListByPage = (pageNum , pageSize , queryDto) => {
    return request({
        url: '/admin/system/sysRole/findByPage/' + pageNum + "/" + pageSize,
        method: 'post',
        data: queryDto
    })
}

//角色添加
export const SaveSysRole = (sysRole)=>{
    return request({
        url: '/admin/system/sysRole/saveSysRole',
        method: 'post',
        data: sysRole
    })
}

//角色修改
export const UpdateSysRole = (sysRole)=>{
    return request({
        url: '/admin/system/sysRole/updateSysRole',
        method: 'put',
        data: sysRole
    })
}

//角色删除
export const DeleteById = (roleId)=>{
    return request({
        url: `/admin/system/sysRole/deleteById/${roleId}`,
        method: 'delete'
    })
}

export const GetAllRoleList = (userId) => {
    return request({
        url: `/admin/system/sysRole/findAllRoles/${userId}`,
        method: 'get'
    })
}

// 查询指定角色所对应的菜单id
export const GetSysRoleMenuIds = (roleId) => {
    return request({
        url: "/admin/system/sysRoleMenu/findSysRoleMenuByRoleId/"+ roleId,
        method: 'get'
    })
}
// 根据角色分配菜单请求方法
export const DoAssignMenuIdToSysRole = (assignMenuDto) => {
    return request({
        url: "/admin/system/sysRoleMenu/doAssign",
        method: 'post',
        data: assignMenuDto
    })
}