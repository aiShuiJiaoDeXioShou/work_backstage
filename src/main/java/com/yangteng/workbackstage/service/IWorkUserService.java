package com.yangteng.workbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangteng.workbackstage.entity.us.WorkUser;
import com.yangteng.workbackstage.myenum.level.WordtreeLevel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林河
 * @since 2022-08-09
 */
public interface IWorkUserService extends IService<WorkUser> {

    WorkUser login(WorkUser user);

    /**
     * 增加用户阅读的经验值,并且判断是否满足进阶条件
     */
    boolean addReadExp(Long exp);

    /**
     * 增加用户作者的经验值,并且判断是否满足进阶条件
     */
    boolean addAuthorExp(Long exp);

    boolean upgrade(Class<? extends WordtreeLevel> clazz);
}
