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
 * 从配置中读取BeanDefinition，以便BeanFactory实例化
 * <p>****************************************************************************</p>
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception ;
}
