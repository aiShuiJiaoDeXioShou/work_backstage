package com.yangteng.workbackstage.handler;

import cn.dev33.satoken.stp.StpInterface;
import com.yangteng.workbackstage.entity.ua.Authority;
import com.yangteng.workbackstage.entity.ua.Role;
import com.yangteng.workbackstage.service.UserAuthorityManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserPermissionCertificationHandler implements StpInterface {
    @Autowired
    private UserAuthorityManagementService userAuthorityManagementService;

    private List<Role> roles;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        if (Objects.isNull(roles)) roles = userAuthorityManagementService.getRoleList(loginId);
        final List<String> authorityName = userAuthorityManagementService.getAuthorityList(roles)
                .stream().map(Authority::getAuthorityName)
                .collect(Collectors.toList());
        log.info("所拥有的权限集合:\n {}", authorityName);
        return authorityName;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        roles = userAuthorityManagementService.getRoleList(loginId);
        final List<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
        log.info("所拥有的角色集合:\n {}", roleNames);
        return roleNames;
    }
}
