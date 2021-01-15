package com.data.itsv.util;

import com.alibaba.fastjson.JSON;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.JavaUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import javax.xml.soap.SOAPException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class webServiceUtils {

    // 日志记录器
    private static final Logger logger = LogManager.getLogger(webServiceUtils.class);




    /**
     * 获取webservice 接口
     *
     * @param url       地址
     * @param namespace 命名空间
     * @param method    方法名
     * @return
     */
    public static String getWebservice(String url,
                                       String namespace, Map<String,String> map, String method) {
        String retStr = "";
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            // QName qName = new QName("http://inter.webservice.cy.cn/", "getInfo");
            QName qName = new QName(namespace, method);
            call.setOperationName(qName);// WSDL里面描述的接口名称

            Object[] objects = new Object[map.size()];
            int i = 0;
            for (String str : map.keySet()) {
                objects[i] = map.get(str);
                QName qname = new QName(namespace, str);
                call.addParameter(qname, XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数
            }

            call.setTimeout(Integer.valueOf(20000));// 这里面超时时间设大点
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            retStr = (String) call.invoke(objects);              // 给方法传递参数，并且调用方法
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        } catch (RemoteException e) {
            logger.error(e.getMessage(), e);
        }
        return retStr;
    }


    /**
     * @param wsdlUrl         wsdl的地址：http://localhost:8001/demo/HelloServiceDemoUrl?wsdl
     *                        http://10.3.136.225:8000/AqscWebMIS/services/GwaqscJxglService?wsdl
     * @param methodName      调用的方法名称 selectOrderInfo
     * @param targetNamespace 命名空间 http://service.limp.com/
     * @param name            name HelloServiceDemo
     * @param paramList       参数集合
     * @throws Exception
     */

    public static String dynamicCallWebServiceByCXF(String wsdlUrl, String methodName, String targetNamespace, String name, List<Object> paramList) throws Exception {

        //临时增加缓存，增加创建速度


        // 创建动态客户端

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();

        // 创建客户端连接

        Client client = factory.createClient(wsdlUrl);

        ClientImpl clientImpl = (ClientImpl) client;

        Endpoint endpoint = clientImpl.getEndpoint();


        // Make use of CXF service model to introspect the existing WSDL

        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);

        // 创建QName来指定NameSpace和要调用的service

        String localPart = name + "SoapBinding";

        QName bindingName = new QName(targetNamespace, localPart);

        BindingInfo binding = serviceInfo.getBinding(bindingName);


        //创建QName来指定NameSpace和要调用的方法绑定方法

        QName opName = new QName(targetNamespace, methodName);//selectOrderInfo


        BindingOperationInfo boi = binding.getOperation(opName);

//		BindingMessageInfo inputMessageInfo = boi.getInput();

        BindingMessageInfo inputMessageInfo = null;

        if (!boi.isUnwrapped()) {

            //OrderProcess uses document literal wrapped style.

            inputMessageInfo = boi.getWrappedOperation().getInput();

        } else {

            inputMessageInfo = boi.getUnwrappedOperation().getInput();

        }


        List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();


        /***********************以下是初始化参数，组装参数；处理返回结果的过程******************************************/

        Object[] parameters = new Object[parts.size()];

        for (int m = 0; m < parts.size(); m++) {

            MessagePartInfo part = parts.get(m);

            // 取得对象实例

            Class<?> partClass = part.getTypeClass();//OrderInfo.class;

            System.out.println(partClass.getCanonicalName()); // GetAgentDetails

            //实例化对象

            Object initDomain = null;

            //普通参数的形参，不需要fastJson转换直接赋值即可

            String string = paramList.get(m).toString();
            if ("java.lang.String".equalsIgnoreCase(partClass.getCanonicalName())

                    || "int".equalsIgnoreCase(partClass.getCanonicalName())) {

                initDomain = string;

            }

            //如果是数组

            else if (partClass.getCanonicalName().indexOf("[]") > -1) {

                //转换数组

                Class<?> componentType = partClass.getComponentType();
                initDomain = JSON.parseArray(string, componentType);

            } else {

                initDomain = JSON.parseObject(string, partClass);

            }

            parameters[m] = initDomain;


        }

        //定义返回结果集

        Object[] result = null;

        //普通参数情况 || 对象参数情况  1个参数 ||ArryList集合

        try {

            result = client.invoke(opName, parameters);

        } catch (Exception ex) {

            ex.printStackTrace();

            return "参数异常" + ex.getMessage();

        }
        //String[] value = param.toArray(new String[0]);

        //返回调用结果

        logger.info("公务--------------------------------接收到得数据为 ：" + result.toString());
        if (result.length > 0) {
			/*System.out.println(result[0]);
			Object json = JSON.toJSON(result[0]);
			System.out.println(json);

			return  json.toString();*/

            //return JSON.toJSONString(result[0]);
            logger.info("公务--------------------------------接收到得数据为 ：" + (String) result[0]);
            return (String) result[0];

        }
        return "invoke success, but is void ";

    }

    /**
     * 使用SOAP1.1发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_1(String postUrl, String soapXml,
                                       String soapAction, List list, String method, String ns) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);


        StringBuffer stringBuffer = new StringBuffer();
        //拼接参数
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append("<arg" + i + ">" + list.get(i) + "</arg" + i + ">");
        }

        //拼接SOAP
        StringBuffer soapRequestData = new StringBuffer("");
        soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"" + ns + "\">");
        //soapRequestData.append(ns);
        soapRequestData.append("<soapenv:Header/>");
        soapRequestData.append("<soapenv:Body>");
        soapRequestData.append("<ser:" + method + ">");
        soapRequestData.append(stringBuffer);
        soapRequestData.append("</ser:" + method + ">");
        soapRequestData.append("</soapenv:Body>" + "</soapenv:Envelope>");


        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapRequestData.toString(),
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                logger.info("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            logger.error("exception in doPostSoap1_1", e);
        }
        return retStr;
    }


}
