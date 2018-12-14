package com.mybatis.service.impl;

import com.mybatis.entity.Book;
import com.mybatis.mapper.BookDao;
import com.mybatis.service.BookService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liang
 * @since 2018-12-14
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {

}
