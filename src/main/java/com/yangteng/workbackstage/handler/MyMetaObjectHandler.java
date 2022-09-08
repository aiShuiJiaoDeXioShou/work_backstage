package com.yangteng.workbackstage.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作，自动填充
     * 
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        // 如果是登录用户，自动填充创建人和更新人
        if (StpUtil.isLogin()) {
            try {
                metaObject.setValue("createUser", StpUtil.getLoginId());
                metaObject.setValue("updateUser", StpUtil.getLoginId());

                Object autorId = metaObject.getValue("autorId");
                if (autorId != null) {
                    metaObject.setValue("autorId", StpUtil.getLoginId());
                }

            } catch (ReflectionException e) {
                log.warn("没有createUser字段,或者没有updateUser字段,无法填充!!!");
            }
        }

    }

    /**
     * 更新操作，自动填充
     * 
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        metaObject.setValue("updateTime", LocalDateTime.now());

        // 如果是登录用户，自动填充创建人和更新人
        if (StpUtil.isLogin()) {
            try {
                metaObject.setValue("updateUser", StpUtil.getLoginId());
            } catch (ReflectionException e) {
                log.warn("没有createUser字段,或者没有updateUser字段,无法填充!!!");
            }
        }

    }
}
