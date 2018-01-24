package com.qing;

import com.qing.context.ClassPathXmlApplicationContext;
import com.qing.entity.Demo;
import com.qing.repository.DemoRepository;

import java.util.List;

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
public class TestApplicationContext {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("demo.xml");
        Demo demo = (Demo) context.getBean("demo");
        System.out.println(demo);
        DemoRepository demoRepository = (DemoRepository) context.getBean("demoRepository");
        List<Demo> list = demoRepository.findList();
        list.forEach(System.out::println);
    }
}
