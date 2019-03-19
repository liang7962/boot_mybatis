package com.mybatis.generator.web;


import com.mybatis.generator.entity.Less;
import com.mybatis.generator.service.LessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Liang
 * @since 2019-03-18
 */
@Controller
@RequestMapping("/less")
public class LessController {

    @Autowired
    LessService lessService;

    @RequestMapping("/")
    @ResponseBody
    Less Voild(){
        Less less=new Less();
        less.setCreate_date(new Date());
        lessService.insert(less);
        return less;
    }


}
