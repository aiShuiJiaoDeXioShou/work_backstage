package com.yangteng.workbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangteng.workbackstage.entity.WorkUser;

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
}
