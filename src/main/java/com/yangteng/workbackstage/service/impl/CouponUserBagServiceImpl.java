package com.yangteng.workbackstage.service.impl;

import com.yangteng.workbackstage.entity.us.CouponUserBag;
import com.yangteng.workbackstage.entity.vo.CouponBagVo;
import com.yangteng.workbackstage.mapper.CouponUserBagMapper;
import com.yangteng.workbackstage.service.ICouponUserBagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class CouponUserBagServiceImpl extends ServiceImpl<CouponUserBagMapper, CouponUserBag> implements ICouponUserBagService {

    @Override
    public List<CouponBagVo> getBag(Long uid) {
        List<CouponBagVo> maps = getBaseMapper().selectAllCouponByUserId(uid);
        return maps;
    }

    @Override
    public List<CouponBagVo> getBagNotExpiring(Long userId) {
        List<CouponBagVo> maps = getBaseMapper().selectAllNotExpiringByUserId(userId);
        return maps;
    }

    @Override
    public List<CouponBagVo> getBagByIds(ArrayList<Long> ids) {
        List<CouponBagVo> maps = getBaseMapper().selectCouponByIds(ids);
        return maps;
    }
}
