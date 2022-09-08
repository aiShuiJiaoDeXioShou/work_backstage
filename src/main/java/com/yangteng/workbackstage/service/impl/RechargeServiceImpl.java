package com.yangteng.workbackstage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.comm.PythonScriptLoad;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.us.Recharge;
import com.yangteng.workbackstage.entity.vo.RechargeSchemeVo;
import com.yangteng.workbackstage.exception.NoFontAuthorityException;
import com.yangteng.workbackstage.mapper.RechargeMapper;
import com.yangteng.workbackstage.service.IRechargeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements IRechargeService {
    @Autowired
    private PythonScriptLoad pythonScriptLoad;

    @Override
    public R rechargeForScheme(Integer rp_id) {

        // 获取所有的充值方案
        List<RechargeSchemeVo> rechargeSchemeOne = pythonScriptLoad
                .runPythonScript("RechargeSchemeOne.py", RechargeSchemeVo.class);
        // 指定的充值方案
        RechargeSchemeVo schemeVo = rechargeSchemeOne.get(rp_id);

        // 判断该用户是否具备充值条件
        ArrayList<String> parameters = schemeVo.getParameters();
        boolean b = StpUtil.hasPermissionOr(parameters.toArray(new String[0]));

        if (b) {
            throw new NoFontAuthorityException("充值失败，该用户不满足方案条件!");
        }

        Recharge recharge = new Recharge();
        recharge.setPreAmount(BigDecimal.valueOf(schemeVo.getAmount()));
        recharge.setGiveAmount(BigDecimal.valueOf(schemeVo.getGivingAmount()));
        recharge.setAmount(
                BigDecimal.valueOf(schemeVo.getAmount())
                        .add(BigDecimal.valueOf(schemeVo.getGivingAmount()))
        );

        boolean save = save(recharge);
        return save?R.ok(): R.fail("充值失败！");
    }
}
