package com.yangteng.workbackstage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangteng.workbackstage.entity.us.ExRecord;
import com.yangteng.workbackstage.entity.us.WorkUser;
import com.yangteng.workbackstage.mapper.WorkUserMapper;
import com.yangteng.workbackstage.myenum.level.ExRecordType;
import com.yangteng.workbackstage.myenum.level.WordtreeAuthorLevel;
import com.yangteng.workbackstage.myenum.level.WordtreeLevel;
import com.yangteng.workbackstage.myenum.level.WordtreeReaderLevel;
import com.yangteng.workbackstage.service.IExRecordService;
import com.yangteng.workbackstage.service.IWorkUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IExRecordService exRecordService;

    @Override
    public WorkUser login(WorkUser user) {
        final LambdaQueryWrapper<WorkUser> wrapper = Wrappers.lambdaQuery(WorkUser.class);
        wrapper.eq(WorkUser::getPhone, user.getPhone())
                        .eq(WorkUser::getPassword, user.getPassword());
        return getOne(wrapper);
    }

    @Override
    public boolean addReadExp(Long exp) {
        long uid = StpUtil.getLoginIdAsLong();
        boolean update = update(
                Wrappers.lambdaUpdate(new WorkUser())
                        .setSql("read_ex = read_ex + " + exp)
                        .eq(WorkUser::getId, uid)
        );
        // 对等级进行判断
        if (update) {
            // 添加经验记录表记录
            ExRecord exRecord = new ExRecord();
            exRecordService.save(
                    exRecord.setType(ExRecordType.Read)
                            .setId(uid)
            );
            update = upgrade(WordtreeReaderLevel.class);
        }
        return update;
    }

    @Override
    public boolean addAuthorExp(Long exp) {
        long uid = StpUtil.getLoginIdAsLong();
        boolean update = update(
                Wrappers.lambdaUpdate(new WorkUser())
                        .setSql("author_ex = author_ex + "+exp)
                        .eq(WorkUser::getId, uid)
        );
        // 对等级进行判断
        if (update) {
            // 添加经验记录表记录
            ExRecord exRecord = new ExRecord();
            exRecordService.save(
                    exRecord.setType(ExRecordType.Book)
                            .setId(uid)
            );
            update = upgrade(WordtreeAuthorLevel.class);
        }
        return update;
    }

    // 通过参数对等级进行判断,升级
    public boolean upgrade(Class<? extends WordtreeLevel> clazz) {
        boolean update = false;
        // 如果传入的参数是WordtreeAuthorLevel则
        if (clazz.equals(WordtreeAuthorLevel.class))
        {
            WorkUser byId = getById(StpUtil.getLoginIdAsLong());

            LambdaUpdateWrapper<WorkUser> wrapper = Wrappers.lambdaUpdate(new WorkUser());

            for (WordtreeAuthorLevel level : WordtreeAuthorLevel.values()) {
                if (level.getExperience() > byId.getAuthorEx()) {
                    wrapper.set(WorkUser::getAuthorLevel, level.getLevel());
                }
            }

            wrapper.eq(WorkUser::getId,StpUtil.getLoginIdAsLong());
            update = update(wrapper);
        }

        // 如果传入的参数是WordtreeReaderLevel则
        else if (clazz.equals(WordtreeReaderLevel.class))
        {
            WorkUser byId = getById(StpUtil.getLoginIdAsLong());

            LambdaUpdateWrapper<WorkUser> wrapper = Wrappers.lambdaUpdate(new WorkUser());

            for (WordtreeReaderLevel level : WordtreeReaderLevel.values()) {
                if (level.getExperience() > byId.getReadEx()) {
                    wrapper.set(WorkUser::getReadLevel, level.getLevel());
                }
            }

            wrapper.eq(WorkUser::getId,StpUtil.getLoginIdAsLong());
            update = update(wrapper);
        }

        return update;
    }
}
