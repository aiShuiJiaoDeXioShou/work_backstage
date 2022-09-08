package com.yangteng.workbackstage.service;

import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.us.Recharge;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
public interface IRechargeService extends IService<Recharge> {

    /**
     * 根据充值方案进行充值
     */
    R rechargeForScheme(Integer rp_id);
}
