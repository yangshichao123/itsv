package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class VideoModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author madi
	 * @说明：设备名称
	 *           
	 * */
	private String fdName;
	/**
	 * @author madi
	 * @说明：视频输入通道编号
	 *           
	 * */
	private String code;
	/**
	 * @author madi
	 * @说明：摄象机类型
	 *           
	 * */
	private String cameraTypeCode,cameraTypeName;
	/**
	 * @author madi
	 * @说明：安装位置
	 *           
	 * */
	private String address;
	/**
	 * @author madi
	 * @说明：经度
	 *           
	 * */
	private String longitude;
	/**
	 * @author madi
	 * @说明：纬度
	 *           
	 * */
	private String latitude;
	/**
	 * @author madi
	 * @说明：设备编号
	 *           
	 * */
	private String fdCode;
	/**
	 * @author madi
	 * @说明：编码格式
	 *           
	 * */
	private String codeFormat;
	/**
	 * @author madi
	 * @说明：分析类型
	 *           
	 * */
	private String analysistype;
	/**
	 * @author madi
	 * @说明：实时图像分辨率
	 *           
	 * */
	private String realRes;
	/**
	 * @author madi
	 * @说明：实时码流标识
	 *           
	 * */
	private String realCodeType;
	/**
	 * @author madi
	 * @说明：实时图像质量
	 *           
	 * */
	private String realImageQuality,mainImageQuality;
	 
	/**
	 * @author madi
	 * @说明：实时编码模式
	 *           
	 * */
	private String realCodeModel,mainCodeModel;
	 
	/**
	 * @author madi
	 * @说明：实时取流方式
	 *           
	 * */
	private String realTransModel,mainTransModel;
	 
	/**
	 * @author madi
	 * @说明：录像图像分辨率
	 *           
	 * */
	private String recres,mainRes,childRes;
	/**
	 * @author madi
	 * @说明：录像码流标识
	 *           
	 * */
	private String childCodeType;
	/**
	 * @author madi
	 * @说明：录像图像质量
	 *           
	 * */
	private String childImageQuality;
	/**
	 * @author madi
	 * @说明：录像编码模式
	 *           
	 * */
	private String childCodeModel;
	/**
	 * @author madi
	 * @说明：录像取流方式
	 *           
	 * */
	private String childTransModel;
	/**
	 * @author madi
	 * @说明：是否本地镜头
	 *           
	 * */
	private String isLocal;
	 
	/**
	 * @author madi
	 * @说明：录像码流标识
	 *           
	 * */
	private String recCodeType;
	/**
	 * @author madi
	 * @说明：录像图像质量
	 *           
	 * */
	private String recImageQuality;
	/**
	 * @author madi
	 * @说明：录像编码模式
	 *           
	 * */
	private String recCodeModel;
	/**
	 * @author madi
	 * @说明：录像取流方式
	 *           
	 * */
	private String recTransModel;
	/**
	 * @author madi
	 * @说明：镜头用途
	 *           
	 * */
	private String target;
	/**
	 * @author madi
	 * @说明：在所属设备中的视频通道号
	 *           
	 * */
	private String vChannel;
	/**
	 * @author madi
	 * @说明：在所属设备中的音频通道号
	 *           
	 * */
	private String aChannel;
	/**
	 * @author madi
	 * @说明：是否云台
	 *           
	 * */
	private String ptz;
	/**
	 * @author madi
	 * @说明：云台协议
	 *           
	 * */
	private String ptzProtocol;
	/**
	 * @author madi
	 * @说明：osd名称
	 *           
	 * */
	private String osd;
	/**
	 * @author madi
	 * @说明：是否本地镜头
	 *           
	 * */
	private String bLocal;
	/**
	 * @author madi
	 * @说明：osd名称是否显示
	 *           
	 * */
	private String showOsd;
	/**
	 * @author madi
	 * @说明：osd-x坐标
	 *           
	 * */
	private String osdX;
	/**
	 * @author madi
	 * @说明：osd-y坐标
	 *           
	 * */
	private String osdY;
	/**
	 * @author madi
	 * @说明：时间是否显示
	 *           
	 * */
	private String showTime;
	/**
	 * @author madi
	 * @说明：时间-x坐标
	 *           
	 * */
	private String timeX;
	/**
	 * @author madi
	 * @说明：时间-y坐标
	 *           
	 * */
	private String timeY;
	/**
	 * @author madi
	 * @说明：码率 
	 *           
	 * */
	private String showRealStatus;
	/**
	 * @author madi
	 * @说明：帧率 
	 *           
	 * */
	private String realStatusX;
	/**
	 * @author madi
	 * @说明：I帧间隔 
	 *           
	 * */
	private String realStatusY;
	/**
	 * @author madi
	 * @说明：子码流码率 
	 *           
	 * */
	private String showRecStatus;
	/**
	 * @author madi
	 * @说明：子码流帧率 
	 *           
	 * */
	private String recStatusX;
	/**
	 * @author madi
	 * @说明：子码流I帧间隔 
	 *           
	 * */
	private String recStatusY;
	/**
	 * @author madi
	 * @说明：智能视频状态显示
	 *           
	 * */
	private String showAnalysisStatus;
	/**
	 * @author madi
	 * @说明：智能状态-x坐标
	 *           
	 * */
	private String analysisStatusX;
	/**
	 * @author madi
	 * @说明：智能状态-x坐标
	 *           
	 * */
	private String analysisStatusY;
	/**
	 * @author madi
	 * @说明：创建时间
	 *           
	 * */
	private Date createTime;
	/**
	 * @author madi
	 * @说明：修改时间
	 *           
	 * */
	private String modifyTime;
	/**
	 * @author madi
	 * @说明：vss编号
	 *           
	 * */
	private String vssCode;
	/**
	 * @author madi
	 * @说明：vss名称
	 *           
	 * */
	private String vssName;
	/**
	 * @author madi
	 * @说明：接入服务名称
	 *           
	 * */
	private String pagName;
	/**
	 * @author madi
	 * @说明：接入服务编号
	 *           
	 * */
	private String pagCode;
	/**
	 * @author madi
	 * @说明：数据转发服务编号
	 *           
	 * */
	private String vtduCode;
	/**
	 * @author madi
	 * @说明：数据转发服务名称
	 *           
	 * */
	private String vtduName;
	/**
	 * @author madi
	 * @说明：中心网关服务名称
	 *           
	 * */
	private String cmsName;
	/**
	 * @author madi
	 * @说明：中心网关服务编号
	 *           
	 * */
	private String cmsCode;
	/**
	 * @author madi
	 * @说明：实时码流标识
	 *           
	 * */
	private String mainCodeType;
	
	/**
	 * @author madi
	 * @说明：通道状态
	 */
	private String State;
	
	/**
	 * @author madi
	 * @说明：语音通道ID
	 */
	private String voiceId;
	/**
	 * @author madi
	 * @说明：子码流使能标识 ，0-不使能，1-使能
	 */
	private String childEnable;
	/**
	 * 主设备类型编号
	 * */
	private String  fdTypeCode;
	/**
	 * 保留字段
	 * */
	private String reserve;
	/**
	 * 视频通道类型1模拟通道2IPC通道
	 * */
	private String  channelType,channelTypes;
	/**
	 * 如果该通道为NVR IPC通道则为相应IPC设备IP地址
	 * */
	private String  channelIP;
	/**
	 * 存储计划状态信息
	 * */
	private VideoStorePlanModel storePlanState;
	

	
}
