package com.woneway.service;

import com.woneway.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:33 下午
 * @description
 */
@Service
public class BookService {

    //    @Autowired
//    @Resource
    @Inject
    private BookDao bookDao;

    public void print() {
        System.out.println(bookDao);
    }
}
