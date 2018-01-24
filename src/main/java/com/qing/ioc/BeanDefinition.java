package com.qing.ioc;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.ioc</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * 定义对一个bean的实体操作对象，包含bean的内容及元数据，保存到BeanFactory中
 * <p>****************************************************************************</p>
 */
public class BeanDefinition {

    private Object bean;
    private Class<?> beanClass;
    private String beanClassName;
    private PropertyValues propertyValues = new PropertyValues();

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
