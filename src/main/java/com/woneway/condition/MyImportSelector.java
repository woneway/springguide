package com.woneway.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 7:56 下午
 * @description
 */
public class MyImportSelector implements ImportSelector {
    /**
     *  返回值， 就是导入到容器中的组件全类名
     *  AnnotationMetadata: 当前标注@Import注解的类的所有注解信息
     * @param importingClassMetadata
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.woneway.bean.Blue"};
    }
}
