package com.qing.beans;

import com.qing.BeanReference;
import com.qing.io.ResourceLoader;
import com.qing.ioc.AbstractBeanDefinitionReader;
import com.qing.ioc.BeanDefinition;
import com.qing.ioc.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.beans</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * 根据xml定义的bean进行读取并赋值注册到bean工厂进行使用
 * <p>****************************************************************************</p>
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private static final String ID_NODE = "id";
    private static final String VALUE_NODE = "value";
    private static final String CLASS_NODE = "class";
    private static final String PROPERTY_NODE = "property";
    private static final String NAME_NODE = "name";
    private static final String REF_NODE = "ref";

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    /**
     * 加载bean
     * @param inputStream 输入流
     * @throws Exception e
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        //创建文档工厂实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        //获取输入流文本
        Document document = documentBuilder.parse(inputStream);
        System.out.println(document);
        // 解析bean
        registerBeanDefinitions(document);
        //关闭输入流，以节省资源
        inputStream.close();
    }

    /**
     * 解析文档节点
     * @param document doc节点
     */
    private void registerBeanDefinitions(Document document) {
        //获取根节点
        Element root = document.getDocumentElement();
        System.out.println(root);
        //解析每个bean以及属性
        parseBeanDefinitions(root);
    }

    /**
     * 处理bean属性
     * @param root root
     */
    private void parseBeanDefinitions(Element root) {
        //获取子节点下的属性
        NodeList nodeList = root.getChildNodes();
        System.out.println(nodeList);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                //解析bean并将值赋值到相应的beans
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 赋值bean属性值，并注入属性值
     * @param ele ele
     */
    private void processBeanDefinition(Element ele) {
        System.out.println(ele);
        //获取属性值
        String name = ele.getAttribute(ID_NODE);
        String className = ele.getAttribute(CLASS_NODE);
        System.out.println(name+", "+className);
        //赋值定义bean实体类的值
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        //存放至BeanDefinitions以注册到实例工厂
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 根据value节点进行赋值到bean
     * @param ele ele
     * @param beanDefinition bean
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        System.out.println(ele+", "+beanDefinition);
        //获取每个bean里面的property值
        NodeList propertyNode = ele.getElementsByTagName(PROPERTY_NODE);
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute(NAME_NODE);
                String value = propertyEle.getAttribute(VALUE_NODE);
                //处理属性值里面是值还是引用，如果是值属性，则为bean注入值，否则赋值引用值
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute(REF_NODE);
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
