package com.woneway.test;

import com.woneway.config.MainConfigOfAutowired;
import com.woneway.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:42 上午
 * @description
 */
public class IOCTest_Autowired {

    @Test
    public void test01() {
        //1. 创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        BookService bean = applicationContext.getBean(BookService.class);

        bean.print();
        applicationContext.close();
    }
}
