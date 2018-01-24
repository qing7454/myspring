package com.qing.aop;

import com.qing.factory.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}