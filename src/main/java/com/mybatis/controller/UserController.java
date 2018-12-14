package com.mybatis.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mybatis.entity.Book;
import com.mybatis.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/show")
    public String testEnum(ModelMap modelMap) {
        Page<Book> bookPage = bookService.selectPage(new Page<>(1, 10));
        logger.info("bookPage>>>>>>>>>>"+bookPage.toString());
        modelMap.addAttribute("bookPage",bookPage);
        return "index";
    }

}