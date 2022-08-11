package com.yangteng.workbackstage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.entity.ua.Authority;
import com.yangteng.workbackstage.entity.ua.Role;
import com.yangteng.workbackstage.entity.ua.RoleForAuthority;
import com.yangteng.workbackstage.entity.ua.UserForRole;
import com.yangteng.workbackstage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthorityManagementServiceImpl implements UserAuthorityManagementService {
    @Autowired
    private IUserForRoleService userForRoleService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleForAuthorityService roleForAuthorityService;

    @Autowired
    private IAuthorityService authorityService;

    /**
     * 通过角色集合拿到所有的权限
     *
     * @param roles
     * @return 权限集合 List<Authority>
     */
    public List<Authority> getAuthorityList(List<Role> roles) {
        // 拿到用户所有角色的id
        final List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());

        // 用角色id到关联表去取关联类
        final List<RoleForAuthority> roleForAuthoritys = roleForAuthorityService.list(
                Wrappers.lambdaQuery(new RoleForAuthority())
                        .in(RoleForAuthority::getRoleId, roleIds)
        );

        // 通过关联类取到所有权限
        List<Authority> authorityList = roleForAuthoritys.stream()
                .map(RoleForAuthority::getAuthorityId)
                .collect(Collectors.toList())
                .stream().map(id -> authorityService.getById(id))
                .collect(Collectors.toList());
        return authorityList;
    }

    /**
     * 通过用户id拿到所有的角色
     *
     * @param loginId
     * @return 角色集合 List<Role>
     */
    public List<Role> getRoleList(Object loginId) {
        final List<UserForRole> list = userForRoleService.list(
                Wrappers.lambdaQuery(new UserForRole())
                        .eq(UserForRole::getUserId, loginId)
        );
        final List<Role> roleCollect = list.stream()
                .map(UserForRole::getRoleId)
                .map(id -> roleService.getById(id))
                .collect(Collectors.toList());
        return roleCollect;
    }
}
