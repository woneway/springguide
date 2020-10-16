package com.woneway.config;

import com.woneway.aop.LogAspects;
import com.woneway.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/13 6:14 下午
 * @description
 */

/**
 * AOP:
 * 1.导入aop模块；
 *         <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-aspects</artifactId>
 *             <version>5.2.9.RELEASE</version>
 *         </dependency>
 * 2.定义一个业务逻辑类 MathCalculator
 * 3.定义一个日志切面类 LogAspects
 *       通知方法：
 *       3.1 前置通知 @Before
 *       3.2 后置通知 @After
 *       3.3 返回通知 @AfterReturning
 *       3.4 异常通知 @AfterThrowing
 *       3.5 环绕通知 @Around  joinPoint.procced()
 * 4.给切面类的目标方法标注何时何地运行
 * 5.将切面类和业务逻辑类添加到容器中
 * 6.告诉spring哪个是切面类(@Aspect)
 * 7.开启@EnableAspectJAutoProxy【；开启基于注解的aop模式】
 *
 * AOP原理：EnableAspectJAutoProxy
 * 1.EnableAspectJAutoProxy是什么
 *      @Import(AspectJAutoProxyRegistrar.class) 给容器中导入AspectJAutoProxyRegistrar.class
 *      利用给容器中导入AspectJAutoProxyRegistrar自定义给容器中注册bean：org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
 * 2.AnnotationAwareAspectJAutoProxyCreator：
 * AnnotationAwareAspectJAutoProxyCreator
 *  -> AspectJAwareAdvisorAutoProxyCreator
 *    -> AbstractAdvisorAutoProxyCreator
 *      -> AbstractAutoProxyCreator
 *         implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *         关注后置处理器（bean初始化前后进行相关操作）,自动装配beanfactory
 *
 *         1.AbstractAutoProxyCreator.setBeanFactory()
 *         2.AbstractAutoProxyCreator 有后置处理器的逻辑
 *         3.AbstractAdvisorAutoProxyCreator.setBeanFactory() 重写1的setBeanFactory -> initBeanFactory
 *         4.AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 *
 * 流程
 *  1）传入配置类，创建ioc容器
 *  2）注册配置类，调用refresh()刷新容器
 *  3）registerBeanPostProcessors(beanFactory)；注册bean的后置处理器来方便拦截bean的创建；
 *      3.1）先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
 *      3.2）给容器中加别的BeanPostProcessor
 *      3.3）优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *      3.4）再给容器中注册实现了Ordered接口的BeanPostProcessor
 *      3.5）注册没实现优先级接口的BeanPostProcessor
 *      3.6）注册BeanPostProcessor就是创建BeanPostProcessor对象保存到容器中
 *           创建internalAutoProxyCreator 的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 *           3.6.1）创建Bean的实例
 *           3.6.2）populateBean;给Bean的各种属性赋值
 *           3.6.3）initializeBean：初始化bean；
 *           3.6.4）BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
 *      3.7）把BeanPostProcessor注册到BeanFactory中：
 *           beanFactory.addBeanPostProcessor(postProcessor);
 *  ========= 以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程 =========
 *
 *  AbstractAdvisorAutoProxyCreator => InstantiationAwareBeanPostProcessor
 *  4）finishBeanFactoryInitialization(beanFactory);完成BeanFactory的初始化工作，创建剩下的单实例bean
 *      4.1）遍历获取容器中所有的bean，依次创建对象getBean(beanName)
 *      4.2）创建bean（如果不为null，则直接获取 ）
 *           【AnnotationAwareAspectJAutoProxyCreator会在所有bean】
 *           4.2.1）先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建；
 *                  只要创建好的bean都会被缓存起来
 *           4.2.2）createBean() 创建bean （AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先返回实例）
 *           【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *           【InstantiationAwareBeanPostProcessor是在创建bean实例前先尝试用后置处理器返回对象】
 *                  1. resolveBeforeInstantiation(beanName, mbdToUse);
 *                      希望后置处理器在此能返回一个代理对象；如果能，就使用；否则调用doCreateBean(beanName, mbdToUse, args)
 *                      1. 后置处理器先尝试返回对象 bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 					    2.  if (bean != null) {
 * 						        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                          }
 *                  2. doCreateBean 真正的创建一个bean实例
 * AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 * 1）每一个bean创建之前，调用postProcessBeforeInstantiation();
 * 关心MathCalculator和LogAspect的创建
 *     1）判断当前bean是否在advisedBeans中（保存了所有需要增强bean）
 *     2）判断当前bean是否是基础类型的  Advice.class || Pointcut.class|| Advisor.class. || AopInfrastructureBean.class ||
 *        或者是否是切面 Aspect.class
 *     3）是否需要跳过
 *         1）获取候选的增强器（切面里面的通知方法）【List<Advisor>  candidateAdvisors】
 *           每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor类型
 *           判断每一个增强器是否是AspectJPointcutAdvisor类型的 如果是返回true
 *         2）返回false
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {
    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }


}
