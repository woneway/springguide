package com.woneway.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/12 10:29 上午
 * @description
 */
//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {

    //返回一个Color对象，这个对象会添加到容器中
    public Color getObject() throws Exception {
        return new Color();
    }

    //对象类型
    public Class<?> getObjectType() {
        return Color.class;
    }

    //是否单例
    public boolean isSingleton() {
        return true;
    }
}
