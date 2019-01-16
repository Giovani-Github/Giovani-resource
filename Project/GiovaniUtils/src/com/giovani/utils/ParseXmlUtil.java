package com.giovani.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 解析xml工具
 * 解析前后需开启和关闭，释放资源
 * 需要依赖dom4j-1.6.1.jar
 *
 * @author Giovani
 * @create: 2019/1/11 10:39
 */
public class ParseXmlUtil {

    /**
     * 读取xml的流
     */
    private static ByteArrayInputStream inputStream;

    /**
     * 根节点
     */
    private static Element rootElement;

    /**
     * 开启解析，传入需要解析的xml
     *
     * @param xml 需要解析的xml
     * @return 返回根节点
     */
    public static Element createParse(String xml) {

        if (StringUtil.isEmpty(xml)) {
            return null;
        }

        SAXReader saxReader = new SAXReader();
        try {

            // 读取xml数据，获得根节点
            inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document document = saxReader.read(inputStream);
            rootElement = document.getRootElement();

            return rootElement;

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 获取某个节点下所有子节点的文本内容，通过层级指定节点
     * <root>
     * <person>
     * <info>
     * <name>张三</name>
     * <age>15</age>
     * </info>
     * </person>
     * </root>
     *
     * @param tagNames      要获取的文本所在的节点深度，如需获取<info>标签下所有子标签，则传递如下参数：
     *                      ["person","info"]
     * @param parentElement 从哪个节点开始
     * @return 返回一个有序的map，key:节点名称，value：节点文本内容
     */
    public static LinkedHashMap<String, String> getTierElementText(String[] tagNames, Element parentElement) {

        // 获取层级子节点
        ArrayList<Element> tierElementChilds = getTierElementChilds(tagNames, parentElement);

        // 开始遍历，获取层级节点中最后一个节点的所有子节点文本。
        return getElementText(tierElementChilds.get(tierElementChilds.size() - 1));
    }

    /**
     * 获取某个节点下所有子节点的文本内容
     *
     * @param element 要获取子节点文本内容的节点
     * @return 返回一个有序的map，key:节点名称，value：节点文本内容
     */
    public static LinkedHashMap<String, String> getElementText(Element element) {

        if (element == null) {
            return null;
        }

        // 存储获取到的文本内容
        LinkedHashMap<String, String> stringMap = null;

        // 获取该节点的子节点，的迭代器
        Iterator iterator = element.elementIterator();
        stringMap = new LinkedHashMap<>();
        while (iterator.hasNext()) {
            Element next = (Element) iterator.next();
            Iterator nextIterator = next.elementIterator();

            // 如果该子节点还有子节点，则不是我们要的数据，跳过。
            if (nextIterator.hasNext()) {
                continue;
            }
            stringMap.put(next.getName(), next.getText());
        }

        return stringMap;
    }

    /**
     * 获取根节点下所有子节点的文本内容, 得到如下内容中，<name>和<age>的内容
     * <root>
     * <name>张三</name>
     * <age>13</age>
     * <user>
     * <name1>张四</name1>
     * <age1>14</age1>
     * </user>
     * </root>
     *
     * @return 返回一个有序的map，key:节点名称，value：节点文本内容
     */
    public static LinkedHashMap<String, String> getRootElementText() {
        // 存储获取到的文本内容
        LinkedHashMap<String, String> stringMap = null;

        // 开始遍历，获取根节点下所有子节点的文本
        Iterator iterator = rootElement.elementIterator();
        stringMap = new LinkedHashMap<>();
        while (true) {

            // 当前节点的下一个节点存在，且当前节点的下一个节点没有子节点，则是我们需要的数据，否则停止迭代，因为需要的数据已经读完
            // 判断当前节点的下一个节点是否存在
            if (!iterator.hasNext()) {
                break;
            }

            // 判断下一个节点的子节点是否存在
            Element next = (Element) iterator.next();
            Iterator nextIterator = next.elementIterator();
            if (nextIterator.hasNext()) {
                break;
            }

            stringMap.put(next.getName(), next.getText());
        }

        return stringMap;
    }

    /**
     * 得到某个节点的所有属性值和属性名
     *
     * @param element 需要获得属性的节点
     * @return 返回一个有序的map对象（LinkedHashMap），存储了所有属性值和属性名
     */
    public static LinkedHashMap<String, String> getElementAttributes(Element element) {

        if (element == null) {
            return null;
        }

        // 存储获取到的属性值和属性名
        LinkedHashMap<String, String> stringMap = null;

        // 循环遍历得到节点的所有属性值和属性名
        stringMap = new LinkedHashMap<>();
        Iterator iterator = element.attributeIterator();
        while (iterator.hasNext()) {
            Attribute attribute = (Attribute) iterator.next();
            stringMap.put(attribute.getName(), attribute.getText());
        }

        return stringMap;
    }

    /**
     * 获取某个节点下的所有子节点
     *
     * @param element 需要获取子节点的节点
     * @return 返回一个List(ArrayList < Element >), 存储所有子节点的Element
     */
    public static ArrayList<Element> getElementChilds(Element element) {

        if (element == null) {
            return null;
        }

        // 存储所有子节点的Element
        ArrayList<Element> elements = null;

        // 遍历获取所有子节点
        elements = new ArrayList<>();

        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element next = (Element) iterator.next();
            elements.add(next);
        }

        return elements;
    }

    /**
     * 获取某个节点下层级子节点，即：子节点的子节点
     * <root>
     * <person>
     * <age>
     * <value>12</value>
     * </age>
     * </person>
     * </root>
     *
     * @param tagNames      层级节点名。如需获取如上xml中的，<person><age><value>这几个标签的element，则：["person", "age", "value"]
     * @param parentElement 父节点。要获取的层级标签中，第一个标签的父节点
     * @return 返回一个List(ArrayList < Element >), 存储所有子节点的Element
     */
    public static ArrayList<Element> getTierElementChilds(String[] tagNames, Element parentElement) {

        if (tagNames.length <= 0) {
            return null;
        }

        for (String tagName : tagNames) {
            if (StringUtil.isEmpty(tagName)) {
                return null;
            }
        }

        // 存储所有子节点的element
        ArrayList<Element> elements = new ArrayList<>();

        // 当前遍历得到的element，先获取父节点下的第一个子节点
        Element currentElement = parentElement.element(tagNames[0]);
        elements.add(currentElement);

        // 循环获取到数组中的最后一个节点的Element，因为刚开始就获取了根节点下的第一个子节点，所以从1开始获取
        for (int i = 1; i < tagNames.length; i++) {
            currentElement = currentElement.element(tagNames[i]);
            elements.add(currentElement);
        }

        return elements;
    }

    /**
     * 关闭解析
     */
    public static void closeParse() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
