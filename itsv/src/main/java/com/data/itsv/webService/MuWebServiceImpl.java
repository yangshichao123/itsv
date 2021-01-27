package com.data.itsv.webService;


import com.data.itsv.model.*;
import com.data.itsv.service.UserService;
import com.data.itsv.util.DateUtils;
import com.data.itsv.util.Properties;
import org.apache.cxf.common.util.ReflectionInvokationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
@Service
@WebService(serviceName = "MOMPService", endpointInterface = "com.data.itsv.webService.MuWebService",targetNamespace = "http://webService.itsv.data.com")
public class MuWebServiceImpl implements MuWebService{

    @Autowired
    private UserService userService;

    @Override
    public LoginUserModel userLogin(String account, String password) {
        // TODO Auto-generated method stub

        SUser sUser = userService.cuUserLogin(account, password);
        LoginUserModel um = new LoginUserModel();
        UserModel userModel=new UserModel();
        userModel.setId(sUser.getId());
        userModel.setAccount(sUser.getAccount());
        userModel.setUserCode(sUser.getCode());
        userModel.setName(sUser.getName());
        userModel.setLoginTime(DateUtils.formatDate(sUser.getLoginTime(),"yyyy-MM-dd HH:mm:ss"));
        um.setUserInfo(userModel);
        ServerModel bcs=new ServerModel();
        try {
            InetAddress addr = InetAddress.getLocalHost();
            bcs.setServerIp(addr.getHostAddress());
            bcs.setServerPort(Properties.getNettyServerPost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        um.setBscServer(bcs);
        ServerModel cms=new ServerModel();
        cms.setServerPort(Properties.getNettyPost());
        cms.setServerIp(Properties.getNettyHost());
        um.setMompServer(cms);
        um.setCmsIP(Properties.getNettyHost());
        um.setCmsPort(Properties.getNettyPost());
        return um;
    }


    @Override
    public String getHcsj( String userName,
                           String userId,
                           String str) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        stringBuffer.append("<HcsjDatas>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("</HcsjDatas>");
        return stringBuffer.toString();

/*
        Element response = new Element("request");
        //根元素标签内的属性名与值
        response.setAttribute("command", "33333");
        //生成Doc文档
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();


        Element	result = new Element("uuuuuuuu");
        result.addContent("0000000000");
        response.addContent(result);


        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        xmlinfo = xmlinfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>");

        System.out.println(Doc.getParent());


        return xmlinfo;*/
    }
    @Override
    public String getWxjh(String userName, String userId, String str) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        stringBuffer.append("<HcsjDatas>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("</HcsjDatas>");
        return stringBuffer.toString();

/*
        Element response = new Element("request");
        //根元素标签内的属性名与值
        response.setAttribute("command", "33333");
        //生成Doc文档
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();


        Element	result = new Element("uuuuuuuu");
        result.addContent("0000000000");
        response.addContent(result);


        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        xmlinfo = xmlinfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>");

        System.out.println(Doc.getParent());


        return xmlinfo;*/
    }
   /* @Override
    public User getUser(String userName, String userId) {
        User user=new User();
       *//* user.setName(userName);
        user.setUserId(userId);*//*
        return user;
    }*/

    @Override
    public String getDirByUserId(String userId, String type) {
        return null;
    }

    @Override
    public String getVideoByUidDirId1(String userId, String dirId, String type) {
        return null;
    }

    @Override
    public boolean sendVideo2ScreenSystem(String userId, String videoCode, String screenNum) {
        return false;
    }

    @Override
    public boolean sendVideo2ScreenSystem1(String userId, String videoCode, String decoderServerIp, String decoderServerPort, String ioIndexNumber) {
        return false;
    }

    @Override
    public String getIDirectory(String userId) {
        return null;
    }

    @Override
    public String addILayout(String loginUserId, String userCode, String screenNum, String name, String descs) {
        return null;
    }

    @Override
    public ArrayList<ILayoutModel> getILayout(String loginUserId, String Id, String userCode, String pageSize, String pageIndex) {
        return null;
    }

    @Override
    public String getILayouts(String loginUserId, String Id, String userCode) {
        return null;
    }

    @Override
    public boolean delILayout(String loginUserId, String id) {
        return false;
    }

    @Override
    public boolean updateILayout(String loginUserId, String userCode, String id, String screenNum, String layoutName, String descs) {
        return false;
    }

    @Override
    public boolean deletIDirectory(String userId, String contenCode) {
        return false;
    }

    @Override
    public String addIDirectory(String userId, String name, String descs, String parentId) {
        return null;
    }

    @Override
    public boolean updateIDirectory(String userId, String name, String descs, String dirId, String parentId) {
        return false;
    }

    @Override
    public boolean configIGoupVideo(String userId, String iGroupId, String videoCode) {
        return false;
    }

    @Override
    public String getVideoByIGoupId(String userId, String dirId) {
        return null;
    }

    @Override
    public boolean deleteFavoriteVideo(String userId, String videoCode, String iGroupId) {
        return false;
    }

    @Override
    public ArrayList<ChannelModel> configLargeIGoupVideo(String userId, ArrayList<HashMap> list) {
        return null;
    }

    @Override
    public String addTour(String userId, String name, String descs, String interval, String screen, ArrayList<HashMap> list) {
        return null;
    }

    @Override
    public ArrayList<TourModel> getTour(String userId) {
        return null;
    }

    @Override
    public String getTours(String userId) {
        return null;
    }

    @Override
    public boolean updateTour(String userId, String tourId, String name, String descs, String interval, String screen, ArrayList<HashMap> list) {
        return false;
    }

    @Override
    public boolean deleteTour(String userId, String tourId) {
        return false;
    }

    @Override
    public String getIGroupTree(String userId) {
        return null;
    }

    @Override
    public ArrayList<ILayoutDetailModel> getILayoutDetail(String userId, String iLayoutId) {
        return null;
    }

    @Override
    public String getVideoByTourId(String userId, String tourId) {
        return null;
    }

    @Override
    public VideoModel getVideoCodeByExtCode(String extCode) {
        return null;
    }

    @Override
    public VideoModel getVideoCodeByCode(String code) {
        return null;
    }

    @Override
    public ArrayList<VideoModel> searchCamera(String condition) {
        return null;
    }

    @Override
    public ArrayList<VideoModel> searchCameraByPage(String condition, String pageSize, String pageIndex) {
        return null;
    }

    @Override
    public int getVideoPlanStoreType(String videoCode) {
        return 0;
    }

    @Override
    public ArrayList<VideoModel> getVideo(String pageSize, String pageIndex) {
        return null;
    }

    @Override
    public VersionModel checkClientLastVersion(String userId) {
        return null;
    }

    @Override
    public boolean checkFuncPri(String userId, String funcCode) {
        return false;
    }

    @Override
    public String getDefaulUserFace(String userId) {
        return null;
    }

    @Override
    public boolean addLog(String content, String flag) {
        return false;
    }

    @Override
    public boolean updateSysUserPassword(String optUserId, String userId, String password) {
        return false;
    }

    @Override
    public ArrayList<VideoModel> getVideoByUidDirId(String userId, String dirId) {
        return null;
    }

    @Override
    public ArrayList<VideoModel> getVideoModelByIGoupId(String userId, String dirId) {
        return null;
    }

    @Override
    public ArrayList<TourModel> getTour1(String userId, String id) {
        return null;
    }

    @Override
    public ArrayList<TourDetailModel> getTourDetailByTourId(String userId, String tourId) {
        return null;
    }

    @Override
    public boolean addUsers(String userId, String deptCode, String userCode, String jobCode, String name, String account, String password, String address, String phone, String telPhone, String level, String state, String eMail, String cuntomerNum, String ip) {
        return false;
    }

    @Override
    public ArrayList<UserModel> getUsers(String userId, String deptCode, String id, String userCode, String jobCode, String name, String account, String password, String address, String phone, String telPhone, String level, String state, String eMail, String perPageSize, String pageIndex) {
        return null;
    }

    @Override
    public boolean updateUsers(String userId, String id, String deptCode, String userCode, String jobCode, String name, String account, String password, String address, String phone, String telPhone, String level, String state, String eMail, String ip) {
        return false;
    }

    @Override
    public boolean deleteUsers(String userId, String id) {
        return false;
    }

    @Override
    public ArrayList<JobModel> getJobs(String userId, String id, String jobCode, String jobName) {
        ArrayList<JobModel> list=new ArrayList<>();
        return list;
    }

    @Override
    public boolean addILayoutDetail(String userId, String screenNum, ArrayList<HashMap> iLayoutList) {
        return false;
    }

    @Override
    public boolean configLargeIGoupVideo1(String userId, String iGroupId, ArrayList<HashMap> list) {
        return false;
    }

    @Override
    public ArrayList<OrgModel> getOrganiz(String userId, String serviceCode, String parentCode) {
        return null;
    }

    @Override
    public boolean addRoles(String userId, String roleCode, String roleName, String desc, String activeState) {
        return false;
    }

    @Override
    public ArrayList<RoleModel> getRoles(String userId, String id, String roleCode, String roleName, String activeState) {
        return null;
    }

    @Override
    public boolean updateRoles(String userId, String id, String roleCode, String roleName, String desc, String activeState) {
        return false;
    }

    @Override
    public boolean deleteRoles(String userId, String id) {
        return false;
    }

    @Override
    public boolean config_User_Role(String userId, String userCode, String roleId) {
        return false;
    }

    @Override
    public boolean disconfig_User_Role(String userId, String userCode, String roleId) {
        return false;
    }

    @Override
    public ArrayList<RoleModel> getUserRole(String userId, String userCode) {
        return null;
    }

    @Override
    public boolean addJobs(String userId, String code, String name, String desc) {
        return false;
    }

    @Override
    public boolean updateJobs(String userId, String id, String jobCode, String jobName, String desc) {
        return false;
    }

    @Override
    public boolean deleteJobs(String userId, String id) {
        return false;
    }

    @Override
    public boolean configRoleResFunc(String userId, String roleID, String privilege) {
        return false;
    }

    @Override
    public String getRoleResFunc(String userId, String roleID) {
        return null;
    }

    @Override
    public boolean configRoleDirectory(String userId, String dirID, String roleID, String privilege) {
        return false;
    }

    @Override
    public boolean updateRoleDirectory(String userId, String dirID, String roleID, String privilege) {
        return false;
    }

    @Override
    public boolean disconfigRoleDirectory(String userId, String dirID, String roleID, String privilege) {
        return false;
    }

    @Override
    public ArrayList<DirectoryModel> getRoleDirectoryPrivilege(String userId, String dirID, String roleID) {
        return null;
    }

    @Override
    public ArrayList<LoginLimitModel> getUserLoginLimitTimes(String userId, String userCode) {
        return null;
    }

    @Override
    public boolean addUserLoginLimitTimes(String userId, String userCode, String startDate, String endDate, String startTime, String endTime, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday, String desc) {
        return false;
    }

    @Override
    public boolean updateUserLoginLimitTimes(String userId, String userCode, String startDate, String endDate, String startTime, String endTime, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday, String desc) {
        return false;
    }

    @Override
    public boolean deleteUserLoginLimitTimes(String userId, String limitId) {
        return false;
    }

    @Override
    public ArrayList<AlarmInfoModel> getAlarmInfo(String userId, String linkVideoCode, String startTime, String endTime) {
        return null;
    }

    @Override
    public ArrayList<BaseModel> getAlarmType() {
        return null;
    }

    @Override
    public boolean rebootFd(String userCode, ArrayList<String> fdCodes) {
        return false;
    }

    @Override
    public boolean getNVRChannelInfo(String userCode, String fdCode) {
        return false;
    }

    @Override
    public boolean setNVRChannelInfo(String userCode, ArrayList<NVRChannelInfoModel> ipcList) {
        return false;
    }

    @Override
    public boolean getCameraOSD(String userCode, String fdCode, String videoCode, String channelNum) {
        return false;
    }

    @Override
    public boolean getFDOSD(String userCode, String fdCode) {
        return false;
    }

    @Override
    public boolean setCameraOSD(String userCode, ArrayList<CameraOSDInfoModel> cameraOSDList) {
        return false;
    }

    @Override
    public boolean startRecord4RealTimeVideo(String userCode, ArrayList<VideoRecordFileModel> requestList) {
        return false;
    }

    @Override
    public boolean stopRecord4RealTimeVideo(String userCode, ArrayList<VideoRecordFileModel> requestList) {
        return false;
    }

    @Override
    public boolean addContactUser(String userCode, String name, String phoneNo, String groupId) {
        return false;
    }

    @Override
    public boolean updateContactUser(String userCode, String id, String name, String phoneNo, String groupId) {
        return false;
    }

    @Override
    public boolean deleteContactUser(String userCode, String id) {
        return false;
    }

    @Override
    public ArrayList<ContactUserModel> getContactUser(String userCode, String id, String name, String phoneNo, String groupId) {
        return null;
    }

    @Override
    public boolean addContactUserGroup(String userCode, String name) {
        return false;
    }

    @Override
    public boolean updateContactUserGroup(String userCode, String id, String name) {
        return false;
    }

    @Override
    public boolean deleteContactUserGroup(String userCode, String id) {
        return false;
    }

    @Override
    public ArrayList<ContactUserModel> getContactUserGroup(String userCode, String id, String name) {
        return null;
    }

    @Override
    public boolean configRoleAlarmResource1(String userId, String roleId, String resId) {
        return false;
    }

    @Override
    public boolean disconfigRoleAlarmResource(String userId, String roleId, String resId) {
        return false;
    }

    @Override
    public boolean configContactGroupAlarmResource1(String userId, String groupId, String resId) {
        return false;
    }

    @Override
    public boolean disconfigContactGroupAlarmResource(String userId, String groupId, String resId) {
        return false;
    }

    @Override
    public ArrayList<AlarmResModel> getAlarmResByRoleId(String roleId) {
        return null;
    }

    @Override
    public ArrayList<AlarmResModel> getAlarmResByContactUserGroupId(String groupId) {
        return null;
    }

    @Override
    public ArrayList<String> configRoleAlarmResource(String userId, String roleId, ArrayList<String> alarmResIds) {
        return null;
    }

    @Override
    public ArrayList<String> configContactGroupAlarmResource(String userId, String groupId, ArrayList<String> alarmResIds) {
        return null;
    }

    @Override
    public String getFdTree() {
        return null;
    }

    @Override
    public ArrayList<ChannelModel> getRes(String userId, String type, String name, String fdCode, String code, String pageSize, String pageIndex) {
        return null;
    }

    @Override
    public ArrayList<AlarmSetModel> getAlarmSet(String userId, String alarmResId, String alarmSetId) {
        return null;
    }

    @Override
    public ArrayList<AlarmResModel> getAlarmResByUserCode(String userCode) {
        return null;
    }

    @Override
    public boolean forceTheUserLogout(String optUserId, String userCode) {
        return false;
    }

    @Override
    public ArrayList<ServiceModel> getServices(String userId, String code, String type, String parentCode) {
        return null;
    }

    @Override
    public boolean rebootService(String userId, String serviceCode) {
        return false;
    }

    @Override
    public boolean updateMasterSlaveFd(String userId, String masterFdCode, String channelNum, String slaveFdChannelCode, String slaveFDAccount, String slaveFDPassword, String slaveFDIP, String slaveFDPort) {
        return false;
    }

    @Override
    public boolean addMasterSlaveFd(String userId, String fdCode, String slaveFdChannelCode, String channelNum, String slaveFDAccount, String slaveFDPassword, String slaveFDIP, String slaveFDPort) {
        return false;
    }

    @Override
    public ArrayList<FDModel> getMasterSlaveFd(String userId, String slaveFdChannelCode) {
        return null;
    }

    @Override
    public boolean deleteMasterSlaveFd(String userId, String slaveFdChannelCode) {
        return false;
    }

    @Override
    public ArrayList<VideoModel> getVideo1(String userId, String code, String name, String type, String fdCode, String pageSize, String pageIndex) {
        return null;
    }

    @Override
    public ArrayList<FDModel> getFD(String pUserId, String fdTemplateCode, String longitude, String latitude, String code, String serverCode, String pageSize, String pageIndex) {
        return null;
    }

    @Override
    public boolean reSetCameraOSD(String userCode, String videoCode, String name) {
        return false;
    }

    @Override
    public MapConfigInfo getMapConfigInfo(String location) {
        return null;
    }
}
