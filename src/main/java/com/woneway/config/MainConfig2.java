package com.woneway.config;

import com.woneway.bean.Color;
import com.woneway.bean.ColorFactoryBean;
import com.woneway.bean.Person;
import com.woneway.bean.Red;
import com.woneway.condition.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:23 下午
 * @description
 */
//配置类==配置文件
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {

    // 默认是单例的

    /**
     * @Scope 调整作用域
     * Specifies the name of the scope to use for the annotated component/bean.
     * <p>Defaults to an empty string ({@code ""}) which implies
     * {@link ConfigurableBeanFactory#SCOPE_SINGLETON SCOPE_SINGLETON}.
     * @Lazy 懒加载
     * 单例情况下，默认是在容器启动的时候床架对象，懒加载的情况下会在第一次使用时进行创建对象
     * @since 4.2
     * SCOPE_PROTOTYPE 多例 每次获取都会创建
     * SCOPE_SINGLETON 单例(默认值) ioc容器启动后会调用发放创建对象到ioc容器中，只会创建一次
     * SCOPE_REQUEST   同一次请求创建一个实例
     * SCOPE_SESSION   同一个session创建一个实例
     */
    //@Scope("prototype")
//    @Lazy
    @Bean("person")
    public Person person() {
        // System.out.println("创建person");
        return new Person("张三", 25);
    }

    /**
     * 按照条件给容器注册bean
     *
     * @Conditional: 按照一定的条件进行判断，满足条件的情况下给容器注册bean
     * <p>
     * 如果是windows系统，
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    @Conditional({MacCondition.class})
    @Bean("mac")
    public Person person03() {
        return new Person("mac", 48);
    }


    /**
     * 给容器中注册组件
     * 1. 包扫描 + 各种注解(@Controller...)
     * 2. @Bean
     * 3. @Import
     * *
     * * {@link Configuration @Configuration}, {@link ImportSelector},
     * * {@link ImportBeanDefinitionRegistrar}, or regular component classes to import.
     * *
     * 3.1 @Import("......class") 直接导入组件
     * 3.2 ImportSelector 导入组件
     * 3.3 ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4.使用Spring提供的FactoryBean
     *   4.1 默认获取到的是工厂bean调用getObject创建的对象
     *   4.2 要获取工厂bean本身，我们需要给id加一个&前缀
     */

    @Bean("colorFactoryBean")
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}