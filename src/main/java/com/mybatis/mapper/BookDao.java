package com.mybatis.mapper;

import com.mybatis.entity.Book;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liang
 * @since 2018-12-14
 */
@Mapper
public interface BookDao extends BaseMapper<Book> {

}
