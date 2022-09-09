package com.yangteng.workbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangteng.workbackstage.entity.us.CouponUserBag;
import com.yangteng.workbackstage.entity.vo.CouponBagVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
public interface CouponUserBagMapper extends BaseMapper<CouponUserBag> {
    List<CouponBagVo> selectAllCouponByUserId(@Param("userId") Long userId);

    List<CouponBagVo> selectAllNotExpiringByUserId(Long userId);

    List<CouponBagVo> selectCouponByIds(@Param("ids") ArrayList<Long> ids);
}
