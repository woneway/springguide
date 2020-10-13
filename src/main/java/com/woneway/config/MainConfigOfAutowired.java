package com.woneway.config;

import com.woneway.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/13 2:24 下午
 * @description
 */

/**
 * 自动装配
 * spring利用依赖注入，完成ioc容器中各个组件的依赖关系赋值
 * 1. @Autowired 自动注入
 *    1.1 默认优先按照类型去找组件
 *    1.2 如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *    1.3 @Qualifier指定装配的组件id
 *    1.4 @Autowired(required=false),允许装配的属性为空
 *    1.5 @Primary 让Spring进行自动装配的时候，默认为首选的bean
 * 2. Spring还支持@Resource（JSR250）和@Inject（JSR330）【Java规范的注解】
 *    2.1 @Resource 可以和@Autowired一样实现自动装配功能；默认是按照组件名称进行装配的
 *        没有支持@Primary和@Autowired(required=false)
 *    2.2 @Inject 还需要导入依赖
 *        支持@Primary,不支持@Autowired(required=false)
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配
 *
 * 3.使用spring自定义组件进行注入，实现Aware实现类
 */
@Configuration
@ComponentScan({"com.woneway.service","com.woneway.dao","com.woneway.controller"})
public class MainConfigOfAutowired {

    @Bean("bookDao2")
    @Primary
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel(2);
        return bookDao;
    }
}
