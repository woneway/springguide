package com.woneway.test;

import com.woneway.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:42 上午
 * @description
 */
public class IOCTest_LifeCycle {

    @Test
    public void test01() {
        //1. 创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);

        System.out.println("容器创建完成");
        applicationContext.close();
    }
}
