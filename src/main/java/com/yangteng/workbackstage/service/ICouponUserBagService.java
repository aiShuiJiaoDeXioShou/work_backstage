package com.yangteng.workbackstage.service;

import com.yangteng.workbackstage.entity.us.CouponUserBag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangteng.workbackstage.entity.vo.CouponBagVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
public interface ICouponUserBagService extends IService<CouponUserBag> {

    List<CouponBagVo> getBag(Long userId);
    List<CouponBagVo> getBagNotExpiring(Long userId);
}
