package com.woneway.config;

import com.woneway.bean.Person;
import com.woneway.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:23 下午
 * @description
 */
//配置类==配置文件
@Configuration
@ComponentScan(
        value = "com.woneway",
        useDefaultFilters = false,
        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        })
// value: 指定要扫描的包
// excludeFilters: 排除过滤器
// includeFilters: 只包含过滤 仅在useDefaultFilters false时生效
//FilterType.ANNOTATION 按照注解
//FilterType.ASSIGNABLE_TYPE 按照给定的类型
//FilterType.ASPECTJ
//FilterType.REGEX
//FilterType.CUSTOM -- 自定义规则
public class MainConfig {

    @Bean
    public Person person() {
        return new Person("李四", 120);
    }
}
