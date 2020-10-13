package com.woneway.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:57 上午
 * @description
 */
@Component
public class Dog implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("dog constructor ...");
    }


    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("dog destroy ...");
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("dog init ...");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
