package com.yangteng.workbackstage.entity.dto;

import com.yangteng.workbackstage.entity.us.Consume;
import com.yangteng.workbackstage.entity.us.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsumeDto {
    private Consume consume;
    /**
     * 使用的消费卷id集合
     */
    private ArrayList<Long> couponIds;
}
