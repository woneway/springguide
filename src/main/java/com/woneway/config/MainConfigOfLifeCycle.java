package com.woneway.config;

import com.woneway.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:37 上午
 * @description bean的生命周期：bean创建--->初始化--->销毁的过程
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候调用我们自定义的初始化和销毁方法
 *
 * 构造(对象创建)
 *   单实例：容器启动的时候进行创建
 *   多实例：在每次获取的时候创建
 *
 * 初始化
 *    对象创建完成，并赋值好，调用初始化方法...
 * 销毁
 *    单实例：容器关闭的时候调用销毁方法
 *    多实例：不进行管理
 *
 * <p>
 * 1.指定初始化和销毁方法
 *     指定init-method和destroy-method
 * 2.实现
 *     org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
 *     org.springframework.beans.factory.DisposableBean#destroy()
 * 3.使用JSR250注解 @PostConstruct @PreDestroy
 * 4. BeanPostProcessor: bean的后置处理器
 *      postProcessBeforeInitialization
 *      postProcessAfterInitialization
 * BeanPostProcessor原理：
 * 	  * populateBean(beanName, mbd, instanceWrapper); 给bean进行赋值
 *    * initializeBean{
 *       wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *       执行初始化：invokeInitMethods(beanName, wrappedBean, mbd);
 *       wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *    }
 *
 * spring底层对BeanPostProcessor的使用：
 * bean赋值，注入其他组件，@Autowired 生命周期注解功能，@Async
 */
@Configuration
@ComponentScan("com.woneway.bean")
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }
}
