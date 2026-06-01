package com.lx.test.bug;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Jazen
 * @date 2025/5/29
 */
@Slf4j
public class XmlTest {

    private static final String PARENTNODEVALUE = "parentnodevalue";
    private static final String PARENTNODEPROPERTIE = "parentnodepropertie";
    private static final String PARENTNODEPROPERTIE_NAME = "parentnodepropertie_";

    public static void main(String[] args) {
        Map<String, Object> resp = getXmlMap();
        log.info("对象读取完毕");
        returnAfterDoSubmit(resp, "root");
        log.info("处理完毕");
    }

    private static Map<String, Object> getXmlMap() {
        // 读取【格式化后响应报文.txt】文件，文件内容是XML格式的，读取并得到Map
        Map<String, Object> resp = new HashMap<>();
        try {
            Document doc = XmlUtil.readXML(new File("src/main/java/com/lx/test/bug/格式化后响应报文.txt"));
            resp = XmlUtil.xmlToMap(doc);
            return resp;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return resp;
    }

    public static String returnAfterDoSubmit(Map<String, Object> resp, String defaultRootName) {
        String respStr;
        JSONObject respMap = JSONUtil.parseObj(resp);
        // 如果返回的xml报文没有根节点，这里固定使用请求报文的根节点作为根节点返回
        if (respMap.size() > 1) {
            respStr = XmlUtil.mapToXmlStr(respMap, defaultRootName);
            // log.info("\n返回的报文为:\n {}", respStr);
            return respStr;
        }
        Map.Entry<String, Object> entry = respMap.entrySet().stream().findFirst().get();
        if (entry.getValue() instanceof Map) {
            respStr = mapToFormatStr("1", resp);
            // log.info("\n返回的报文为:\n {}", respStr);
            return respStr;
        }
        respStr = XmlUtil.mapToXmlStr(respMap, defaultRootName);
        // log.info("\n返回的报文为:\n {}", respStr);
        return respStr;
    }

    public static String mapToFormatStr(String msgFormat, Map<String, Object> map) {
        String reqBody = "";
        if ("1".equals(msgFormat)) {
            //  从map拿第一层的唯一的一个key作为rootName
            if (map.size() > 1) {
                log.warn("Format failed, there are multiple root nodes!");
                log.info("Create virtual node 'virtualRoot' as the master node");
                Map<String, Object> newMap = new HashMap<>();
                newMap.put("virtualRoot", map);
                map = newMap;
            }
            String rootName = map.keySet().stream().findFirst().get();
            Document document = XmlUtil.mapToXml((Map<?, ?>) map.get(rootName), rootName);
            Element element = document.getDocumentElement();
            SetXmlProperties(element, map, element.getTagName());
            String xmlString = XmlUtil.toStr(element);
            // 定义 XML 头的结束标记
            String xmlDeclarationEnd = "?>";
            // 查找 XML 声明的结束标记
            int xmlDeclarationEndIndex = xmlString.indexOf(xmlDeclarationEnd) + xmlDeclarationEnd.length();
            // 去除头部信息，从 XML 声明结束之后的第一个字符开始
            reqBody = xmlString.substring(xmlDeclarationEndIndex);
        }

        if ("2".equals(msgFormat)) {
            reqBody = JSONUtil.toJsonStr(map);
        }

        if ("3".equals(msgFormat)) {
            try {
                StringBuilder sb = new StringBuilder();
                for (String key : map.keySet()) {
                    sb.append(key).append("=").append(URLEncoder.encode((String) map.get(key), "UTF-8")).append("&");
                }
                reqBody = sb.toString();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return reqBody;
    }
    private static final Pattern ANY_BSP_PARENTNODEPROPERTIE_PATTERN = Pattern.compile(".*parentnodepropertie_.*");

    public static void SetXmlProperties(Element documentElement, Map<String, Object> map, String xmlPath) {
        JSONObject jsonObject = JSONUtil.parseObj(map);
        if (ANY_BSP_PARENTNODEPROPERTIE_PATTERN.matcher(jsonObject.toString()).matches()) {
            System.out.println("存在！");
            // 获取子节点列表
            NodeList nodeList = documentElement.getChildNodes();
            // 遍历子节点
            List<Node> nodesToRemove = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // 判断节点类型为元素节点
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String elementName = element.getTagName();
                    if (PARENTNODEVALUE.equals(elementName)) {
                        // 处理parentnodevalue元素
                        Node parentNode = node.getParentNode();
                        String elementValue = getElementValue(element);
                        if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element parentElement = (Element) parentNode;
                            // 创建新的文本节点并设置值
                            Document doc = parentElement.getOwnerDocument();
                            Node newTextNode = doc.createTextNode(elementValue);
                            // 插入新文本节点，先获取第一个子节点
                            Node firstChild = parentElement.getFirstChild();
                            if (firstChild == null) {
                                // 如果父元素没有子节点，直接添加新文本节点
                                parentElement.appendChild(newTextNode);
                            } else {
                                // 如果有子节点，将新文本节点插入到第一个子节点之前
                                parentElement.insertBefore(newTextNode, firstChild);
                            }
                            i++;
                        }
                        nodesToRemove.add(node);
                    } else if (elementName.startsWith(PARENTNODEPROPERTIE)) {
                        if (elementName.startsWith(PARENTNODEPROPERTIE_NAME)) {
                            // 2.0版本 处理以parentnodepropertie_开头的元素,更加灵活
                            Node parentNode = node.getParentNode();
                            String elementValue = getElementValue(element);
                            if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element parentElement = (Element) parentNode;
                                String name = elementName.substring(PARENTNODEPROPERTIE_NAME.length());
                                if (StrUtil.isNotBlank(name)) {
                                    parentElement.setAttribute(name, elementValue);
                                } else {
                                    parentElement.setAttribute("name", elementValue);
                                }
                            }
                        } else {
                            // 处理以parentnodepropertie开头的元素
                            Node parentNode = node.getParentNode();
                            String elementValue = getElementValue(element);
                            if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element parentElement = (Element) parentNode;
                                int index = elementValue.indexOf('=');
                                if (index != -1) {
                                    String name = elementValue.substring(0, index);
                                    String value = elementValue.substring(index + 1);
                                    // 新增 键值对
                                    parentElement.setAttribute(name, value);
                                } else {
                                    // 新增默认属性name 键值对
                                    parentElement.setAttribute("name", elementValue);
                                }
                            }
                        }
                        nodesToRemove.add(node);
                    } else {
                        // 正常递归
                        SetXmlProperties(element, map, xmlPath + "." + element.getTagName());
                    }
                }
            }
            // 统一移除标记的节点，避免遍历过程中移除导致问题
            for (Node nodeToRemove : nodesToRemove) {
                if (nodeToRemove != null) {
                    Node parentNode = nodeToRemove.getParentNode();
                    if (parentNode != null) {
                        parentNode.removeChild(nodeToRemove);
                    }
                }
            }
        }
    }

    public static String getElementValue(Element element) {
        StringBuilder value = new StringBuilder();
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                value.append(node.getNodeValue());
            }
        }
        return value.toString();
    }
}
