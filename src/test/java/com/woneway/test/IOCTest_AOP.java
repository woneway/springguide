package com.woneway.test;

import com.woneway.aop.MathCalculator;
import com.woneway.config.MainConfigOfAOP;
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
public class IOCTest_AOP {

    @Test
    public void test01() {
        //1. 创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator bean = applicationContext.getBean(MathCalculator.class);
        bean.div(10, 1);
    }
}
