package com.mybatis.service.impl;

import com.mybatis.entity.Course;
import com.mybatis.mapper.CourseDao;
import com.mybatis.service.CourseService;
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
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {

}
