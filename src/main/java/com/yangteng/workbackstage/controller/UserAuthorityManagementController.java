package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.ua.Authority;
import com.yangteng.workbackstage.entity.ua.Role;
import com.yangteng.workbackstage.entity.ua.RoleForAuthority;
import com.yangteng.workbackstage.entity.ua.UserForRole;
import com.yangteng.workbackstage.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 此类包含所有权限和角色相关的方法
 */
@Slf4j
@RequestMapping("/au_role")
@SaCheckRole("admin")
@RestController
@SaCheckLogin
public class UserAuthorityManagementController {
    @Autowired
    private IAuthorityService authorityService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleForAuthorityService roleForAuthorityService;
    @Autowired
    private IWorkUserService userService;
    @Autowired
    private IUserForRoleService userForRoleService;
    @Autowired
    private UserAuthorityManagementService userAuthorityManagementService;

    /**
     * 添加角色
     *
     * @param role
     * @return R
     */
    @PostMapping("/role")
    public R addRole(Role role) {
        return roleService.save(role) ? R.ok() : R.fail();
    }

    /**
     * 添加权限
     *
     * @param authority
     * @return R
     */
    @PostMapping("/authority")
    public R addAuthority(Authority authority) {
        return authorityService.save(authority) ? R.ok() : R.fail();
    }

    /**
     * 添加角色和权限的关系
     *
     * @param userId
     * @param roleId
     * @return R
     */
    @PostMapping("/user_for_role/{userId}/{roleId}")
    public R addUserForRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return userForRoleService.save(new UserForRole().setRoleId(roleId).setUserId(userId)) ? R.ok() : R.fail();
    }

    /**
     * 添加角色和权限的关系
     *
     * @param roleId
     * @param authorityId
     * @return R
     */
    @PostMapping("/role_for_authority")
    public R addRoleForAuthority(Long roleId, Long authorityId) {
        return roleForAuthorityService.save(new RoleForAuthority().setAuthorityId(authorityId).setRoleId(roleId))
                ? R.ok()
                : R.fail();
    }

    /**
     * 删除指定用户所拥有的指定角色
     *
     * @param userId
     * @param roleId
     * @return R
     */
    @DeleteMapping("/user_for_role")
    public R deleteUserForRole(Long userId, Long roleId) {

        return userForRoleService.remove(
                Wrappers.lambdaQuery(new UserForRole())
                        .eq(UserForRole::getUserId, userId)
                        .eq(UserForRole::getRoleId, roleId)) ? R.ok() : R.fail();
    }

    /**
     * 删除指定角色所拥有的指定权限
     *
     * @param roleId
     * @param authorityId
     * @return R
     */
    @DeleteMapping("/role_for_authority/{roleId}/{authorityId}")
    public R deleteRoleForAuthority(@PathVariable Long roleId, @PathVariable Long authorityId) {
        return roleForAuthorityService.remove(
                Wrappers.lambdaQuery(new RoleForAuthority())
                        .eq(RoleForAuthority::getRoleId, roleId)
                        .eq(RoleForAuthority::getAuthorityId, authorityId)) ? R.ok() : R.fail();
    }

    /**
     * 删除指定角色
     *
     * @param roleId
     * @return R
     */
    @DeleteMapping("/role/{roleId}")
    public R deleteRole(@PathVariable Long roleId) {
        return roleService.removeById(roleId) ? R.ok() : R.fail();
    }

    /**
     * 删除指定权限
     *
     * @param authorityId
     * @return R
     */
    @DeleteMapping("/authority/{authorityId}")
    public R deleteAuthority(@PathVariable Long authorityId) {
        return authorityService.removeById(authorityId) ? R.ok() : R.fail();
    }

    /**
     * 更新指定角色的信息
     *
     * @param role
     * @return R
     */
    @PutMapping("/role")
    public R updateRole(Role role) {
        return roleService.updateById(role) ? R.ok() : R.fail();
    }

    /**
     * 更新指定权限的信息
     *
     * @param authority
     * @return R
     */
    @PutMapping("/authority")
    public R updateAuthority(Authority authority) {
        return authorityService.updateById(authority) ? R.ok() : R.fail();
    }

    /**
     * 添加指定用户指定角色的过期时间
     *
     * @param userId
     * @param roleId
     * @param expireTime
     */
    @PutMapping("/user_for_role/{userId}/{roleId}/{expireTime}")
    public R addUserForRoleExpireTime(@PathVariable Integer userId, @PathVariable Integer roleId,
            @PathVariable Long expireTime) {
        final UserForRole userForRole = userForRoleService.getOne(
                Wrappers.lambdaUpdate(new UserForRole())
                        .eq(UserForRole::getUserId, userId)
                        .eq(UserForRole::getRoleId, roleId));
        if (userForRole == null)
            return R.fail();
        final LocalDateTime newExpireTime = userForRole.getExpiration().plus(expireTime, ChronoUnit.SECONDS);
        return userForRoleService.saveOrUpdate(userForRole.setExpiration(newExpireTime)) ? R.ok() : R.fail();
    }

    /**
     * 查询指定用户的指定角色是否过期
     *
     * @param userId
     * @param roleId
     * @return R
     */
    @GetMapping("/is_role_exp/{userId}/{roleId}")
    public R isUserForRoleExpired(@PathVariable Long userId, @PathVariable Long roleId) {
        final UserForRole userForRole = userForRoleService.getOne(
                Wrappers.lambdaQuery(new UserForRole())
                        .eq(UserForRole::getUserId, userId)
                        .eq(UserForRole::getRoleId, roleId));
        if (userForRole == null)
            return R.fail();
        return userForRole.getExpiration().isAfter(LocalDateTime.now()) ? R.ok() : R.fail();
    }

    /**
     * 查询指定用户所有的角色
     *
     * @return R
     */
    @GetMapping("/find_all_role/{id}")
    public R findAllRole(@PathVariable Long id) {
        return R.ok(userAuthorityManagementService.getRoleList(id));
    }

    /**
     * 查询指定用户所有的权限
     *
     * @return R
     */
    @GetMapping("/find_all_authority/{id}")
    public R findAllAuthority(@PathVariable Long id) {
        return R.ok(userAuthorityManagementService.getAuthorityList(userAuthorityManagementService.getRoleList(id)));
    }
}
