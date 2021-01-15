package com.data.itsv.util;

import com.data.itsv.model.VDirectory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dom {

    public static List<Map> getData(String strMessContent) {
        //String strMess=new String(strMessContent,"GB2312");
        StringReader read = new StringReader(strMessContent);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        Document doc = null;
        try {
            doc = sb.build(source);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();

        List list = new ArrayList<>();
        try {

            list.add(com.data.itsv.util.Dom.getDataMap(root));
        } catch (Exception e) {
            // TODO: handle exception
        }

        return list;
    }

    //递归获取子元素  group特殊处理
    public static Map getDataMap(Element root) {

        Map<String,Object> oneMap = new HashMap<String,Object>();
        try {
            Element et = null;
            List nodes = root.getChildren();
            for (int i = 0; i < nodes.size(); i++) {
                et = (Element) nodes.get(i);// 循环依次得到子元素
                String name = et.getName();
                String value = et.getValue();
                // 获取元素的孩子数目
                List fsize = et.getChildren();

                if (fsize.size() == 0) {
                    oneMap.put(name, value);
                } else {
                    String[] split = nodes.toString().split(name);
                    if (split.length > 2) {
                        List list = (List) oneMap.get(name);
                        if (list == null) {
                            list = new ArrayList<>();
                            oneMap.put(name, list);
                        }
                        list.add(com.data.itsv.util.Dom.getDataMap((Element) nodes.get(i)));
                    } else {
                        oneMap.put(name, com.data.itsv.util.Dom.getDataMap(et));
                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return oneMap;
    }


    public static String getXml(Map map, Map<String,VDirectory> vDirectoryMap,String type) {

        Element response = new Element("Root");
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();

        getXmlElement(map,response,vDirectoryMap,type);



        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        System.out.println(Doc.getParent());


        return xmlinfo;
    }


    public static void getXmlElement(Map<String,Object> map, Element father,Map<String,VDirectory> vDirectoryMap,String type) {

        for (Object o : map.keySet()) {
            Object o1 = map.get(o);
            if(o1 instanceof  String){
                Element result = new Element("node");
                result.setAttribute("id", vDirectoryMap.get((String) o).getId()+"");
                result.setAttribute("type", type);
                result.setAttribute("getVideoFlag", "false");
                result.setAttribute("name", vDirectoryMap.get((String) o).getName());
                father.addContent(result);
            }else if(o1 instanceof Map){
                Element result = new Element("node");
                result.setAttribute("id", vDirectoryMap.get((String) o).getId()+"");
                result.setAttribute("type", type);
                result.setAttribute("getVideoFlag", "false");
                result.setAttribute("name", vDirectoryMap.get((String) o).getName());
                father.addContent(result);
                getXmlElement((Map)o1,result,vDirectoryMap,type);
            }
        }


    }

    /**
     * 生成返回命令 xml
     *
     * @param map
     * @return
     */
    public static String getXmlStrResponse(Map<String,Object> map) {
        Element response = new Element("response");
        //根元素标签内的属性名与值
        response.setAttribute("command", (String) map.get("command"));
        //生成Doc文档
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();
        Object object = map.get("result");
        if (object != null) {
            String resultStr = (String) object;
            //生成新的元素
            Element result = new Element("result");
            result.setAttribute("code", resultStr);
            if ("0".equals(resultStr)) {
                result.addContent("success");
            } else if ("1".equals(resultStr)) {
                result.addContent("用户名错误");
            } else if ("2".equals(resultStr)) {
                result.addContent("密码错误");
            } else if ("-1".equals(resultStr)) {
                result.addContent("登陆失败");
            } else if ("200".equals(resultStr)) {
                result.addContent("登陆限制");
            }
            //加入根级元素中
            response.addContent(result);
        }

        getXmlElement(map, response);


        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        xmlinfo = xmlinfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>");

        System.out.println(Doc.getParent());


        return xmlinfo;
    }

    /**
     * 生成发送xml
     *
     * @param map
     * @return
     */
    public static String getXmlStrRequest(Map<String,Object> map) {
        Element response = new Element("request");
        //根元素标签内的属性名与值
        response.setAttribute("command", (String) map.get("command"));
        //生成Doc文档
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();


        getXmlElement(map, response);


        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        xmlinfo = xmlinfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>");

        System.out.println(Doc.getParent());


        return xmlinfo;
    }

    /**
     * 递归生成xml
     *
     * @param map    数据map
     * @param father 父节点
     */

    public static void getXmlElement(Map<String,Object> map, Element father) {


        for (Map.Entry<String,Object> entry2 : map.entrySet()) {
            Object value = entry2.getValue();
            if (value instanceof String && !"command".equals(entry2.getKey()) && !"result".equals(entry2.getKey())) {//是字符串直接生成元素 并添加到父节点
                //生成新的元素
                Element result = new Element(entry2.getKey());
                result.addContent((String) value);
                father.addContent(result);
            } else if (value instanceof List && !"command".equals(entry2.getKey()) && !"result".equals(entry2.getKey())) {//是 list集合 遍历集合生成每一个 节点元素 并添加到父节点  递归循环
                ArrayList<Map> list = (ArrayList<Map>) value;
                for (Map<String,Object> map1 : list) {
                    //生成新的元素
                    Element result = new Element(entry2.getKey());
                    father.addContent(result);
                    getXmlElement(map1, result);
                }
            } else if (value instanceof Map && !"command".equals(entry2.getKey()) && !"result".equals(entry2.getKey())) {//是 map 生成节点 添加到父节点 并递归循环
                //生成新的元素
                Element result = new Element(entry2.getKey());
                father.addContent(result);
                getXmlElement((Map) entry2.getValue(), result);
            }
        }

    }
}
