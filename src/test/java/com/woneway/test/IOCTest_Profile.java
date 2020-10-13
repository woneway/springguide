package com.woneway.test;

import com.woneway.config.MainConfigOfAutowired;
import com.woneway.config.MainConfigOfProfile;
import com.woneway.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:42 上午
 * @description
 */
public class IOCTest_Profile {

    /**
     * 1.使用命令行动态参数：-Dspring.profiles.active=test
     * 2.applicationContext.getEnvironment().setActiveProfiles("test", "dev");
     */
    @Test
    public void test01() {
        //1. 创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2. 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test", "dev");
        //3. 注册配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4. 启动刷新容器
        applicationContext.refresh();
        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String beanName : beanNamesForType) {
            System.out.println(beanName);
        }
        applicationContext.close();
    }
}
