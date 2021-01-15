package com.data.itsv.service.impl;

import com.data.itsv.mapper.VAlarmMapper;
import com.data.itsv.model.*;
import com.data.itsv.model.vo.*;
import com.data.itsv.netty.ProtocolRequestProcesser;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.service.*;
import com.data.itsv.util.DateFormatHelper;
import com.data.itsv.util.HashMapHelper;
import com.data.itsv.util.SocketHelper;
import com.github.pagehelper.PageInfo;
import io.netty.channel.ChannelHandlerContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;


@Service("ProtocolService")
public class ProtocolServiceImpl implements ProtocolService {

    @Autowired
    private UserService userService;
    @Autowired
    private VAlarmSetService vAlarmSetService;
    @Autowired
    private ProtocolRequestProcesser protocolRequestProcesser;

    @Autowired
    private VVideoPresetService vVideoPresetService;

    @Autowired
    private VideoService videoService;
    @Autowired
    private VItourService itourService;
    @Autowired
    private VIlayoutService ilayoutService;
    @Autowired
    private VVideoPresetService videoPresetService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private DirService dirService;
    @Value("heartbeatTime")
    private  String heartbeatTime;
    @Override
    public void register(RequestModel rm, String account, String password) {
        StringBuffer sf = new StringBuffer();

        // 查询服务信息
        SUser um = userService.cuUserLogin(account, password);
        String resultCode = "1";
        String result = " user non-existent!";
        if (um.getCode() != null) {

            if ("0".equals(um.getLoginState())) {
                resultCode = "1";
                result = "登录失败，用户名密码错误或该用户未启用！";

            } else if ("2".equals(um.getLoginState())) {
                resultCode = "2";
                result = "登录失败，用户未在有效登录时间内！";

            } else if ("3".equals(um.getLoginState())) {
                resultCode = "3";
                result = "用户已在其他地方登陆，请注销后在登录！";

            } else if ("1".equals(um.getLoginState())) {
                resultCode = "0";
                result = "登陆成功！";
                um.setLastHeartBeatTime(new Date());
                HashMapHelper.getLoginUserMap().put(
                        um.getCode(), um);
                // 保存登陆信息
                HashMapHelper.getUserConnectionMap().put(
                        um.getCode(), rm.getCtx());
                // 查询用户订阅告警信息
                HashMapHelper.getUserOrderAlarmMap().put(
                        um.getCode(),
                        vAlarmSetService.getAlarmResByUserCode(um.getCode()));

            }
        }

        // 构造返回命令

        sf.append("<response command='CSRegister'>");
        sf.append("<result code='"
                + resultCode
                + "'>"
                + result
                + "</result>"
                + "<parameters>"
                + "<userName>"
                + um.getName()
                + "</userName>"
                + "<id>"
                + um.getCode()
                + "</id>"
                + "<cmsIp>"
                + um.getCmsIP()
                + "</cmsIp>"
                + "<cmsPort>"
                + um.getCmsPort()
                + "</cmsPort>"
                + "<funcPrivilege>"
                + um.getFuncPrivilege()
                + "</funcPrivilege>"

                + "<keepAlivePeriod>"
                + heartbeatTime
                + "</keepAlivePeriod>"

                + "<currentTime>"
                + DateFormatHelper.date2String(new Date(),
                "yyyy-MM-dd HH:mm:ss") + "</currentTime>"
                + "</parameters>");
        sf.append("</response>");

        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }
    }

    @Override
    public void keepAlive(RequestModel rm, String userCode,
                          String keepAlivePeriod) {
        StringBuffer sf = new StringBuffer();

        String resultCode = "0";
        String result = "success";
        Set<String> strings = HashMapHelper.getUserConnectionMap().keySet();
        for (String string : strings) {
            if(HashMapHelper.getUserConnectionMap().get(string).equals(rm.getCtx())){
                userCode=string;
            }
        }

       /* HashMapHelper.getLogMap().put(UUIDHelper.getUUID(),
                new LogModel("1", "编号为" + userCode + "服务向ITSV进行心跳保持操作！"));*/
        // 判断心跳是否超时
        resultCode = checkUserHeartBeat(userCode);
        // LoginUserModel um=HashMapHelper.getLoginUserMap().get(userCode);
        // if (um == null) {
        // resultCode="-1";
        // result = "该用户已断开连接！";
        // } else {
        // // 验证用户心跳是否有效
        // Date tempDate = um.getLastHeartBeatTime();
        // Date newTime = new Date();
        // // 最后心跳时间 3个心跳周期
        // if (newTime.getTime() - tempDate.getTime() < 3 *
        // Integer.parseInt(keepAlivePeriod) * 1000) {
        //
        // um.setLastHeartBeatTime(new Date());
        // } else {
        // resultCode="-2";
        // result = "该用户已心跳已超时！";
        //
        //
        // }
        //
        // }
        //
        // //判断是否需要释放资源
        // if(!"0".equals(resultCode)){
        // // 释放资源
        // releaseUserResource(userCode);
        //
        // }
        if ("-1".equals(resultCode)) {

            result = "该用户已断开连接！";
        } else if ("-2".equals(resultCode)) {

            result = "该用户已心跳已超时！";
        }

        // 判断是否需要释放资源
        if (!"0".equals(resultCode)) {
            // 释放资源
            releaseUserResource(userCode);
            // 断开连接
            rm.getCtx().close();
            return;
        }

        // 构造返回命令

        sf.append("<response command='KeepAlive'>");
        sf.append("<result code='" + resultCode + "'>" + result + "</result>"
                + "<parameters>" + "<keepAlivePeriod>" + keepAlivePeriod
                + "</keepAlivePeriod>" + "</parameters>");
        sf.append("</response>");
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());

        }
    }

    @Override
    public void reportCameraState(RequestModel rm, ArrayList<AMQDeviceStateModel> list) {

        videoService.reportCameraState(list);
    }

    @Override
    public void reportAlarm(RequestModel rm, ArrayList<Object> list) {
        // TODO Auto-generated method stub
        AMQMessageModel amq = new AMQMessageModel();
        amq.setType("alarmInfomation");
        amq.setList(list);
        // 发送订阅的信息
        protocolRequestProcesser.productMessage(amq);
        //处理告警信息
        vAlarmSetService.reportAlarm((ArrayList<Object>) list);
    }

    @Override
    public void reportCSCameraState(ChannelHandlerContext session, ArrayList<Object> list) {
        // TODO Auto-generated method stub
        BaseMsg bm = SocketHelper.getBm();
        int totalPackage = 10;
        if (list.size() == 0) {
            // 构造返回命令
            StringBuffer sf = new StringBuffer();
            sf.append("<request command=\"ReportCameraState\">");
            sf.append("<parameters>");
            sf.append("<group>");
            sf.append("<URL>");
            sf.append("<resId>" + "</resId>");
            sf.append("<state>" + "</state>");
            sf.append("</URL>");
            sf.append("</group>");
            sf.append("</parameters>");
            // 发送请求方
            {
                SocketHelper.sendResponse(session, bm, sf.toString());
            }

        } else {
            int countor = 0;
            int k = 0;
            // 构造返回命令
            StringBuffer sf = new StringBuffer();
            StringBuffer bodysf = new StringBuffer();
            for (int i = k; i < list.size(); i++) {
                countor++;
                k++;
                AMQDeviceStateModel temp = (AMQDeviceStateModel) list.get(i);
                bodysf.append("<URL>");
                bodysf.append("<resId>" + temp.getResId() + "</resId>");
                bodysf.append("<state>" + temp.getStateS() + "</state>");
                bodysf.append("</URL>");
                if (k == list.size()) {
                    sf.append("<request command=\"ReportCameraState\">");
                    sf.append("<parameters>");
                    sf.append("<group>");
                    sf.append(bodysf);
                    sf.append("</group>");
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                } else if (countor == totalPackage) {
                    sf.append("<request command=\"ReportCameraState\">");
                    sf.append("<parameters>");
                    sf.append("<group>");
                    sf.append(bodysf);
                    sf.append("</group>");
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }
            }
        }

    }

    @Override
    public void reportCSAlarm(ChannelHandlerContext session, ArrayList<Object> list) {
        // TODO Auto-generated method stub
        BaseMsg bm = SocketHelper.getBm();
        int totalPackage = 10;
        if (list.size() == 0) {
            // 构造返回命令
            StringBuffer sf = new StringBuffer();
            sf.append("<request command=\"ReportAlarm\">");
            sf.append("<parameters>" + "<alarmInfo>");
            sf.append("<id>" + "</id>");
            sf.append("<sessionId>" + "</sessionId>");
            sf.append("<name>" + "</name>");
            sf.append("<description>" + "</description>");
            sf.append("<alarmType>" + 0 + "</alarmType>");
            sf.append("<alarmLevel>" + "</alarmLevel>");
            sf.append("<beginTime>" + "</beginTime>");
            sf.append("<endTime>" + "</endTime>");
            sf.append("<alarmState>" + "</alarmState>");
            sf.append("<affirmMan>" + "</affirmMan>");
            sf.append("<affirmTime>" + "</affirmTime>");
            sf.append("<cleanMan>" + "</cleanMan>");
            sf.append("<cleanTime>" + "</cleanTime>");
            sf.append("<isLinkPic>" + "</isLinkPic>");
            sf.append("</alarmInfo>" + "</parameters>");

            // 发送请求方
            {
                SocketHelper.sendResponse(session, bm, sf.toString());
            }

        } else {
            int countor = 0;
            int k = 0;
            // 构造返回命令
            StringBuffer sf = new StringBuffer();
            StringBuffer bodysf = new StringBuffer();
            for (int i = k; i < list.size(); i++) {
                countor++;
                k++;
                VAlarmVo temp = (VAlarmVo) list.get(i);
                bodysf.append("<alarmInfo>");
                bodysf.append("<id>" + temp.getId() + "</id>");
                bodysf.append("<sessionId>" + session.hashCode() + "</sessionId>");
                bodysf.append("<name>" + temp.getName() + "</name>");
                if (temp.getDescription() == null) {
                    bodysf.append("<description>" + "</description>");
                } else {
                    bodysf.append("<description>" + temp.getDescription()
                            + "</description>");
                }

                if (temp.getAlarmType() == null) {
                    bodysf.append("<alarmType>" + "</alarmType>");
                } else {
                    bodysf.append("<alarmType>" + temp.getAlarmType()
                            + "</alarmType>");
                }

                if (temp.getAlarmlevel() == null) {
                    bodysf.append("<alarmLevel>" + "</alarmLevel>");
                } else {
                    bodysf.append("<alarmLevel>" + temp.getAlarmlevel()
                            + "</alarmLevel>");
                }

                if (temp.getBegintime() == null) {
                    bodysf.append("<beginTime>" + "</beginTime>");
                } else {
                    bodysf.append("<beginTime>" + temp.getBegintime()
                            + "</beginTime>");
                }

                if (temp.getEndtime() == null) {
                    bodysf.append("<endTime>" + "</endTime>");
                } else {
                    bodysf.append("<endTime>" + temp.getEndtime()
                            + "</endTime>");
                }

                if (temp.getAlarmstate() == null) {
                    bodysf.append("<alarmState>" + "</alarmState>");
                } else {
                    bodysf.append("<alarmState>" + temp.getAlarmstate()
                            + "</alarmState>");
                }

                if (temp.getAffirmman() == null) {
                    bodysf.append("<affirmMan>" + "</affirmMan>");
                } else {
                    bodysf.append("<affirmMan>" + temp.getAffirmman()
                            + "</affirmMan>");
                }

                if (temp.getAffirm() == null) {
                    bodysf.append("<afirmTime>" + "</afirmTime>");
                } else {
                    bodysf.append("<afirmTime>" + temp.getAffirm()
                            + "</afirmTime>");
                }
                if (temp.getCleanman() == null) {
                    bodysf.append("<cleanMan>" + "</cleanMan>");
                } else {
                    bodysf.append("<cleanMan>" + temp.getCleanman()
                            + "</cleanMan>");
                }
                if (temp.getCleantime() == null) {
                    bodysf.append("<cleanTime>" + "</cleanTime>");
                } else {
                    bodysf.append("<cleanTime>" + temp.getCleantime()
                            + "</cleanTime>");
                }
                bodysf.append("<isLinkPic>" + "</isLinkPic>");
                bodysf.append("</alarmInfo>");
                if (k == list.size()) {
                    sf.append("<request command=\"ReportAlarm\">");
                    sf.append("<parameters>");
                    sf.append(bodysf);
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }
                if (countor == totalPackage) {
                    sf.append("<request command=\"ReportAlarm\">");
                    sf.append("<parameters>");
                    sf.append(bodysf);
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }
            }
        }

    }

    @Override
    public void getLayout(RequestModel rm, String id, String layoutId) {



        String pageSize = "30";
        int totalPackage = 0;
        // 调用接口
        PageInfo<VIlayoutVo> list = ilayoutService.getILayout(id, layoutId, id,
                pageSize, "1");
        if (list.getList().size() > 0) {
            totalPackage = list.getPages();
        } else {
            totalPackage = 0;
        }
        String resultCode = "0";
        String result = "success";
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            StringBuffer sf = new StringBuffer();

            sf.append("<response command=\"GetLayout\">");
            sf.append("<result code=\"" + resultCode + "\">" + result
                    + "</result>");
            sf.append("<parameters>");

            sf.append("<totalPkt>" + 0 + "</totalPkt>");
            sf.append("<pktNum>" + 0 + "</pktNum>");
            sf.append("<layoutGroup>");
            sf.append("</layoutGroup>");
            sf.append("</parameters>");
            sf.append("</response>");
            // 发送请求方
            {
                SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                        .toString());
            }
        } else {
            for (int i = 0; i < totalPackage; i++) {
                int temp = i + 1;
                list = ilayoutService.getILayout(id, layoutId, id, pageSize, temp + "");

                // 构造返回命令
                StringBuffer sf = new StringBuffer();

                sf.append("<response command=\"GetLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + result

                        + "</result>");
                sf.append("<parameters>");

                sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                sf.append("<pktNum>" + i + "</pktNum>");
                sf.append("<layoutGroup>");
                List<VIlayoutVo> list1 = list.getList();
                for (int k = 0; list1.size() > k; k++) {

                    sf.append("<layout>");
                    sf.append("<layoutId>" + list1.get(k).getId()
                            + "</layoutId>");
                    sf.append("<layoutName>" + list1.get(k).getLayoutName()
                            + "</layoutName>");
                    if (list1.get(k).getScreenNum() == null) {
                        sf.append("<screenNum>" + 0 + "</screenNum>");
                    } else {
                        sf.append("<screenNum>" + list1.get(k).getScreenNum()
                                + "</screenNum>");
                    }
                    sf.append("<camResGroup>");
                   /* List<VIlayoutVo> iLayoutSourcelist = itourService
                            .getILayoutDetail(id, list.get(k).getId());*/
                    List<VIlayoutDetailVo> iLayoutSourcelist = list1.get(k).getDetailList();
                    for (int j = 0; j < iLayoutSourcelist.size(); j++) {
                        sf.append("<camRes>");
                        sf.append("<resId>"
                                + iLayoutSourcelist.get(j).getVideoCode()
                                + "</resId>");
                        sf.append("<resName>"
                                + iLayoutSourcelist.get(j).getVideoName()
                                + "</resName>");
                        sf.append("<resIndex>"
                                + iLayoutSourcelist.get(j).getLocationNum()
                                + "</resIndex>");
                        sf.append("</camRes>");
                    }

                    sf.append("</camResGroup>");
                    sf.append("</layout>");
                }
                sf.append("</layoutGroup>");
                sf.append("</parameters>");
                sf.append("</response>");
                // 发送请求方
                {
                    SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                            .toString());
                }
            }

        }
    }

    @Override
    public void getCatolog(RequestModel rm, String useId, String treeType) {

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        String result = new String();
        // 获取用户目录树
        if ("0".equals(treeType)) {
            // TODO Auto-generated method stub
            // 查询服务信息
            result = dirService.getDirByUserId(useId);
        }
        // 获取我的目录树
        else if ("1".equals(treeType)) {
            result = dirService.getIGroupTree(useId);
        }
        HashMap xmlMap = new HashMap<String,String>();
        int totalPackage = 0;
        int packageSize = 10;
        int pktNum = 0;

        // 解析xml
        try {
            StringReader read = new StringReader(result);
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
            Element rootElement = doc.getRootElement();
            // 象每一个节点中添加它的父节点
            addparid(rootElement, "0");

            Element root = doc.getRootElement();
            // 将每一个节点取出来放到map中
            analyzeXml(root, xmlMap);
        } catch (Exception e) {
            // TODO: handle exception
        }
        // 分包发送
        {
            int countor = 0;
            StringBuffer bodySb = new StringBuffer();
            Set ks = xmlMap.keySet();
            Iterator it = ks.iterator();
            if (ks.size() != 0) {
                totalPackage = ks.size() / packageSize + 1;
            }
            if (totalPackage == 0) {
                sf.append("<response command=\"GetCatolog\">");
                sf.append("<result code='" + resultCode + "'>" + "sucess"
                        + "</result>" + "<parameters>" + "<totalPkt>" + 0
                        + "</totalPkt>" + "<pktNum>" + 0 + "</pktNum>"
                        + "<root>" + bodySb + "</root>" + "</parameters>");
                sf.append("</response>");
            } else {
                while (it.hasNext()) {
                    String tempStr = (String) xmlMap.get((String) it.next());
                    bodySb.append(tempStr);
                    countor++;
                    if (pktNum != totalPackage - 1 && countor == packageSize) {
                        pktNum++;
                        // 发送
                        sf.append("<response command=\"GetCatolog\">");
                        sf.append("<result code='" + resultCode + "'>"
                                + "sucess" + "</result>" + "<parameters>"
                                + "<totalPkt>" + totalPackage + "</totalPkt>"
                                + "<pktNum>" + (pktNum - 1) + "</pktNum>"
                                + "<root>" + bodySb + "</root>"
                                + "</parameters>");
                        sf.append("</response>");
                        // 发送请求方
                        {
                            SocketHelper.sendResponse(rm.getCtx(), rm
                                    .getBm(), sf.toString());
                        }
                        bodySb = new StringBuffer();
                        countor = 0;
                        sf = new StringBuffer();
                    }
                }
                if (pktNum == totalPackage - 1) {
                    // 发送
                    sf.append("<response command=\"GetCatolog\">");
                    sf.append("<result code='" + resultCode + "'>" + "sucess"
                            + "</result>" + "<parameters>" + "<totalPkt>"
                            + totalPackage + "</totalPkt>" + "<pktNum>"
                            + (totalPackage - 1) + "</pktNum>" + "<root>"
                            + bodySb + "</root>" + "</parameters>");
                    sf.append("</response>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(),
                                sf.toString());
                    }
                    bodySb = new StringBuffer();
                    countor = 0;
                    sf = new StringBuffer();
                }
            }
        }

    }

    @Override
    public void getCatologRes(RequestModel rm, String useId, String catologId,
                              String treeType) {
        // TODO Auto-generated method stub

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        String result = new String();
        if ("0".equals(treeType)) {
            result = videoService.getVideoByUidDirId(useId, catologId, "1");
        }
        if ("1".equals(treeType)) {
            //自定义目录暂时没有用到 暂时没有实现
            result = videoService.getVideoByIGoupId(useId, catologId);
        }

        int totalPkt = 0;
        int pktNum = 0;
        // 构造返回命令
        if (result != null && result.trim().length() != 0) {
            result = result.replace("<Node", "<node");
            result = result.replace("</Node", "</node");
            result = result.replace("'", "\"");
            totalPkt = 1;
            sf.append("<response command=\"GetCatologRes\">");
            sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                    + "</result>" + "<parameters>" + "<catologId>" + catologId
                    + "</catologId>" + "<totalPkt>" + totalPkt + "</totalPkt>"
                    + "<pktNum>" + pktNum + "</pktNum>" + "<camDetailGroup>"
                    + result + "</camDetailGroup>" + "</parameters>");
            sf.append("</response>");
        } else {
            totalPkt = 0;
            sf.append("<response command=\"GetCatologRes\">");
            sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                    + "</result>" + "<parameters>" + "<catologId>" + catologId
                    + "</catologId>" + "<totalPkt>" + 0 + "</totalPkt>"
                    + "<pktNum>" + 0 + "</pktNum>" + "<camDetailGroup>"
                    + result + "</camDetailGroup>" + "</parameters>");
            sf.append("</response>");
        }

        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }

    }

    @Override
    public void getTourGroup(RequestModel rm, String useId) {
        // TODO Auto-generated method stub
        String resultCode = "0";
        String result = "success";
        List<VItour> list = itourService.getTour(useId);
        StringBuffer sf = new StringBuffer();

        // totalPackage
        int totalPackage = 0;
        int packageSize = 10;
        int packageNum = 0;

        if (list.size() != 0) {
            totalPackage = list.size() / packageSize;
            if (list.size() % packageSize > 0) {
                totalPackage = list.size() / packageSize + 1;
            }
        } else {
            totalPackage = 0;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令

            sf.append("<response command=\"GetTourGroup\">");
            sf.append("<result code=\"" + resultCode + "\">" + result
                    + "</result>");
            sf.append("<parameters>");
            sf.append("<totalPkt>" + 0 + "</totalPkt>");
            sf.append("<pktNum>" + 0 + "</pktNum>");
            sf.append("<tourGroup");
            sf.append("</tourGroup>");
            sf.append("</parameters>");
            sf.append("</response>");
            // 发送请求方
            {
                SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                        .toString());
            }
        } else {
            int countor = 0;
            int k = 0;
            StringBuffer bodysf = new StringBuffer();
            for (int i = k; i < list.size(); i++) {
                countor++;
                k++;
                VItour temp = list.get(i);

                bodysf.append("<tour>");
                bodysf.append("<tourId>" + temp.getCode() + "</tourId>");
                bodysf.append("<name>" + temp.getName() + "</name>");
                bodysf.append("<screenNum>" + temp.getScreen()
                        + "</screenNum>");
                bodysf.append("<desc>" + temp.getDescs() + "</desc>");
                bodysf
                        .append("<interval>" + temp.getInterval()
                                + "</interval>");
                bodysf.append("</tour>");

                if (k == list.size()) {
                    // 构造返回命令
                    sf.append("<response command=\"GetTourGroup\">");
                    sf.append("<result code=\"" + resultCode + "\">" + result
                            + "</result>");
                    sf.append("<parameters>");

                    sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                    sf.append("<pktNum>" + packageNum + "</pktNum>");
                    sf.append("<tourGroup>");
                    sf.append(bodysf);
                    sf.append("</tourGroup>");
                    sf.append("</parameters>");
                    sf.append("</response>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(),
                                sf.toString());
                        packageNum++;
                        sf = new StringBuffer();
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                } else if (countor == packageSize) {
                    // 构造返回命令
                    sf.append("<response command=\"GetTourGroup\">");
                    sf.append("<result code=\"" + resultCode + "\">" + result
                            + "</result>");
                    sf.append("<parameters>");

                    sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                    sf.append("<pktNum>" + packageNum + "</pktNum>");
                    sf.append("<tourGroup>");
                    sf.append(bodysf);
                    sf.append("</tourGroup>");
                    sf.append("</parameters>");
                    sf.append("</response>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(),
                                sf.toString());
                        packageNum++;
                        sf = new StringBuffer();
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }

            }
        }

    }

    @Override
    public void getTourGroupRes(RequestModel rm, String useId, String tourId) {
        // TODO Auto-generated method stub
        String resultCode = "0";
        StringBuffer sf = new StringBuffer();

        String result = videoService.getVideoByTourId(useId, tourId);
        result = result.replace("<Node", "<node");
        result = result.replace("</Node", "</node");
        result = result.replace("'", "\"");

        // 构造返回命令

        sf.append("<response command=\"GetTourGroupRes\">");
        sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                + "</result>" + "<parameters>" + "<tourId>" + tourId
                + "</tourId>" + "<camDetailGroup>" + result
                + "</camDetailGroup>" + "</parameters>");
        sf.append("</response>");
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }

    }

    @Override
    public void queryCameraPtzPreset(RequestModel rm, String useId, String resId) {
        // TODO Auto-generated method stub

        String resultCode = "0";
        String result = "success";
        List<VVideoPreset> list = videoPresetService.getVideoPreset(useId, resId);

        // 构造返回命令
        StringBuffer sf = new StringBuffer();
        sf.append("<response command=\"GetPtzPreset\">");
        sf
                .append("<result code=\"" + resultCode + "\">" + result
                        + "</result>");
        sf.append("<parameters>");
        sf.append("<camPresetGroup>" + "<resId>" + resId + "</resId>");

        for (int j = 0; j < list.size(); j++) {
            // 组包
            {
                VVideoPreset temp = list.get(j);
                sf.append("<preset>");
                sf.append("<presetId>" + temp.getId() + "</presetId>");
                sf.append("<name>" + temp.getName() + "</name>");
                sf.append("<index>" + temp.getIndexNum() + "</index>");
                sf.append("<keepWatch>" + temp.getKeepWatchFlag()
                        + "</keepWatch>");
                sf.append("</preset>");

            }
        }
        sf.append("</camPresetGroup>");
        sf.append("</parameters>");
        sf.append("</response>");
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }

    }

    @Override
    public void updateMyCatolog(RequestModel rm, String optType, String useId,
                                String catologId, String catologName, String parentCatologId,
                                String desc, String treeType) {
        /*// TODO Auto-generated method stub
        ServiceEbi ebi = new ServiceEbo();
        StringBuffer sf = new StringBuffer();
        // SocketAddress remoteAddress= rm.getSession().getRemoteAddress();

        String resultCode = "0";
        // 添加
        if ("1".equals(optType)) {
            String result = ebi.addIDirectory(useId, catologName, desc,
                    parentCatologId);
            if (result == "0") {
                resultCode = "1";
                // 构造返回命令

                sf.append("<response command=\"ChangeCatolog\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<catologId>" + ""
                        + "</catologId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "0";
                sf.append("<response command=\"ChangeCatolog\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<catologId>" + result
                        + "</catologId>" + "</parameters>");
                sf.append("</response>");
            }

        }
        // 删除
        else if ("2".equals(optType)) {
            Boolean flag = ebi.deletIDirectory(useId, catologId);
            if (!flag) {
                resultCode = "1";
                // 构造返回命令

                sf.append("<response command=\"ChangeCatolog\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<catologId>" + ""
                        + "</catologId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "0";
                sf.append("<response command=\"ChangeCatolog\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<catologId>"
                        + catologId + "</catologId>" + "</parameters>");
                sf.append("</response>");
            }
        }
        // 更新
        else if ("3".equals(optType)) {
            Boolean flag = ebi.updateIDirectory(useId, catologName, desc,
                    catologId, parentCatologId);
            if (!flag) {
                resultCode = "1";
                // 构造返回命令

                sf.append("<response command=\"ChangeCatolog\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<catologId>" + ""
                        + "</catologId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "0";
                sf.append("<response command=\"ChangeCatolog\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<catologId>"
                        + catologId + "</catologId>" + "</parameters>");
                sf.append("</response>");
            }
        }
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getSession(), rm.getBm(), sf
                    .toString());
        }*/
    }

    @Override
    public void updateMyCatologRes(RequestModel rm, String optType,
                                   String useId, ArrayList<Map> list, String treeType) {
        // TODO Auto-generated method stub
        ServiceEbi ebi = new ServiceEbo();
        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        // 添加
        if ("1".equals(optType)) {
            ArrayList<ChannelModel> resultList = ebi.configLargeIGoupVideo(
                    useId, list);
            if (resultList.size() == list.size()) {
                resultCode = "0";
                sf.append("<response command=\"ChangeCatologRes\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                Number str = list.size() - resultList.size();
                sf.append("<response command=\"ChangeCatologRes\">");
                sf.append("<result code=\"" + resultCode + "\">" + str
                        + "个资源添加失败" + "</result>" + "<parameters>"
                        + "</parameters>");
                sf.append("</response>");
            }

        }
        // 删除
        else if ("2".equals(optType)) {
            for (int i = 0; i < list.size(); i++) {
                ASObject as = (ASObject) list.get(i);
                String resId = (String) as.get("code");
                String IGroupId = (String) as.get("IGroupId");
                Boolean flag = ebi.deleteFavoriteVideo(useId, resId, IGroupId);
                if (flag) {
                    resultCode = "0";
                    sf.append("<response command=\"ChangeCatologRes\">");
                    sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                            + "</result>" + "<parameters>" + "</parameters>");
                    sf.append("</response>");
                } else {
                    resultCode = "1";
                    sf.append("<response command=\"ChangeCatologRes\">");
                    sf.append("<result code=\"" + resultCode + "\">"
                            + "failure" + "</result>" + "<parameters>"
                            + "</parameters>");
                    sf.append("</response>");
                }
            }
        }
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getSession(), rm.getBm(), sf
                    .toString());
        }
    }

    @Override
    public void updateTourGroup(RequestModel rm, String optType, String useId,
                                String tourId, String name, String descs, String interval,
                                String screen, ArrayList<Map> list) {
        /*// TODO Auto-generated method stub
        ServiceEbi ebi = new ServiceEbo();
        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        // 添加
        if ("1".equals(optType)) {
            String result = ebi.addTour(useId, name, descs, interval, screen,
                    list);
            if (result == "0" || result == null) {
                resultCode = "1";
                sf.append("<response command=\"ChangeTourGroup\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>");
                sf.append("<tourId>" + result + "</tourId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "0";
                sf.append("<response command=\"ChangeTourGroup\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>");
                sf.append("<tourId>" + result + "</tourId>" + "</parameters>");
                sf.append("</response>");
            }

        }
        // 删除
        else if ("2".equals(optType)) {
            Boolean flag = ebi.deleteTour(useId, tourId);
            if (!flag) {
                resultCode = "1";
                sf.append("<response command=\"ChangeTourGroup\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>");
                sf.append("<tourId>" + tourId + "</tourId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "0";
                sf.append("<response command=\"ChangeTourGroup\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>");
                sf.append("<tourId>" + tourId + "</tourId>" + "</parameters>");
                sf.append("</response>");
            }
        }
        // 修改
        else if ("3".equals(optType)) {
            Boolean flag = ebi.updateTour(useId, tourId, name, descs, interval,
                    screen, list);
            if (!flag) {
                resultCode = "1";
                sf.append("<response command=\"ChangeTourGroup\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>");
                sf.append("<tourId>" + tourId + "</tourId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "0";
                sf.append("<response command=\"ChangeTourGroup\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>");
                sf.append("<tourId>" + tourId + "</tourId>" + "</parameters>");
                sf.append("</response>");
            }
        }

        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getSession(), rm.getBm(), sf
                    .toString());
        }
*/
    }

    public static void addparid(Element rootElement, String pid) {
        List<Element> children = rootElement.getChildren();
        for (Element element : children) {
            element.setAttribute("parentId", pid);
            addparid(element, element.getAttributeValue("id"));
        }

    }

    public static void analyzeXml(Element rootElement,
                                  HashMap<String,String> map) {
        List<Element> children = rootElement.getChildren();
        for (Element element : children) {
            Element e = (Element) i.next();
            String id = e.getAttributeValue("id");
            String name = e.getAttributeValue("name");
            String type = e.getAttributeValue("type");
            String parentId = e.getAttributeValue("parentId");
            String getVideoFlag = e.getAttributeValue("getVideoFlag");
            String eStr = "<node getVideoFlag=\"" + getVideoFlag + "\" id=\""
                    + id + "\" name=\"" + name + "\" parentId=\"" + parentId
                    + "\" type=\"" + type + "\"/>";
            map.put(id, eStr);
            analyzeXml(e, map);
        }


    }

    @Override
    public void changeLayout(RequestModel rm, String userId, String layoutId,
                             String layoutName, String optType) {
        StringBuffer sf = new StringBuffer();
        String resultCode = "0";

        // 增加
        if ("1".equals(optType)) {
            String result = "";
            result = ilayoutService.addILayout(userId, userId, "", layoutName, "");
            if (result != "0") {
                resultCode = "0";
                sf.append("<response command=\"ChangeLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<layoutId>" + result
                        + "</layoutId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                sf.append("<response command=\"ChangeLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<layoutId>" + result
                        + "</layoutId>" + "</parameters>");
                sf.append("</response>");
            }
        }
        // 删除
        else if ("2".equals(optType)) {
            boolean resultFlag = false;
            resultFlag = ilayoutService.delILayout(userId, layoutId);
            if (resultFlag) {
                resultCode = "0";
                sf.append("<response command=\"ChangeLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<layoutId>"
                        + layoutId + "</layoutId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                sf.append("<response command=\"ChangeLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<layoutId>"
                        + layoutId + "</layoutId>" + "</parameters>");
                sf.append("</response>");
            }
        }
        // 修改
        else if ("3".equals(optType)) {
            boolean resultFlag = false;
            resultFlag = ilayoutService.updateILayout(userId, userId, layoutId, "",
                    layoutName, "");
            if (resultFlag) {
                sf.append("<response command=\"ChangeLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<layoutId>"
                        + layoutId + "</layoutId>" + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                sf.append("<response command=\"ChangeLayout\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<layoutId>"
                        + layoutId + "</layoutId>" + "</parameters>");
                sf.append("</response>");
            }
        }

        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf.toString());
    }

    @Override
    /**
     * @author zml
     * @说明 : 设置我的布局
     * @结果：0成功 1失败
     */
    public void setLayout(RequestModel rm, String userId, String layoutId,
                          String screenNum, ArrayList<Map> iLayoutList) {

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        boolean flag = ilayoutService.addILayoutDetail(userId, screenNum, iLayoutList);
        if (flag) {
            sf.append("<response command=\"SetLayout\">");
            sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                    + "</result>" + "<parameters>" + "</parameters>");
            sf.append("</response>");
        } else {
            resultCode = "1";
            sf.append("<response command=\"SetLayout\">");
            sf.append("<result code=\"" + resultCode + "\">" + "false"
                    + "</result>" + "<parameters>" + "</parameters>");
            sf.append("</response>");
        }
        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf.toString());
    }

    @Override
    public void getAlarmInfo(RequestModel rm, String userId, String alarmType,
                             String alarmState, String pagesize, String pageindex) {

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        int totalPackage = 0;
        int totalNum = 0;
        PageInfo<VAlarmVo> list1 = vAlarmSetService.getAlarmInfo(userId, "",
                alarmType, alarmState, "", pagesize, pageindex);
        List<VAlarmVo> list = list1.getList();

        if (list.size() > 0) {
            totalPackage = list1.getPages();
            totalNum = totalPackage*Integer.parseInt(pagesize);
        } else {
            sf.append("<response command=\"GetAlarmInfo\">");
            sf.append("<result code=\"" + resultCode + "\">" + "success"
                    + "</result>");
            sf.append("<parameters>");
            sf.append("<pageTotalNum>" + 0 + "</pageTotalNum>");
            sf.append("<totalNum>" + 0 + "</totalNum>");
            sf.append("<pageSize>" + pagesize + "</pageSize>");
            sf.append("<pageIndex>" + pageindex + "</pageIndex>");
            sf.append("</parameters>");
            sf.append("</response>");
        }
        if (totalPackage > 0) {
            int currentPageSize = list.size();
            sf.append("<response command=\"GetAlarmInfo\">");
            sf.append("<result code=\"" + resultCode + "\">" + "success"
                    + "</result>");
            sf.append("<parameters>");
            sf.append("<pageTotalNum>" + totalPackage + "</pageTotalNum>");
            sf.append("<totalNum>" + totalNum + "</totalNum>");
            sf.append("<pageSize>" + currentPageSize + "</pageSize>");
            sf.append("<pageIndex>" + pageindex + "</pageIndex>");
            sf.append("<alarmInfoGroup>");
            for (int i = 0; i < list.size(); i++) {
                sf.append("<alarmInfo>");
                sf
                        .append("<alarmId>" + list.get(i).getAlarmid()
                                + "</alarmId>");
                sf.append("<sessionId>" + list.get(i).getSessionid()
                        + "</sessionId>");
                sf.append("<name>" + list.get(i).getName() + "</name>");
                if (list.get(i).getDesc() == null) {
                    sf.append("<description>" + "</description>");
                } else {
                    sf.append("<description>" + list.get(i).getDesc()
                            + "</description>");
                }
                if (list.get(i).getAlarmType() == null) {
                    sf.append("<alarmType>" + "</alarmType>");
                } else {
                    sf.append("<alarmType>" + list.get(i).getAlarmType()
                            + "</alarmType>");
                }
                if (list.get(i).getAlarmlevel() == null) {
                    sf.append("<alarmLevel>" + "</alarmLevel>");
                } else {
                    sf.append("<alarmLevel>" + list.get(i).getAlarmlevel()
                            + "</alarmLevel>");
                }
                if (list.get(i).getBegintime() == null) {
                    sf.append("<beginTime>" + "</beginTime>");
                } else {
                    sf.append("<beginTime>" + list.get(i).getBegintime()
                            + "</beginTime>");
                }
                if (list.get(i).getEndtime() == null) {
                    sf.append("<endTime>" + "</endTime>");
                } else {
                    sf.append("<endTime>" + list.get(i).getEndtime()
                            + "</endTime>");
                }
                if (list.get(i).getAlarmstate() == null) {
                    sf.append("<alarmState>" + "</alarmState>");
                } else {
                    sf.append("<alarmState>" + list.get(i).getAlarmstate()
                            + "</alarmState>");
                }
                if (list.get(i).getAffirmman() == null) {
                    sf.append("<affirmMan>" + "</affirmMan>");
                } else {
                    sf.append("<affirmMan>" + list.get(i).getAffirmman()
                            + "</affirmMan>");
                }
                if (list.get(i).getAffirm() == null) {
                    sf.append("<affirmTime>" + "</affirmTime>");
                } else {
                    sf.append("<affirmTime>" + list.get(i).getAffirm()
                            + "</affirmTime>");
                }

                if (list.get(i).getCleanman() == null) {
                    sf.append("<cleanMan>" + "</cleanMan>");
                } else {
                    sf.append("<cleanMan>" + list.get(i).getCleanman()
                            + "</cleanMan>");
                }
                if (list.get(i).getCleantime() == null) {
                    sf.append("<cleanTime>" + "</cleanTime>");
                } else {
                    sf.append("<cleanTime>" + list.get(i).getCleantime()
                            + "</cleanTime>");
                }

                sf.append("</alarmInfo>");
            }

            sf.append("</alarmInfoGroup>");
            sf.append("</parameters>");
            sf.append("</response>");
        }
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }

    }

    @Override
    public void getAlarmEvidence(RequestModel rm, String userId, String alarmId) {

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        List<VAlarmEvidenceVo> list = vAlarmSetService.getAlarmEvidence(userId,
                alarmId);
        if (list.size() > 0) {
            sf.append("<response command=\"GetAlarmEvidence\">");
            sf.append("<result code=\"" + resultCode + "\">" + "success"
                    + "</result>");
            sf.append("<parameters>");
            sf.append("<alarmId>" + list.get(0).getAlarmId() + "</alarmId>");
            sf.append("<evidenceType>" + list.get(0).getEvidenceType()
                    + "</evidenceType>");
            sf.append("<evidenceGroup>");
            for (int i = 0; i < list.size(); i++) {
                sf.append("<evidenceUrl>" + list.get(i).getEvidence()
                        + "</evidenceUrl>");
            }
            sf.append("</evidenceGroup>");
            sf.append("</parameters>");
            sf.append("</response>");
        } else {
            sf.append("<response command=\"GetAlarmEvidence\">");
            sf.append("<result code=\"" + resultCode + "\">" + "success"
                    + "</result>");
            sf.append("<parameters>");
            sf.append("<alarmId>" + alarmId + "</alarmId>");
            sf.append("<evidenceType>" + 0 + "</evidenceType>");
            sf.append("<evidenceGroup>");
            sf.append("</evidenceGroup>");
            sf.append("</parameters>");
            sf.append("</response>");
        }
        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }
    }

    @Override
    public void changePtzPreset(RequestModel rm, String optType, String useId,
                                String resId, String presetName, String presetIndex,
                                String keepWatch, String presetId) {
        // TODO Auto-generated method stub
        StringBuffer sf = new StringBuffer();
        String resultCode = "0";

        // 增加
        if ("1".equals(optType)) {
            String result = "";
            result = vVideoPresetService.addVideoPreset(useId, resId, presetName, keepWatch,
                    presetIndex);
            if (result != "0") {
                resultCode = "0";
                sf.append("<response command=\"ChangePtzPreset\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<resId>" + resId
                        + "</resId>" + "<preset>" + "<presetId>" + result
                        + "</presetId>" + "<name>" + presetName + "</name>"
                        + "<index>" + presetIndex + "</index>" + "<keepWatch>"
                        + keepWatch + "</keepWatch>" + "</preset>"
                        + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                sf.append("<response command=\"ChangePtzPreset\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<resId>" + resId
                        + "</resId>" + "<preset>" + "<presetId>" + result
                        + "</presetId>" + "<name>" + presetName + "</name>"
                        + "<index>" + presetIndex + "</index>" + "<keepWatch>"
                        + keepWatch + "</keepWatch>" + "</preset>"
                        + "</parameters>");
                sf.append("</response>");
            }
        }
        // 删除
        else if ("2".equals(optType)) {
            boolean resultFlag = false;
            resultFlag = vVideoPresetService.delVideoPreset(useId, presetId);
            if (resultFlag) {
                resultCode = "0";
                sf.append("<response command=\"ChangePtzPreset\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<resId>" + resId
                        + "</resId>" + "<preset>" + "<presetId>" + ""
                        + "</presetId>" + "<name>" + presetName + "</name>"
                        + "<index>" + presetIndex + "</index>" + "<keepWatch>"
                        + keepWatch + "</keepWatch>" + "</preset>"
                        + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                sf.append("<response command=\"ChangePtzPreset\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<resId>" + resId
                        + "</resId>" + "<preset>" + "<presetId>" + ""
                        + "</presetId>" + "<name>" + presetName + "</name>"
                        + "<index>" + presetIndex + "</index>" + "<keepWatch>"
                        + keepWatch + "</keepWatch>" + "</preset>"
                        + "</parameters>");
                sf.append("</response>");
            }
        }
        // 修改
        else if ("3".equals(optType)) {
            boolean resultFlag = false;
            resultFlag = vVideoPresetService.updateVideoPreset(useId, presetId, presetName,
                    keepWatch);
            if (resultFlag) {
                resultCode = "0";
                sf.append("<response command=\"ChangePtzPreset\">");
                sf.append("<result code=\"" + resultCode + "\">" + "sucess"
                        + "</result>" + "<parameters>" + "<resId>" + resId
                        + "</resId>" + "<preset>" + "<presetId>" + ""
                        + "</presetId>" + "<name>" + presetName + "</name>"
                        + "<index>" + presetIndex + "</index>" + "<keepWatch>"
                        + keepWatch + "</keepWatch>" + "</preset>"
                        + "</parameters>");
                sf.append("</response>");
            } else {
                resultCode = "1";
                sf.append("<response command=\"ChangePtzPreset\">");
                sf.append("<result code=\"" + resultCode + "\">" + "failure"
                        + "</result>" + "<parameters>" + "<resId>" + resId
                        + "</resId>" + "<preset>" + "<presetId>" + ""
                        + "</presetId>" + "<name>" + presetName + "</name>"
                        + "<index>" + presetIndex + "</index>" + "<keepWatch>"
                        + keepWatch + "</keepWatch>" + "</preset>"
                        + "</parameters>");
                sf.append("</response>");
            }
        }

        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf.toString());

    }

    @Override
    public void reportCameraChanged(ChannelHandlerContext session, ArrayList<Object> list,
                                    String optType) {
        // TODO Auto-generated method stub
        BaseMsg bm = SocketHelper.getBm();
        int packageSize = 30;
        if (list.size() == 0) {
            // 构造返回命令
            StringBuffer sf = new StringBuffer();
            sf.append("<request command=\"ReportCameraChanged\">");
            sf.append("<parameters>");
            sf.append("<changeType>" + optType + "</changeType>");
            sf.append("<camStateGroup>");
            sf.append("<camState>");
            sf.append("<resId>" + "</resId>");
            sf.append("<state>" + "</state>");
            sf.append("</camState>");
            sf.append("</camStateGroup>");
            sf.append("</parameters>");
            // 发送请求方
            {
                SocketHelper.sendResponse(session, bm, sf.toString());
            }

        } else {
            int countor = 0;
            int k = 0;
            // 构造返回命令
            StringBuffer sf = new StringBuffer();
            StringBuffer bodysf = new StringBuffer();
            for (int i = k; i < list.size(); i++) {
                countor++;
                k++;
                VVideo temp = (VVideo) list.get(i);
                bodysf.append("<camState>");
                bodysf.append("<resId>" + temp.getCode() + "</resId>");
                bodysf.append("<state>" + "</state>");
                bodysf.append("</camState>");
                if (k == list.size()) {
                    sf.append("<request command=\"ReportCameraChanged\">");
                    sf.append("<parameters>");
                    sf.append("<changeType>" + optType + "</changeType>");
                    sf.append("<camStateGroup>");
                    sf.append(bodysf);
                    sf.append("</camStateGroup>");
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                } else if (countor == packageSize) {
                    sf.append("<request command=\"ReportCameraChanged\">");
                    sf.append("<parameters>");
                    sf.append("<changeType>" + optType + "</changeType>");
                    sf.append("<camStateGroup>");
                    sf.append(bodysf);
                    sf.append("</camStateGroup>");
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }
            }
        }

    }

    @Override
    public void getAlarmRes(RequestModel rm, String userId) {
        // TODO Auto-generated method stub

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        String result = "success";
       List<VAlarmSetVo> list = vAlarmSetService.getAlarmResByUserCode(userId);
        // totalPackage
        int totalPackage = 0;
        int packageSize = 10;
        int packageNum = 0;

        if (list.size() != 0) {
            totalPackage = list.size() / packageSize;
            if (list.size() % packageSize > 0) {
                totalPackage = list.size() / packageSize + 1;
            }
        } else {
            totalPackage = 0;
        }
        if (totalPackage == 0) {
            // 构造返回命令

            sf.append("<response command=\"GetAlarmRes\">");
            sf.append("<result code=\"0\">");
            sf.append(result);
            sf.append("</result>");
            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("</parameters>");
            sf.append("</response>");
            // 发送请求方
            {
                SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                        .toString());
            }

        } else {
            int countor = 0;
            int k = 0;
            // 构造返回命令
            StringBuffer bodysf = new StringBuffer();
            for (int i = k; i < list.size(); i++) {
                countor++;
                k++;
                VAlarmSetVo temp = (VAlarmSetVo) list.get(i);
                StringBuffer tempsf = new StringBuffer();
                tempsf = getAlarmResBody(temp);
                bodysf.append(tempsf);
                if (k == list.size()) {
                    sf.append("<response command=\"GetAlarmRes\">");
                    sf.append("<result code=\"0\">");
                    sf.append(result);
                    sf.append("</result>");
                    sf.append("<parameters>");
                    sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                    sf.append("<pktNum>" + packageNum + "</pktNum>");
                    sf.append(bodysf);
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(),
                                sf.toString());
                        packageNum++;
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                } else if (countor == packageSize) {
                    sf.append("<response command=\"GetAlarmRes\">");
                    sf.append("<result code=\"0\">");
                    sf.append(result);
                    sf.append("</result>");
                    sf.append("<parameters>");
                    sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                    sf.append("<pktNum>" + 0 + "</pktNum>");
                    sf.append(bodysf);
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(rm.getCtx(), rm.getBm(),
                                sf.toString());
                        packageNum++;
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }
            }
        }

    }

    @Override
    public void reportCSAlarmChange(ChannelHandlerContext session, ArrayList<Object> list,
                                    String optType) {
        // TODO Auto-generated method stub
        BaseMsg bm = SocketHelper.getBm();
        int totalPackage = 0;
        int packageSize = 10;
        int packageNum = 0;
        StringBuffer sf = new StringBuffer();
        if (list.size() != 0) {
            totalPackage = list.size() / packageSize;
            if (list.size() % packageSize > 0) {
                totalPackage = list.size() / packageSize + 1;
            }
        } else {
            totalPackage = 0;
        }
        // 添加
        // if("1".equals(optType)){
        //
        // }
        // 删除
        // else if("2".equals(optType)){
        if (totalPackage == 0) {
            // 构造返回命令

            sf.append("<request command=\"ChangeAlarmResource\">");
            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("</parameters>");
            sf.append("</response>");
            // 发送请求方
            {
                SocketHelper.sendResponse(session, bm, sf.toString());
            }

        } else {
            int countor = 0;
            int k = 0;
            // 构造返回命令
            StringBuffer bodysf = new StringBuffer();
            for (int i = k; i < list.size(); i++) {
                countor++;
                k++;
                VAlarmSetVo temp = (VAlarmSetVo) list.get(i);
                StringBuffer tempsf = new StringBuffer();
                tempsf = getAlarmBody(temp, optType);
                bodysf.append(tempsf);
                if (k == list.size()) {
                    sf.append("<request command=\"ChangeAlarmResource\">");
                    sf.append("<parameters>");
                    sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                    sf.append("<pktNum>" + packageNum + "</pktNum>");
                    sf.append("<AlarmResGroup>");
                    sf.append(bodysf);
                    sf.append("</AlarmResGroup>");
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        packageNum++;
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                } else if (countor == packageSize) {
                    sf.append("<request command=\"ChangeAlarmResource\">");
                    sf.append("<parameters>");
                    sf.append("<totalPkt>" + totalPackage + "</totalPkt>");
                    sf.append("<pktNum>" + packageNum + "</pktNum>");
                    sf.append("<AlarmResGroup>");
                    sf.append(bodysf);
                    sf.append("</AlarmResGroup>");
                    sf.append("</parameters>");
                    // 发送请求方
                    {
                        SocketHelper.sendResponse(session, bm, sf.toString());
                        packageNum++;
                        bodysf = new StringBuffer();
                        sf = new StringBuffer();
                        countor = 0;
                    }
                }
            }
        }
        // }
        // 修改
        // else if("3".equals(optType)){
        //
        // }

    }

    public StringBuffer getAlarmBody(VAlarmSetVo temp, String optType) {
        StringBuffer sf = new StringBuffer();
        sf.append("<URL>");

        sf.append("<opt>");
        sf.append(optType);
        sf.append("</opt>");

        sf.append("<id>");
        sf.append(temp.getAlarmResId());
        sf.append("</id>");

        sf.append("<deviceId>");
        sf.append(temp.getDeviceid());
        sf.append("</deviceId>");

        sf.append("<pagId>");
        sf.append(temp.getPagcode());
        sf.append("</pagId>");

        sf.append("<auId>");
        sf.append(temp.getAuCode());
        sf.append("</auId>");

        sf.append("<type>");
        sf.append(temp.getAlarmType());
        sf.append("</type>");

        sf.append("<name>");
        sf.append(temp.getChannelName());
        sf.append("</name>");

        sf.append("<channelNum>");
        sf.append(temp.getChannelNum());
        sf.append("</channelNum>");

        sf.append("<description>");
        sf.append(temp.getDescs());
        sf.append("</description>");

        sf.append("<level>");
        sf.append(temp.getAlarmLevel());
        sf.append("</level>");

        sf.append("<isLinkMap>");
        sf.append(temp.getIslinkmap());
        sf.append("</isLinkMap>");

        sf.append("<isArming>");
        sf.append(temp.getIsarming());
        sf.append("</isArming>");

        sf.append("<isLinkSoud>");
        sf.append(temp.getIslinksoud());
        sf.append("</isLinkSoud>");

        sf.append("<Ip>");
        sf.append(temp.getFdIp());
        sf.append("</Ip>");

        sf.append("<Port>");
        sf.append(temp.getFdPort());
        sf.append("</Port>");

        sf.append("<UserName>");
        sf.append(temp.getChannelName());
        sf.append("</UserName>");

        sf.append("<PassWord>");
        sf.append(temp.getPassword());
        sf.append("</PassWord>");

        sf.append("<latitude>");
        sf.append(temp.getLatitude());
        sf.append("</latitude>");

        sf.append("<longitude>");
        sf.append(temp.getLongitude());
        sf.append("</longitude>");

        // 布控信息
        {

           /* ArrayList armingList = temp.getArmingList();
            for (int ai = 0; ai < armingList.size(); ai++) {
                ArmingModel am = (ArmingModel) armingList.get(ai);
                sf.append("<Arming>");
                sf.append("<startTime>");
                sf.append(am.getStartTime());
                sf.append("</startTime>");
                sf.append("<endTime>");
                sf.append(am.getEndTime());
                sf.append("</endTime>");
                sf.append("</Arming>");
            }*/

        }
        // 告警联动信息
        {
/*

            ArrayList linkList = temp.getLinkList();
            for (int li = 0; li < linkList.size(); li++) {
                LinkModel lm = (LinkModel) linkList.get(li);
                sf.append("<Link>");
                sf.append("<resId>");
                sf.append(lm.getResId());
                sf.append("</resId>");
                sf.append("<isLinkPtz>");
                sf.append(lm.getIsLinkPtz());
                sf.append("</isLinkPtz>");
                sf.append("<ptzPresent>");
                sf.append(lm.getPtzPresent());
                sf.append("</ptzPresent>");
                sf.append("<isLinkRec>");
                sf.append(lm.getIsLinkRec());
                sf.append("</isLinkRec>");
                sf.append("<recFps>");
                sf.append(lm.getRecFps());
                sf.append("</recFps>");
                sf.append("<isLinkPrev>");
                sf.append(lm.getIsLinkPrev());
                sf.append("</isLinkPrev>");
                sf.append("<isLinkPic>");
                sf.append(lm.getIsLinkPic());
                sf.append("</isLinkPic>");
                sf.append("</Link>");
            }
*/

        }
        sf.append("</URL>");
        return sf;
    }

    public StringBuffer getAlarmResBody(VAlarmSetVo temp) {
        StringBuffer sf = new StringBuffer();

        sf.append("<AlarmRes>");
        sf.append("<id>");
        sf.append(temp.getAlarmResId());
        sf.append("</id>");

        sf.append("<deviceId>");
        sf.append(temp.getDeviceid());
        sf.append("</deviceId>");

        sf.append("<pagId>");
        sf.append(temp.getPagcode());
        sf.append("</pagId>");

        sf.append("<auId>");
        sf.append(temp.getAuCode());
        sf.append("</auId>");

        sf.append("<type>");
        sf.append(temp.getAlarmType());
        sf.append("</type>");

        sf.append("<name>");
        sf.append(temp.getChannelName());
        sf.append("</name>");

        sf.append("<channelNum>");
        sf.append(temp.getChannelNum());
        sf.append("</channelNum>");

        sf.append("<description>");
        sf.append(temp.getDescs());
        sf.append("</description>");

        sf.append("<level>");
        sf.append(temp.getAlarmLevel());
        sf.append("</level>");

      /*  sf.append("<isLinkMap>");
        sf.append(temp.getIsLinkMap());
        sf.append("</isLinkMap>");

        sf.append("<isArming>");
        sf.append(temp.getIsArming());
        sf.append("</isArming>");

        sf.append("<isLinkSoud>");
        sf.append(temp.getIsLinkSoud());
        sf.append("</isLinkSoud>");

        sf.append("<Ip>");
        sf.append(temp.getAlarmFDIp());
        sf.append("</Ip>");

        sf.append("<Port>");
        sf.append(temp.getAlarmFDPort());
        sf.append("</Port>");

        sf.append("<UserName>");
        sf.append(temp.getAlarmFDUserName());
        sf.append("</UserName>");

        sf.append("<PassWord>");
        sf.append(temp.getAlarmFDPassWord());
        sf.append("</PassWord>");

        sf.append("<latitude>");
        sf.append(temp.getLatitude());
        sf.append("</latitude>");

        sf.append("<longitude>");
        sf.append(temp.getLongitude());
        sf.append("</longitude>");

        // 布控信息
        {

            ArrayList armingList = temp.getArmingList();
            for (int ai = 0; ai < armingList.size(); ai++) {
                ArmingModel am = (ArmingModel) armingList.get(ai);
                sf.append("<Arming>");
                sf.append("<startTime>");
                sf.append(am.getStartTime());
                sf.append("</startTime>");
                sf.append("<endTime>");
                sf.append(am.getEndTime());
                sf.append("</endTime>");
                sf.append("</Arming>");
            }

        }
        // 告警联动信息
        {

            ArrayList linkList = temp.getLinkList();
            for (int li = 0; li < linkList.size(); li++) {
                LinkModel lm = (LinkModel) linkList.get(li);
                sf.append("<Link>");
                sf.append("<resId>");
                sf.append(lm.getResId());
                sf.append("</resId>");
                sf.append("<isLinkPtz>");
                sf.append(lm.getIsLinkPtz());
                sf.append("</isLinkPtz>");
                sf.append("<ptzPresent>");
                sf.append(lm.getPtzPresent());
                sf.append("</ptzPresent>");
                sf.append("<isLinkRec>");
                sf.append(lm.getIsLinkRec());
                sf.append("</isLinkRec>");
                sf.append("<recFps>");
                sf.append(lm.getRecFps());
                sf.append("</recFps>");
                sf.append("<isLinkPrev>");
                sf.append(lm.getIsLinkPrev());
                sf.append("</isLinkPrev>");
                sf.append("<isLinkPic>");
                sf.append(lm.getIsLinkPic());
                sf.append("</isLinkPic>");
                sf.append("</Link>");
            }

        }*/
        sf.append("</AlarmRes>");
        return sf;
    }

    /**
     * @author ghj
     * @说明：释放用户资源
     */
    public static void releaseUserResource(String userCode) {
        // 释放资源
        HashMapHelper.getLoginUserMap().remove(userCode);
        HashMapHelper.getUserOrderAlarmMap().remove(userCode);
        ChannelHandlerContext channelHandlerContext = HashMapHelper.getUserConnectionMap().get(userCode);
        HashMapHelper.getUserConnectionMap().remove(userCode);

    }

    public static String checkUserHeartBeat(String userCode) {
        String resultCode = "0";
        // 判断心跳是否超时
        SUser sUser = HashMapHelper.getLoginUserMap().get(userCode);
        if (sUser == null) {
            resultCode = "-1";

        } else {
            // 验证用户心跳是否有效
            Date tempDate = sUser.getLastHeartBeatTime();
            Date newTime = new Date();
            // 最后心跳时间 3个心跳周期
            if (newTime.getTime() - tempDate.getTime() < 3 * heartbeatTime * 1000) {

                sUser.setLastHeartBeatTime(new Date());
            } else {
                resultCode = "-2";

            }

        }

        return resultCode;
    }

    @Override
    public void reportCSAlarm4StrProtocol(ChannelHandlerContext session,
                                          ArrayList<Object> list) {

        // 构造返回命令
        StringBuffer sf = new StringBuffer();
        StringBuffer bodysf = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {

            AMQAlarmInforModel temp = (AMQAlarmInforModel) list.get(i);
            bodysf.append("id=" + temp.getId() + ",");
            bodysf.append("name=" + temp.getName() + ",");
            if (temp.getDescription() == null) {
                bodysf.append("description=,");
            } else {
                bodysf.append("description=" + temp.getDescription() + ",");
            }

            if (temp.getAlarmType() == null) {
                bodysf.append("alarmType=,");
            } else {
                bodysf.append("alarmType=" + temp.getAlarmType() + ",");
            }

            if (temp.getAlarmLevel() == null) {
                bodysf.append("alarmLevel=,");
            } else {
                bodysf.append("alarmLevel=" + temp.getAlarmLevel() + ",");
            }

            if (temp.getBeginTime() == null) {
                bodysf.append("beginTime=,");
            } else {
                bodysf.append("beginTime=" + temp.getBeginTime() + ",");
            }

            if (temp.getEndTime() == null) {
                bodysf.append("endTime=,");
            } else {
                bodysf.append("endTime=" + temp.getEndTime() + ",");
            }

            if (temp.getAlarmState() == null) {
                bodysf.append("alarmState=,");
            } else {
                bodysf.append("alarmState=" + temp.getAlarmState() + ",");
            }

            if (temp.getAffirmMan() == null) {
                bodysf.append("affirmMan=,");
            } else {
                bodysf.append("affirmMan=" + temp.getAffirmMan() + ",");
            }

            if (temp.getAffirmTime() == null) {
                bodysf.append("afirmTime=,");
            } else {
                bodysf.append("afirmTime=" + temp.getAffirmTime() + ",");
            }
            if (temp.getCleanMan() == null) {
                bodysf.append("cleanMan=,");
            } else {
                bodysf.append("cleanMan=" + temp.getCleanMan() + ",");
            }
            if (temp.getCleanTime() == null) {
                bodysf.append("cleanTime=,");
            } else {
                bodysf.append("cleanTime=" + temp.getCleanTime() + ",");
            }
            bodysf.append("isLinkPic=,");
            bodysf.append("^");
        }
        sf.append("ReportAlarm");
        sf.append(";");
        sf.append(bodysf);
        // 发送请求方
        if (session != null && session.isConnected()) {
            SocketHelper.sendUserClientMsg(session, sf.toString());
        }

        bodysf = null;
        sf = null;
    }

    @Override
    public void reportCSCameraState4StrProtocol(ChannelHandlerContext session,
                                                ArrayList<Object> list) {
        // 构造返回命令
        StringBuffer sf = new StringBuffer();
        StringBuffer bodysf = new StringBuffer();

        for (int i = 0; i < list.size(); i++) {
            AMQDeviceStateModel temp = (AMQDeviceStateModel) list.get(i);
            bodysf.append("videoCode=" + temp.getResId());
            bodysf.append(",");
            bodysf.append("state=" + temp.getStateS());
            bodysf.append("^");

        }
        // 头
        sf.append("ReportCameraState");
        sf.append(";");
        sf.append(bodysf);
        // 发送请求方
        {
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, sf.toString());
            }
            bodysf = new StringBuffer();
            sf = new StringBuffer();

        }
    }

    @Override
    public void reportSnapPicFinished(RequestModel rm,
                                      ArrayList<VEvidenceFile> list) {
        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        sf.append("<response command=\"ReportSnapPicFinished\">");
        sf.append("<result code=\"" + resultCode + "\">" + "success"
                + "</result>");
        sf.append("<parameters>");
        sf.append("</parameters>");
        sf.append("</response>");

        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }
        //入库

        reportService.addStoreFile(list);
    }

    @Override
    public void reportCameraRecordFinished(RequestModel rm,
                                           ArrayList<VEvidenceFile> list) {

        StringBuffer sf = new StringBuffer();
        String resultCode = "0";
        sf.append("<response command=\"ReportCameraRecordFinished\">");
        sf.append("<result code=\"" + resultCode + "\">" + "success"
                + "</result>");
        sf.append("<parameters>");
        sf.append("</parameters>");
        sf.append("</response>");

        // 发送请求方
        {
            SocketHelper.sendResponse(rm.getCtx(), rm.getBm(), sf
                    .toString());
        }

        //入库

        reportService.addStoreFile(list);
    }

    @Override
    public void reportDeviceState(RequestModel rm, ArrayList<AMQDeviceStateModel> list) {
        // TODO Auto-generated method stub
        //状态入库
        //推送到客户端
        AMQMessageModel amq = new AMQMessageModel();
        amq.setType("deviceState");
        amq.setList(list);
        // 发送订阅的信息
        protocolRequestProcesser.productMessage(amq);

        reportService.reportDeviceState(list);
    }

    @Override
    public void reportServiceState(RequestModel rm,
                                   ArrayList<AMQDeviceStateModel> list) {
        // TODO Auto-generated method stub
        //状态入库
        //推送到客户端
        AMQMessageModel amq = new AMQMessageModel();
        amq.setType("serviceState");
        amq.setList(list);
        // 发送订阅的信息
        protocolRequestProcesser.productMessage(amq);

        reportService.reportServiceState(list);

    }



    @Override
    public void reportNVRChannelInfo(String deviceCode, String resId, String userName, String password, String ip, String port) {
        reportService.reportNVRChannelInfo( deviceCode,  resId,  userName,  password,  ip,  port);
    }

    @Override
    public boolean reportGetFDOSDResult(ArrayList<CameraOSDInfoModel> cameraOSDList) {
        // TODO Auto-generated method stub
        // 摄像机OSD信息入库
        return reportService.reportGetCameraOSDResult(cameraOSDList);
    }

    @Override
    public boolean reportGetCameraOSDResult(ArrayList<CameraOSDInfoModel> cameraOSDList) {
        return  reportService.reportGetCameraOSDResult(cameraOSDList);
    }
}
