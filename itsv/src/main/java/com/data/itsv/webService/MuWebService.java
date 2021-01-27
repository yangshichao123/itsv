package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;
import com.data.itsv.model.CameraOSDInfoModel;
import com.data.itsv.model.NVRChannelInfoModel;
import org.apache.ibatis.annotations.ResultType;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.ws.BindingType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//http://192.168.0.145:8888/ITSV/services/MOMPService
@WebService(name ="MOMPService" ,targetNamespace = "http://webService.itsv.data.com")
public interface MuWebService {
    String getHcsj(@WebParam(name = "qsrq", targetNamespace = "http://webService.itsv.data.com") String qsrq,
                   @WebParam(name = "jsrq", targetNamespace = "http://webService.itsv.data.com") String jsrq,
                   @WebParam(name = "xm", targetNamespace = "http://webService.itsv.data.com") String xm);

    String getWxjh(String userName, String userId, String str) ;

    // User getUser(String userName, String userId) ;


  /*  @WebResult(name = "out")
    LoginUserModel userLogin(@WebParam(name = "in0", targetNamespace = "http://webService.itsv.data.com") String account
            ,@WebParam(name = "in1", targetNamespace = "http://webService.itsv.data.com") String password);*/

    LoginUserModel userLogin(String account
            ,String password);

   /* LoginUserModel userLogin(String account
            , String password);*/


    public String getDirByUserId(String userId, String type);

    /**
     * 根据用户id获取用户目录
     *
     * @author ghj
     * @param userId
     *            :用户id
     * @param dirId
     *            :目录id
     * @param type
     *            :资源类型
     *
     * */

    public String getVideoByUidDirId1(String userId, String dirId, String type);

    /**
     * 视频流上墙
     *
     * @author ghj
     * @param userId
     *            :用户编号
     * @param videoCode
     *            :视频资源编号
     * @param screenNum
     *            :屏幕号
     *
     * */

    public boolean sendVideo2ScreenSystem(String userId, String videoCode,
                                          String screenNum);

    /**
     * 视频流上墙
     *
     * @author ghj
     * @param userId
     *            :用户编号
     * @param videoCode
     *            :视频资源编号
     *            :屏幕号
     *
     * */

    public boolean sendVideo2ScreenSystem1(String userId, String videoCode,
                                          String decoderServerIp, String decoderServerPort,
                                          String ioIndexNumber);

    /**
     * 查询我的目录信息
     *
     * @author ghj
     */

    public String getIDirectory(String userId);

    /**
     * 添加我的布局信息
     *
     * @author ghj
     * @param loginUserId
     *            :登陆人编号
     * @param userCode
     *            ：用户编号
     * @param screenNum
     *            ：分屏数
     * @param name
     *            ：我的布局名称
     * @param descs
     *            ：描述信息
     *
     * */
    public String addILayout(String loginUserId, String userCode,
                             String screenNum, String name, String descs);

    /**
     * 查询我的布局信息
     *
     * @author ghj
     * @param loginUserId
     *            :登陆人编号
     * @param Id
     *            ：编号
     * @param userCode
     *            ：用户编号
     * @param pageSize
     *            ：每页大小
     * @param pageIndex
     *            ：第几页
     *            ：总页数
     *            ：总记录数
     *
     * */
    public ArrayList<ILayoutModel> getILayout(String loginUserId, String Id,
                                              String userCode, String pageSize, String pageIndex);

    /**
     * 查询我的布局信息
     *
     * @author ghj
     * @param loginUserId
     *            :登陆人编号
     * @param Id
     *            ：编号
     * @param userCode
     *            ：用户编号
     *
     * */
    public String getILayouts(String loginUserId, String Id, String userCode);

    /**
     * 删除我的布局信息
     *
     * @author ghj
     * @param loginUserId
     *            :登陆人编号
     * @param id
     *            ：编号
     *
     * */
    public boolean delILayout(String loginUserId, String id);

    /**
     * 更新我的布局信息
     *
     * @author ghj
     * @param loginUserId
     *            :登陆人编号
     * @param userCode
     *            ：用户编号
     *            ：编号
     * @param screenNum
     *            ：分屏数目
     * @param layoutName
     *            ：布局名称
     * @param descs
     *            ：描述
     *
     * */
    public boolean updateILayout(String loginUserId, String userCode,
                                 String id, String screenNum, String layoutName, String descs);

    /**
     * 删除我的目录信息
     *
     * @author ghj
     */
    public boolean deletIDirectory(String userId, String contenCode);

    /**
     * 添加我的目录信息
     *
     * @author ghj
     *            :
     *
     * */

    public String addIDirectory(String userId, String name, String descs,
                                String parentId);

    /**
     * 更新我的目录信息
     *
     * @author ghj
     *            :
     *
     * */
    public boolean updateIDirectory(String userId, String name, String descs,
                                    String dirId, String parentId);

    /**
     * 配置我的目录下资源
     *
     * @author madi
     *
     * */
    public boolean configIGoupVideo(String userId, String iGroupId,
                                    String videoCode);

    /**
     * 通过我的目录编号 查询该目录下所能看到的视频资源
     *
     * @author madi
     *
     * */
    public String getVideoByIGoupId(String userId, String dirId);

    /**
     * 删除我的目录下资源信息
     *
     * @author madi
     *
     * */
    public boolean deleteFavoriteVideo(String userId, String videoCode,
                                       String iGroupId);

    /**
     * 批量配置我的目录下资源
     *
     * @author madi
     * */
    public ArrayList<ChannelModel> configLargeIGoupVideo(String userId,
                                                         ArrayList<HashMap> list);

    /**
     * 添加我的轮巡
     *
     * @author ghj
     * @param userId
     *            :用户id
     *
     * */

    public String addTour(String userId, String name, String descs,
                          String interval, String screen, ArrayList<HashMap> list);

    /**
     * 添加我的轮巡
     *
     * @author ghj
     * @param userId
     *            :用户id
     *
     * */

    public ArrayList<TourModel> getTour(String userId);

    /**
     * 获取我的轮巡
     *
     * @author ghj
     * @param userId
     *            :用户id
     *
     * */

    public String getTours(String userId);

    /**
     * 更新我的轮巡
     *
     * @author ghj
     * @param userId
     *            :用户id
     *
     * */

    public boolean updateTour(String userId, String tourId, String name,
                              String descs, String interval, String screen, ArrayList<HashMap> list);

    /**
     * 删除我的轮巡
     *
     * @author ghj
     * @param userId
     *            :用户id
     *
     * */

    public boolean deleteTour(String userId, String tourId);

    /**
     * 通过用户编号查询该用户自建目录树
     *
     * @author madi
     *
     * */
    public String getIGroupTree(String userId);

    /**
     * 获取布局详细信息
     *
     * @author ghj
     */
    public ArrayList<ILayoutDetailModel> getILayoutDetail(String userId,
                                                          String iLayoutId);

    /**
     * 根据轮询组id获取用户目录
     *
     * @author ghj
     * @param userId
     *            tourId:轮询组id
     *
     * */

    public String getVideoByTourId(String userId, String tourId);

    /**
     * 通过设备扩展编号查询设备编号
     * */
    public VideoModel getVideoCodeByExtCode(String extCode);

    /**
     * 通过设备扩展编号查询设备编号
     * */
    public VideoModel getVideoCodeByCode(String code);

    /**
     * 查询指定位置摄像机
     * conditon:lineCode,stationCode,videoName;lineCode,stationCode,videoName
     * */
    public ArrayList<VideoModel> searchCamera(String condition);

    /**
     * 查询指定位置摄像机
     * conditon:lineCode,stationCode,videoName;lineCode,stationCode,videoName
     * */
    public ArrayList<VideoModel> searchCameraByPage(String condition,
                                                    String pageSize, String pageIndex);

    /**
     * 查询摄像机录像计划存储类型
     * */
    public int getVideoPlanStoreType(String videoCode);

    /**
     * 查询视频通道信息
     *
     * @author ghj
     *
     * */
    public ArrayList<VideoModel> getVideo(String pageSize, String pageIndex);

    /**
     * 查询客户端版本信息
     *
     * @author ghj
     *
     * */
    public VersionModel checkClientLastVersion(String userId);

    /**
     *
     * 检查用户功能模块权限
     */
    public boolean checkFuncPri(String userId, String funcCode);

    /**
     * 查询默认用户头像
     *
     * @author ghj
     *
     * */
    public String getDefaulUserFace(String userId);

    /**
     * @author Administrator 添加日志
     * @param content
     *            日志内容
     * @param flag
     *            标志，1系统日志，2操作日志
     * */
    public boolean addLog(String content, String flag);

    /**
     * 修改密码
     * */
    public boolean updateSysUserPassword(String optUserId, String userId,
                                         String password);

    /**
     * 根据用户id获取用户目录
     *
     * @author ghj
     * @param userId
     *            :用户id
     * @param dirId
     *            :目录id
     *
     * */

    public ArrayList<VideoModel> getVideoByUidDirId(String userId, String dirId);

    /**
     * 通过我的目录编号 查询该目录下所能看到的视频资源
     *
     * @author ghj
     *
     * */
    public ArrayList<VideoModel> getVideoModelByIGoupId(String userId,
                                                        String dirId);

    /**
     * 获取我的轮巡
     *
     * @author ghj
     * @param userId
     *            用户id
     *
     * */

    public ArrayList<TourModel> getTour1(String userId, String id);

    /**
     * 根据轮询组id获取用户目录
     *
     * @author ghj
     * @param userId
     *            tourId:轮询组id
     *
     * */

    public ArrayList<TourDetailModel> getTourDetailByTourId(String userId,
                                                            String tourId);

    /**
     * 添加用户信息
     *
     * @author nihuanshan
     *
     * @param userId
     *            ：登陆人编号
     * @param deptCode
     *            ：部门编号
     * @param userCode
     *            ：用户编号
     * @param jobCode
     *            ：职位编号
     * @param name
     *            ：用户名
     * @param account
     *            ：账号
     * @param password
     *            ：密码
     * @param address
     *            ：地址
     * @param phone
     *            ：手机号码
     * @param telPhone
     *            ：座机号码
     * @param level
     *            ：用户等级：1-5，数越大级别越高
     * @param state
     *            ：用户状态：0未启用，1启用
     * @param eMail
     *            ：邮箱
     * @param cuntomerNum
     *            ：所属客户
     * @param ip
     *            ：ip
     * */
    public boolean addUsers(String userId, String deptCode, String userCode,
                            String jobCode, String name, String account, String password,
                            String address, String phone, String telPhone, String level,
                            String state, String eMail, String cuntomerNum, String ip);

    /**
     * 获取用户信息
     *
     * @author nihuanshan ：
     *
     * @param userId
     *            ：登陆人编号
     * @param deptCode
     *            ：部门编号
     * @param id
     *            ：用户id
     * @param userCode
     *            ：用户编号
     * @param jobCode
     *            ：职位编号
     * @param name
     *            ：用户名
     * @param account
     *            ：账号
     * @param password
     *            ：密码
     * @param address
     *            ：地址
     * @param phone
     *            ：手机号码
     * @param telPhone
     *            ：座机号码
     * @param level
     *            ：用户等级：1-5，数越大级别越高
     * @param state
     *            ：用户状态：0未启用，1启用
     * @param eMail
     *            ：邮箱
     * @param perPageSize
     *            ：每页大小
     * @param pageIndex
     *            ：第几页
     * */
    public ArrayList<UserModel> getUsers(String userId, String deptCode,
                                         String id, String userCode, String jobCode, String name,
                                         String account, String password, String address, String phone,
                                         String telPhone, String level, String state, String eMail,
                                         String perPageSize, String pageIndex);

    /**
     * 更新用户信息
     *
     * @author nihuanshan
     *
     * @param userId
     *            ：登陆人编号
     * @param id
     *            ：用户id
     * @param deptCode
     *            ：部门编号
     * @param userCode
     *            ：用户编号
     * @param jobCode
     *            ：职位编号
     * @param name
     *            ：用户名
     * @param account
     *            ：账号
     * @param password
     *            ：密码
     * @param address
     *            ：地址
     * @param phone
     *            ：手机号码
     * @param telPhone
     *            ：座机号码
     * @param level
     *            ：用户等级：1-5，数越大级别越高
     * @param state
     *            ：用户状态：0未启用，1启用
     * @param eMail
     *            ：邮箱
     * @param ip
     *            ：ip
     * */
    public boolean updateUsers(String userId, String id, String deptCode,
                               String userCode, String jobCode, String name, String account,
                               String password, String address, String phone, String telPhone,
                               String level, String state, String eMail, String ip);

    /**
     * 删除用户信息
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,id：序号
     * */
    public boolean deleteUsers(String userId, String id);

    /**
     * 获取职位信息列表
     *
     * @author nihuanshan
     * @param userId
     *            :登陆人编号,id：序号,jobCode：编号,jobName名称
     *
     * */



    public List<JobModel> getJobs(String userId, String id,
                                  String jobCode, String jobName);

    /**
     * 添加布局详细信息
     *
     * @author ghj
     */
    public boolean addILayoutDetail(String userId, String screenNum,
                                    ArrayList<HashMap> iLayoutList);

    /**
     * 批量配置我的目录下资源
     *
     * @author madi
     * */
    public boolean configLargeIGoupVideo1(String userId, String iGroupId,
                                         ArrayList<HashMap> list);

    /**
     * 查询组织信息
     *
     * @author zml
     *            :
     *
     * */
    public ArrayList<OrgModel> getOrganiz(String userId, String serviceCode,
                                          String parentCode);

    /**
     * 添加角色信息
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,roleCode：角色编号,roleName：角色名称,desc：角色描述
     *            activeState:角色启用状态，0未启用，1启用
     * */
    public boolean addRoles(String userId, String roleCode, String roleName,
                            String desc, String activeState);

    /**
     * 获取角色信息
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,id：角色id,roleCode：角色编号,roleName：角色名称,
     *            activeState:角色启用状态，0未启用，1启用
     * */
    public ArrayList<RoleModel> getRoles(String userId, String id,
                                         String roleCode, String roleName, String activeState);

    /**
     * 更新角色信息
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,id:序号,roleCode：角色编号,roleName：角色名称,desc：职位描述
     *            activeState:角色启用状态，0未启用，1启用
     * */
    public boolean updateRoles(String userId, String id, String roleCode,
                               String roleName, String desc, String activeState);

    /**
     * 删除角色信息
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,id：序号
     *
     * */
    public boolean deleteRoles(String userId, String id);

    /**
     * 配置用户角色关系
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,userCode：用户编号,roleId：角色ID
     * */
    public boolean config_User_Role(String userId, String userCode,
                                    String roleId);

    /**
     * 拆除用户角色关系
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,userCode：用户编号,roleId：角色ID
     * */
    public boolean disconfig_User_Role(String userId, String userCode,
                                       String roleId);

    /**
     * 获取用户角色信息
     *
     * @author nihuanshan
     * @param userId
     *            ：登陆人编号,userCode：用户编号
     * */
    public ArrayList<RoleModel> getUserRole(String userId, String userCode);

    /**
     * 增加职位信息
     *
     * @author ghj
     * @param userId
     *            :登陆人编号,code：编号,name：名称,desc：描述
     *
     * */
    public boolean addJobs(String userId, String code, String name, String desc);

    /**
     * 更新职位信息
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,id:序号,jobCode：编号,jobName：职位名称,desc：职位描述
     *
     * */
    public boolean updateJobs(String userId, String id, String jobCode,
                              String jobName, String desc);

    /**
     * 删除职位信息
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,id：序号
     *
     * */
    public boolean deleteJobs(String userId, String id);

    /**
     * 配置角色模块权限
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,roleID：角色ID,privilege：模块权限
     * */
    public boolean configRoleResFunc(String userId, String roleID,
                                     String privilege);

    /**
     * 获取角色模块权限
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,roleID：角色ID
     * */
    public String getRoleResFunc(String userId, String roleID);

    /**
     * 配置角色 目录权限
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,dirID,目录ID,roleID：角色ID,privilege：目录权限
     * */
    public boolean configRoleDirectory(String userId, String dirID,
                                       String roleID, String privilege);

    /**
     * 更新角色 目录权限
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,dirID,目录ID,roleID：角色ID,privilege：目录权限
     * */
    public boolean updateRoleDirectory(String userId, String dirID,
                                       String roleID, String privilege);

    /**
     * 拆除角色 目录权限
     *
     * @author ghj
     * @param userId
     *            ：登陆人编号,dirID,目录ID,roleID：角色ID,privilege：目录权限
     * */
    public boolean disconfigRoleDirectory(String userId, String dirID,
                                          String roleID, String privilege);

    /**
     * 获取角色目录权限
     *
     * @author ghj
     * @param userId
     *            登陆人编号,dirID,目录ID,roleID角色ID
     * */
    public ArrayList<DirectoryModel> getRoleDirectoryPrivilege(String userId,
                                                               String dirID, String roleID);

    /**
     * 获取用户登录时间段限制
     *
     * @author ghj
     * @param userId
     *            登陆人编号,userCode:用户编号
     * */
    public ArrayList<LoginLimitModel> getUserLoginLimitTimes(String userId,
                                                             String userCode);

    /**
     * 增加用户登录时间段限制
     *
     * @author ghj
     * @param userId
     *            登陆人编号,userCode:用户编号
     * */
    public boolean addUserLoginLimitTimes(String userId, String userCode,
                                          String startDate, String endDate, String startTime, String endTime,
                                          String monday, String tuesday, String wednesday, String thursday,
                                          String friday, String saturday, String sunday, String desc);

    /**
     * 更新用户登录时间段限制
     *
     * @author ghj
     * @param userId
     *            登陆人编号,userCode:用户编号
     * */
    public boolean updateUserLoginLimitTimes(String userId, String userCode,
                                             String startDate, String endDate, String startTime, String endTime,
                                             String monday, String tuesday, String wednesday, String thursday,
                                             String friday, String saturday, String sunday, String desc);

    /**
     * 删除用户登录时间段限制
     *
     * @author ghj
     * @param userId
     *            登陆人编号,limitId:用户登录限制id
     * */
    public boolean deleteUserLoginLimitTimes(String userId, String limitId);

    /**
     * 查询告警信息
     *
     * @author ghj
     */
    public ArrayList<AlarmInfoModel> getAlarmInfo(String userId,
                                                  String linkVideoCode, String startTime, String endTime);

    /**
     * 查询告警类型信息
     *
     * @author ghj
     */
    public ArrayList<BaseModel> getAlarmType();

    /**
     *重启设备
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param fdCodes
     *            设备编号
     * */

    public boolean rebootFd(String userCode, ArrayList<String> fdCodes);

    /**
     *获取设备从属关系(NVR和IPC关系)
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param fdCode
     *            设备编号
     *            设备通道号 0(通道号，-1表示查询所有通道)
     * */

    public boolean getNVRChannelInfo(String userCode, String fdCode);

    /**
     *动态增加NVR设备通道（IPC）
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *            设备通道号 0(通道号，-1表示查询所有通道)
     * */

    public boolean setNVRChannelInfo(String userCode,
                                     ArrayList<NVRChannelInfoModel> ipcList);

    /**
     *查询摄像机OSD信息
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param fdCode
     *            设备编号
     *@param channelNum
     *            设备通道号 0(通道号，-1表示查询所有通道)
     *@param videoCode
     *            摄像机编号
     * */

    public boolean getCameraOSD(String userCode, String fdCode,
                                String videoCode, String channelNum);

    /**
     *查询NVR、DVR设备下摄像机OSD信息
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param fdCode
     *            设备编号
     * */

    public boolean getFDOSD(String userCode, String fdCode);

    /**
     *设置摄像机OSD信息
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param cameraOSDList
     *            摄像机OSD信息
     * */

    public boolean setCameraOSD(String userCode,
                                ArrayList<CameraOSDInfoModel> cameraOSDList);

    /**
     *开始实时录像
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param requestList
     *            请求录像集合
     * */

    public boolean startRecord4RealTimeVideo(String userCode,
                                             ArrayList<VideoRecordFileModel> requestList);

    /**
     *停止实时录像
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param requestList
     *            请求录像集合
     * */

    public boolean stopRecord4RealTimeVideo(String userCode,
                                            ArrayList<VideoRecordFileModel> requestList);

    /**
     *添加联系人
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param name
     *            姓名
     *@param phoneNo
     *            手机号
     *@param groupId
     *            分组编号
     * */

    public boolean addContactUser(String userCode, String name, String phoneNo,
                                  String groupId);

    /**
     *更新联系人
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param name
     *            姓名
     *@param phoneNo
     *            手机号
     *@param groupId
     *            分组编号
     *@param id
     *            联系人Id
     * */

    public boolean updateContactUser(String userCode, String id, String name,
                                     String phoneNo, String groupId);

    /**
     *删除联系人
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *            分组编号
     *@praam id 联系人Id
     * */

    public boolean deleteContactUser(String userCode, String id);

    /**
     *查询联系人
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param name
     *            姓名
     *@param phoneNo
     *            手机号
     *@param groupId
     *            分组编号
     *@param id
     *            联系人Id
     * */

    public ArrayList<ContactUserModel> getContactUser(String userCode,
                                                      String id, String name, String phoneNo, String groupId);

    /**
     *添加联系人分组
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param name
     *            分组名称
     * */

    public boolean addContactUserGroup(String userCode, String name);

    /**
     *更新联系人分组
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param name
     *            名称
     *@param id
     *            联系人Id
     * */

    public boolean updateContactUserGroup(String userCode, String id,
                                          String name);

    /**
     *删除联系人分组
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *            名称
     *@praam id 联系人Id
     * */

    public boolean deleteContactUserGroup(String userCode, String id);

    /**
     *查询联系人分组
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     *@param name
     *            名称
     *@param id
     *            联系人Id
     * */

    public ArrayList<ContactUserModel> getContactUserGroup(String userCode,
                                                           String id, String name);
    /**
     * 配置角色订阅告警资源
     *
     * @author gaohejie
     *
     * */
    public boolean configRoleAlarmResource1(String userId, String roleId,String resId);
    /**
     * 删除角色订阅告警资源
     *
     * @author gaohejie
     *
     * */
    public boolean disconfigRoleAlarmResource(String userId, String roleId,String resId);
    /**
     * 配置联系人分组订阅告警资源
     *
     * @author gaohejie
     *
     * */
    public boolean configContactGroupAlarmResource1(String userId, String groupId,String resId);
    /**
     * 删除联系人分组订阅告警资源
     *
     * @author gaohejie
     *
     * */
    public boolean disconfigContactGroupAlarmResource(String userId, String groupId,String resId);
    /**
     * 通过角色编号告警资源下载
     *
     * @author ghj
     * @param roleId 角色ID
     * */
    public ArrayList<AlarmResModel> getAlarmResByRoleId(String roleId);
    /**
     * 通过联系人分组编号告警资源下载
     *
     * @author ghj
     * @param groupId 联系人分组编号
     * */
    public ArrayList<AlarmResModel> getAlarmResByContactUserGroupId(String groupId);
    /**
     * 配置角色订阅告警资源
     *
     * @author gaohejie
     *
     * */
    public ArrayList<String> configRoleAlarmResource(String userId, String roleId,ArrayList<String> alarmResIds);
    /**
     * 配置角色订阅告警资源
     *
     * @author gaohejie
     *
     * */
    public ArrayList<String> configContactGroupAlarmResource(String userId, String groupId,ArrayList<String> alarmResIds);

    /**
     * 获得存储服务下的通道资源树
     *
     * @author gaohejie
     **/
    public String getFdTree();
    /**
     * 查询资源信息
     *
     * @author gaohejie
     * */
    public ArrayList<ChannelModel> getRes(String userId, String type,
                                          String name, String fdCode, String code, String pageSize,
                                          String pageIndex);
    /**
     * 查询告警通道告警设置信息
     *
     * @author gaohejie
     *
     * */
    public ArrayList<AlarmSetModel> getAlarmSet(String userId,
                                                String alarmResId, String alarmSetId);
    /**
     * 通过用户编号告警资源下载
     *
     * @author ghj
     *
     *
     * */
    public ArrayList<AlarmResModel> getAlarmResByUserCode(String userCode);
    /**
     *
     * 强迫用户下线
     * @author ghj
     * @param optUserId 操作人编号
     * @param userCode 用户编号
     *
     * */
    public boolean forceTheUserLogout(String optUserId,String userCode);
    /**
     * 查询服务信息
     *
     * @author ghj
     * */
    public ArrayList<ServiceModel> getServices(String userId, String code,
                                               String type, String parentCode);
    /**
     * 系统服务重启
     * @author ghj
     * @param userId 下线用户编号
     * @param serviceCode 服务编号
     * */
    public boolean rebootService(String userId,String serviceCode);
    /**
     * 更新主子设备关系
     *
     * @author gaohejie
     * @param userId
     *            登陆人编号
     * @param slaveFdChannelCode
     *            子设备通道编号
     * @return 成功失败标志 true成功，false失败
     * */
    public boolean updateMasterSlaveFd(String userId,String masterFdCode,String channelNum,
                                       String slaveFdChannelCode,String slaveFDAccount,String slaveFDPassword,String slaveFDIP,String slaveFDPort);
    /**
     * 配置主子设备关系
     *
     * @author gaohejie
     * @param userId
     *            登陆人编号
     * @param slaveFdChannelCode
     *            子设备通道编号
     * @return 成功失败标志 true成功，false失败
     * */
    public boolean addMasterSlaveFd(String userId,String fdCode,
                                    String slaveFdChannelCode,String channelNum,String slaveFDAccount,String slaveFDPassword,String slaveFDIP,String slaveFDPort);
    /**
     * 配置主子设备关系
     *
     * @author gaohejie
     * @param userId
     *            登陆人编号
     * @param slaveFdChannelCode
     *            子设备通道编号
     * @return 成功失败标志 true成功，false失败
     * */
    public ArrayList<FDModel> getMasterSlaveFd(String userId,
                                               String slaveFdChannelCode);

    /**
     * 删除主子设备关系
     *
     * @author gaohejie
     * @param userId
     *            登陆人编号
     * @param slaveFdChannelCode
     *            子设备通道编号
     * @return 成功失败标志 true成功，false失败
     * */
    public boolean deleteMasterSlaveFd(String userId,
                                       String slaveFdChannelCode);
    /**
     * 查询视频通道信息
     *
     * @author gaohejie
     * */
    public ArrayList<VideoModel> getVideo1(String userId, String code,
                                          String name, String type, String fdCode, String pageSize,
                                          String pageIndex);
    /**
     * 查询设备信息
     *
     * @author gaohejie
     */
    public ArrayList<FDModel> getFD(String pUserId, String fdTemplateCode,
                                    String longitude, String latitude, String code, String serverCode,
                                    String pageSize, String pageIndex);
    /**
     *设置摄像机OSD信息
     *
     * @author gaohe
     *@param userCode
     *            用户编号
     * */

    public boolean reSetCameraOSD(String userCode,String videoCode,String name);
    /**
     * 查询地图配置信息
     * */
    public MapConfigInfo getMapConfigInfo(String location);

}
