package com.giovani.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * 拼接xml工具
 * 需要依赖dom4j-1.6.1.jar
 *
 * @author Giovani
 * @create: 2019/1/11 14:12
 */
public class JointXmlUtil {

    /**
     * 根节点
     */
    private static Element rootElement;
    /**
     * xml文本对象
     */
    private static Document document;

    /**
     * 创建根节点
     *
     * @param tagName 根节点名称
     * @return 返回根节点Element
     */
    public static Element addRootElement(String tagName) {

        if (StringUtil.isEmpty(tagName)) {
            return null;
        }
        document = DocumentHelper.createDocument();
        rootElement = document.addElement(tagName);
        return rootElement;
    }

    /**
     * 连续创建多级子节点
     * <root>
     * <person>
     * <info>
     * <name>
     * 张三
     * </name>
     * </info>
     * </person>
     * </root>
     *
     * @param tagNames      多级子节点各自的名称，如需创建如上格式，则：["pserson", "info", "name"]
     * @param parentElement 父节点
     * @return 返回一个ArrayList<Element>，包含所创建节点的Element
     */
    public static ArrayList<Element> addChildElements(String[] tagNames, Element parentElement) {
        // 当前循环创建的element
        Element currentElement = null;
        // 存储已创建的Element
        ArrayList<Element> elementList = null;

        // 判断参数名是否合法
        for (int i = 0; i < tagNames.length; i++) {
            if (StringUtil.isEmpty(tagNames[i])) {
                return null;
            }
        }
        if (parentElement == null) {
            return null;
        }

        currentElement = parentElement;
        elementList = new ArrayList<>();

        // 循环创建子Element
        for (int i = 0; i < tagNames.length; i++) {
            currentElement = addChildElement(tagNames[i], currentElement);
            elementList.add(currentElement);
        }

        return elementList;

    }

    /**
     * 连续创建多个子节点，并为每个子节点添加文本内容
     * <root>
     * <parent>
     * <info>
     * <name>张三</name>
     * <age>12</age>
     * <gender>男</gender>
     * </info>
     * </parent>
     * </root>
     *
     * @param elements      多个子节点，如需创建如上格式中的<name><age><gender>标签并插入文本内容，则：
     *                      ArrayLsit<String[]> list = new ArrayList<String[]>();
     *                      list.add(new String[]{"name", "张三"})
     *                      list.add(new String[]{"age", "12"})
     *                      list.add(new String[]{"gender", "男"})
     * @param parentElement 父节点
     * @return 返回一个List(ArrayList < Element >)，存储创建的所有子节点Element
     */
    public static ArrayList<Element> addElements(ArrayList<String[]> elements, Element parentElement) {
        // 当前循环创建的element
        Element currentElement = null;
        // 存储已创建的Element
        ArrayList<Element> elementList = new ArrayList<>();

        if (parentElement == null) {
            return null;
        }

        // 遍历取出要创建的每个子节点数据，进行创建
        for (String[] element : elements) {
            // 判断标签名是否合法
            if (StringUtil.isEmpty(element[0])) {
                return null;
            }

            // 创建子节点
            currentElement = addChildElement(element[0], parentElement);
            elementList.add(currentElement);
            // 为子节点添加文本内容
            currentElement.addText(element[1]);

        }

        return elementList;
    }

    /**
     * 创建某个节点的子节点
     *
     * @param tagName       节点名称
     * @param parentElement 父节点
     * @return 返回子节点Element
     */
    public static Element addChildElement(String tagName, Element parentElement) {

        if (StringUtil.isEmpty(tagName)) {
            return null;
        }

        if (parentElement == null) {
            return null;
        }

        return parentElement.addElement(tagName);
    }

    /**
     * 把生成好的xml，输出成字符串xml
     *
     * @return 返回字符串xml
     */
    public static String xmlToString() {
        return document.asXML();
    }

    /**
     * 把生成好的xml，输出成xml文件
     *
     * @param path 输出的本地路径，例: C:\\Demo.xml，"\"需要转移
     */
    public static void xmlToFile(String path) {
        try {
            Writer fileWriter = new FileWriter(path);
            XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
