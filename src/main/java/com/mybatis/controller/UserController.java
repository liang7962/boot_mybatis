package com.mybatis.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatis.entity.Book;
import com.mybatis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private BookService bookService;

//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping(value = "/show")
//    public ModelAndView testEnum(ModelAndView modelAndView) {
//        Page<Book> bookPage = bookService.selectPage(new Page<>(1, 10));
//        logger.info("bookPage>>>>>>>>>>"+bookPage.toString());
//        modelAndView.addObject("bookPage",bookPage);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }

    @GetMapping(value = "/show")
    public String testEnum(ModelMap modelMap) {
        Page<Book> bookPage = bookService.selectPage(new Page<>(1, 10));
        log.info("bookPage>>>>>>>>>>"+bookPage.toString());
        log.info("name>>>>>>>>>>"+bookPage.getRecords().get(0).getName());
        modelMap.addAttribute("bookPage",bookPage);
        return "index";
    }

    @GetMapping("/thread")
    @ResponseBody
    public String threadTest(){
        for (int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i2>>>");
                }
            });
            System.out.println("thread>>>"+thread.getName());
            thread.start();
        }
        return "index";
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper=new ObjectMapper();
        Book book=new Book();
        book.setBid(1);
        book.setName("Jack");
        book.setAnthorid(2321);
        String userInfoString= null;
        try {
            userInfoString = objectMapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(userInfoString);
    }

}