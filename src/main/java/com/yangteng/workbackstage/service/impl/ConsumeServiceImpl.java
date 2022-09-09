package com.yangteng.workbackstage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.dto.ConsumeDto;
import com.yangteng.workbackstage.entity.us.Consume;
import com.yangteng.workbackstage.entity.us.WorkUser;
import com.yangteng.workbackstage.entity.vo.CouponBagVo;
import com.yangteng.workbackstage.mapper.ConsumeMapper;
import com.yangteng.workbackstage.service.IConsumeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangteng.workbackstage.service.ICouponUserBagService;
import com.yangteng.workbackstage.service.IWorkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
@Service
public class ConsumeServiceImpl extends ServiceImpl<ConsumeMapper, Consume> implements IConsumeService {
    @Autowired
    private IConsumeService consumeService;

    @Autowired
    private IWorkUserService userService;

    @Autowired
    private ICouponUserBagService couponUserBagService;

    @Override
    public R userConsume(ConsumeDto consumeDto) {
        Consume consume = consumeDto.getConsume();

        if (!Objects.isNull(consumeDto.getCouponIds())){
            // 获取所有的消费卷集合
            List<CouponBagVo> couponBags = couponUserBagService.getBagByIds(consumeDto.getCouponIds());

            // 减去优惠
            for (CouponBagVo couponBag : couponBags) {

                if (couponBag.getDiscount() > 0){
                    BigDecimal multiply = consume.getMoney().multiply(
                            BigDecimal.valueOf(couponBag.getDiscount() * 0.1)
                    );
                    consume.setMoney(multiply);
                }

                consume.setMoney(
                        consume.getMoney().subtract(BigDecimal.valueOf(couponBag.getDiscountedPrice()))
                );

            }

            // 删除所有用过的优惠卷
            couponUserBagService.removeBatchByIds(consumeDto.getCouponIds());
        }

        // 将结果保存到消费记录中
        boolean save = consumeService.save(consume);
        if (save) {
            WorkUser user = userService.getById(StpUtil.getLoginIdAsLong());
            BigDecimal subtract = user.getAccountBalance().subtract(consume.getMoney());
            if(subtract.floatValue() > 0){
                // 更新用户余额数据
                user.setAccountBalance(subtract);
                userService.save(user);
            } else {
                return R.fail("余额不足，请充值！！");
            }
        }
        return save?R.ok():R.fail("新增订单失败！");
    }

}
