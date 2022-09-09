package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.dto.ConsumeDto;
import com.yangteng.workbackstage.entity.us.Consume;
import com.yangteng.workbackstage.service.IConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SaCheckLogin
@RequestMapping("/consume")
public class ConsumeController {

    @Autowired
    private IConsumeService consumeService;

    /**
     * 新增一条消费记录
     * 更新用户余额
     */
    @PostMapping
    public R addConsume(@RequestBody ConsumeDto consumeDto) {
        return consumeService.userConsume(consumeDto);
    }

    /**
     * 查询用户所有的消费记录
     */
    @GetMapping
    public R getConsume() {

        return R.ok(consumeService.list(
                Wrappers.lambdaQuery(new Consume())
                        .eq(Consume::getUserId, StpUtil.getLoginIdAsLong())
        ));
    }

}
