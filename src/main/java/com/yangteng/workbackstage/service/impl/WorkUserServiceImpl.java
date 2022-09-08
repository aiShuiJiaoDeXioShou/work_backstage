package com.yangteng.workbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangteng.workbackstage.entity.us.WorkUser;
import com.yangteng.workbackstage.mapper.WorkUserMapper;
import com.yangteng.workbackstage.service.IWorkUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林河
 * @since 2022-08-09
 */
@Service
public class WorkUserServiceImpl extends ServiceImpl<WorkUserMapper, WorkUser> implements IWorkUserService {

    @Override
    public WorkUser login(WorkUser user) {
        final LambdaQueryWrapper<WorkUser> wrapper = Wrappers.lambdaQuery(WorkUser.class);
        wrapper.eq(WorkUser::getPhone, user.getPhone())
                        .eq(WorkUser::getPassword, user.getPassword());
        return getOne(wrapper);
    }
}
