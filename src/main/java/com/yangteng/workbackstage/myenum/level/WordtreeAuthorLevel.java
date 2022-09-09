package com.yangteng.workbackstage.myenum.level;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WordtreeAuthorLevel implements WordtreeLevel {
    尊上 (9, 8000000L, "谁能触及非凡?道果所在!"),
    白金 (8, 800000L, "白金所在,即是彼岸所在"),
    大神 (7, 400000L, "大神强者恐怖如斯!"),
    LV5 (6, 200000L,"也许你就是下一个乌贼"),
    LV4 (5, 100000L, "或许坐忘道才适合我"),
    LV3(4,60000L, "转生成为终点作家的我,今天也要拯救终点!") ,
    LV2 (3, 30000L, "再加把劲,拉屎要用力"),
    LV1 (2, 10000L, "也许就能成功呢?"),
    夜寐速更(1, 5000L,"就这,就这?夜寐速更!!!"),
    一拒强者(0, 1000L, "这个境界你是怎么好意思睡着的?");

    @EnumValue
    private Integer level;
    private Long experience;
    private String message;

}
