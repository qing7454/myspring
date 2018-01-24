package com.qing;

import com.qing.beans.AutowireCapableBeanFactory;
import com.qing.beans.XmlBeanDefinitionReader;
import com.qing.entity.Demo;
import com.qing.factory.AbstractBeanFactory;
import com.qing.io.ResourceLoader;
import com.qing.ioc.BeanDefinition;

import java.util.Map;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World.");
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("demo.xml");

        AbstractBeanFactory factory = new AutowireCapableBeanFactory();

        //注册到factory
        for (Map.Entry<String, BeanDefinition> map : reader.getRegistry().entrySet()) {
            factory.registerBeanDefinition(map.getKey(), map.getValue());
        }

        //获取bean
        Demo demo = (Demo) factory.getBean("demo");
        System.out.println(demo.toString());

    }
}
