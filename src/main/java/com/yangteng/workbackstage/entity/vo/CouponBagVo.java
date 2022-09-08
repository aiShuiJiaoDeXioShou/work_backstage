package com.yangteng.workbackstage.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CouponBagVo {
    private Integer couponId;
    private LocalDateTime createTime;
    private String title;
    private Long discountedPrice;
    private Integer discount;
    private LocalDateTime expiredTime;

    private Boolean isExpired;

    private long effectiveTime;

    public Boolean getExpired() {
        return expiredTime.isBefore(createTime);
    }

    /**
     * 以毫秒为计算单位
     * @return 返回持续时间
     */
    public long getEffectiveTime() {
        Duration duration = Duration.between(createTime,expiredTime);
        long millis = duration.toMillis();
        return millis;
    }
}
