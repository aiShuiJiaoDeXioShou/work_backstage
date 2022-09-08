package com.yangteng.workbackstage.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RechargeSchemeVo {
    private String title;
    private Double pay;
    private Double amount;
    private Double givingAmount;
    // 赠送金额所需权限
    private ArrayList<String> parameters;
}
