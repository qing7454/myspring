package com.qing.ioc;

import java.util.LinkedHashSet;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.ioc</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * 包装一个对象所有的PropertyValue。<br/>
 * 为什么封装而不是直接用一个集合进行装载?因为可以封装一些操作。比如添加值或取值
 * <p>****************************************************************************</p>
 */
public class PropertyValues {

    private LinkedHashSet<PropertyValue> propertyValues = new LinkedHashSet<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValues.add(propertyValue);
    }

    public LinkedHashSet<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
