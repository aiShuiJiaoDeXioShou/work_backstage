package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.us.Coupon;
import com.yangteng.workbackstage.entity.us.CouponUserBag;
import com.yangteng.workbackstage.entity.vo.CouponBagVo;
import com.yangteng.workbackstage.exception.NoFontAuthorityException;
import com.yangteng.workbackstage.service.ICouponService;
import com.yangteng.workbackstage.service.ICouponUserBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 消费卷管理控制器
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ICouponUserBagService userBagService;

    @Autowired
    private ICouponService couponService;

    /**
     * 获取当前用户所有的消费卷
     */
    @GetMapping("/nowuser")
    public R nowuser(){
        // 当前用户所拥有的所有优惠卷
        List<CouponBagVo> bag = userBagService.getBag(StpUtil.getLoginIdAsLong());
        return R.ok(bag);
    }

    /**
     * 判断是否有该优惠卷,当前用户获得指定的消费卷
     */
    @PostMapping("/nowuser/give/{couponId}")
    public R give(@PathVariable Long couponId) {
        Coupon byId = couponService.getById(couponId);
        if (Objects.isNull(byId)) throw new NoFontAuthorityException("没有该优惠卷！");
        // 如果数据库中有该id对应的优惠卷，将它添加到关联表中
        CouponUserBag bag = new CouponUserBag();
        bag.setCouponId(couponId);
        userBagService.save(bag);
        return R.ok();
    }

    /**
     * 获取所有没有过期的消费卷
     */
    @GetMapping("/nowuser_not_expiring")
    public R nowuserNotExpiring(){
        // 当前用户所拥有的所有优惠卷
        List<CouponBagVo> bag = userBagService.getBag(StpUtil.getLoginIdAsLong());
        return R.ok(bag);
    }



}
