package com.yangteng.workbackstage.mapper;

import com.yangteng.workbackstage.entity.book.BookCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林河
 * @since 2022-08-09
 */
public interface BookCollectMapper extends BaseMapper<BookCollect> {

    /**
     * 根据书籍id查询书籍收藏数量
     * @param start 分页开始的位置
     * @param end 分页结束的位置
     */
    List<HashMap> selectBookCollectCountByBookId(@Param("start") Integer start, @Param("end") Integer end);
}
