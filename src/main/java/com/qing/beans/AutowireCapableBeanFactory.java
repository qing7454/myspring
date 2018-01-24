package com.qing.beans;

import com.qing.BeanReference;
import com.qing.aop.BeanFactoryAware;
import com.qing.factory.AbstractBeanFactory;
import com.qing.ioc.BeanDefinition;
import com.qing.ioc.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.beans</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                //获取filed type
                //String fullTypeName = declaredField.getType().getName();//这是全名，如：java.lang.String
                String fieldType = declaredField.getType().getSimpleName();
                Object val = parseValue(value, fieldType);
                declaredField.set(bean, val);
            }
        }
    }

    /**
     * 根据fieldType进行类型转换，目前只是做一个简单的转换，未考虑到其它复杂情况，以后扩展
     * @param value 属性值
     * @param fieldType 属性值类型
     * @return 属性值
     */
    private Object parseValue(Object value, String fieldType) {
        Object result = "";
        switch (fieldType) {
            case "Long":
                result = Long.parseLong(value + "");
                break;
            case "Integer":
                result = Integer.parseInt(value + "");
                break;
            case "String":
                result = value + "";
                break;
            case "BigDecimal":
                result = BigDecimal.valueOf((Long) value);
                break;
            case "Date":
                result = Date.valueOf(value+"");
                break;
            case "TimeStamp":
                result = Date.valueOf(value+"");
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
