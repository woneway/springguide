package com.woneway.test;

import com.woneway.bean.Person;
import com.woneway.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:42 上午
 * @description
 */
public class IOCTest_PropertyValue {
    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        printBeans(applicationContext);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
