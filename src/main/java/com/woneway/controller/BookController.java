package com.woneway.controller;

import com.woneway.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:33 下午
 * @description
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    public void print() {
        System.out.println(bookService);
    }
}
