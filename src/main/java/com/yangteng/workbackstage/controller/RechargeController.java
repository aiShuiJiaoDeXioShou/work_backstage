package com.yangteng.workbackstage.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.comm.PythonScriptLoad;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.us.Recharge;
import com.yangteng.workbackstage.entity.us.WorkUser;
import com.yangteng.workbackstage.entity.vo.RechargeSchemeVo;
import com.yangteng.workbackstage.exception.NoFontAuthorityException;
import com.yangteng.workbackstage.service.IRechargeService;
import com.yangteng.workbackstage.service.IWorkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 充值控制器
 */
@RestController
@SaCheckLogin
@RequestMapping("/recharge")
public class RechargeController {

    @Autowired
    private IRechargeService rechargeService;

    @Autowired
    private PythonScriptLoad pythonScriptLoad;

    @Autowired
    private IWorkUserService userService;

    /**
     * 自定义充值账户
     */
    @PostMapping
    public R addRecharge(@RequestBody Recharge recharge) {
        recharge.setAmount(recharge.getGiveAmount().add(recharge.getPreAmount()));
        boolean save = rechargeService.save(recharge);
        if (save) {
            // 更新用户余额
            WorkUser nowuser = userService.getById(StpUtil.getLoginIdAsLong());
            BigDecimal add = nowuser.getAccountBalance().add(recharge.getAmount());
            userService.save(nowuser.setAccountBalance(add));
        }
        return R.ok();
    }

    /**
     * 根据充值方案进行充值
     */
    @PostMapping("/recharge_program/{rp_id}")
    public R addRecharge(@PathVariable Integer rp_id) {
        return rechargeService.rechargeForScheme(rp_id);
    }

    /**
     * 获取充值方案
     */
    @GetMapping("/recharge_program")
    public R getRechargeProgram() {
        // 加载指定位置的python语言脚本来获取充值方案
        List<RechargeSchemeVo> rechargeSchemeOne = pythonScriptLoad
                .runPythonScript(
                        "RechargeSchemeOne.py",
                        RechargeSchemeVo.class,
                        " "+StpUtil.getLoginIdAsLong()
                );
        return R.ok(rechargeSchemeOne);
    }

}
