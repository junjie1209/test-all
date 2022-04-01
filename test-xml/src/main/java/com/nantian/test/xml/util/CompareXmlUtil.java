package com.nantian.test.xml.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 比较两个xml的语意是否相同
 *
 * @author: Junjie Zhang
 * @date: 2022/2/14
 */
public class CompareXmlUtil {
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("D:\\Files\\网络自动化\\测试xml\\start_manualTask_end.xml"));
        Map<String, Object> stringObjectMap = xmlToMap(document);
        System.out.println(stringObjectMap.toString());
    }

    public static <K, V> Map<String, Object> xmlToMap(Document document) throws Exception {
        Element elements = document.getRootElement();
        Map<String, Object> maps = new HashMap<>();
        maps = elementMap(elements,maps);
        return maps;
    }

    /**
     * xml解析为map的方法
     * @param element
     * @param maps
     * @return
     */
    @SuppressWarnings({ "unchecked", "unused" })
    private static Map<String, Object> elementMap(Element element,Map<String, Object> maps) {
        List<Attribute> list = element.attributes();
        for (Iterator<Attribute> it = list.iterator(); it.hasNext();) {
            Attribute attr = it.next();
             maps.put(attr.getName(), attr.getValue());
        }
        if (!("").equals(element.getTextTrim().toString())) {
            maps.put(element.getName(), element.getTextTrim());
        }
        List<Element> eles = element.elements();
        Element elp = null;
        for (Iterator<Element> it = eles.iterator(); it.hasNext();) {
            Element el = it.next();
            elementMap(el,maps);
        }
        return maps;
    }
}
