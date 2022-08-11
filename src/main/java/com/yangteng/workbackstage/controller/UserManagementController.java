package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.WorkUser;
import com.yangteng.workbackstage.service.IWorkUserService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@ToString
public class UserManagementController {

    @Autowired
    private IWorkUserService userService;

    // 注册账号
    @PostMapping("/register")
    public R<String> register(@RequestBody WorkUser workUser) {
        log.info("注册账号：{}", workUser);
        userService.save(workUser);
        return R.ok("注册成功！");
    }

    @PostMapping("/login")
    public R login(@RequestBody WorkUser user) {
        log.info("user: {}", user);
        if (StpUtil.isLogin()) {
            return R.fail("已经登录！");
        }
        WorkUser workUser = userService.login(user);
        if (workUser != null) {
            StpUtil.login(workUser.getId());
            return R.ok(StpUtil.getTokenInfo());
        }
        return R.fail("登录失败！");
    }

    @GetMapping("/info/{id}")
    public R info(@PathVariable Integer id) {
        if (!StpUtil.isLogin()) {
            return R.fail("未登录！");
        }
        final WorkUser workUser = userService.getById(id);
        return R.ok(workUser);
    }

    @RequestMapping("logout")
    public R<String> logout() {
        StpUtil.logout();
        return R.ok();
    }
}
