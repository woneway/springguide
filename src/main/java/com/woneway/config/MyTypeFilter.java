package com.woneway.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:49 下午
 * @description
 */
public class MyTypeFilter implements TypeFilter {
    /**
     * @param metadataReader        the metadata reader for the target class 读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory a factory for obtaining metadata readers 可以获取其他任何类信息
     * @return
     * @throws IOException
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类资源(类的路径)
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        System.out.println("-----> " + className);
        /**
         * -----> com.woneway.test.IOCTest
         * -----> com.woneway.MainTest
         * -----> com.woneway.bean.Person
         * -----> com.woneway.config.MyTypeFilter
         * -----> com.woneway.controller.BookController
         * -----> com.woneway.dao.BookDao
         * -----> com.woneway.service.BookService
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         * mainConfig
         * IOCTest
         * mainTest
         * person
         * myTypeFilter
         * bookController
         * bookDao
         * bookService
         */
        if (className.contains("Test")) {
            //加入这个if语句之后的结果
            /**
             *-----> com.woneway.test.IOCTest
             * -----> com.woneway.MainTest
             * -----> com.woneway.bean.Person
             * -----> com.woneway.config.MyTypeFilter
             * -----> com.woneway.controller.BookController
             * -----> com.woneway.dao.BookDao
             * -----> com.woneway.service.BookService
             * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
             * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
             * org.springframework.context.annotation.internalCommonAnnotationProcessor
             * org.springframework.context.event.internalEventListenerProcessor
             * org.springframework.context.event.internalEventListenerFactory
             * mainConfig
             * person
             * myTypeFilter
             * bookController
             * bookDao
             * bookService
             */
            return false;
        }

        return true;
    }
}
