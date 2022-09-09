package com.yangteng.workbackstage.service;

import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.dto.ConsumeDto;
import com.yangteng.workbackstage.entity.us.Consume;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
public interface IConsumeService extends IService<Consume> {

    R userConsume(ConsumeDto consumeDto);

}
