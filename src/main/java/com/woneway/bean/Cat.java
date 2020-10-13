package com.woneway.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:51 上午
 * @description
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("cat constructor ...");
    }


    public void destroy() throws Exception {
        System.out.println("cat destroy ...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("cat init ...");
    }
}
