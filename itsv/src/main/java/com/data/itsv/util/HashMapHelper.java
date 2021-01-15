package com.data.itsv.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.data.itsv.model.LogRest;
import com.data.itsv.model.SUser;
import io.netty.channel.ChannelHandlerContext;

/**
 * 该类提供各种功能HashMap
 * @author ghj
 * */
public class HashMapHelper {
	private static  HashMap<String,String> serverMap ;
	private static  HashMap<String,ArrayList> dataMap ;
	private static  HashMap<String,ChannelHandlerContext> userConnectionMap ;
	private static  HashMap<Long,ChannelHandlerContext> connMap ;
	private static  HashMap<String,ArrayList> userOrderAlarmMap ;
	private static  HashMap<String,ArrayList<ChannelHandlerContext>> userOrderAlarmSessionMap ;
	private static  HashMap<Long,String> sessionIdUserCodeMap ;

	// 互斥锁
	public static Lock lock = new ReentrantLock();
	/**
	 * 登录用户信息
	 * @return
	 */
	private static  HashMap<String,SUser> loginUserMap ;
	/**
	 * 连接信息
	 * @return
	 */
	public synchronized static HashMap<Long,ChannelHandlerContext> getConnMap(){
		if(connMap==null){
			connMap = new HashMap<Long,ChannelHandlerContext>();
		}
		return connMap;
	}
	/**
	 * @说明：用户Socket和用户编号
	 * @return
	 */
	
	public synchronized static HashMap<Long,String> getSessionIdUserCodeMap(){
		if(sessionIdUserCodeMap==null){
			sessionIdUserCodeMap = new HashMap<Long,String>();
		}
		return sessionIdUserCodeMap;
	}
	/**
	 * 获登录用户信息
	 * @return
	 */
	public synchronized static HashMap<String,SUser> getLoginUserMap(){
		if(loginUserMap==null){
			loginUserMap = new HashMap<String,SUser>();
		}
		return loginUserMap;
	}
	/**
	 * 获登录用户连接信息
	 * @return
	 */
	public synchronized static HashMap<String,ChannelHandlerContext> getUserConnectionMap(){
		if(userConnectionMap==null){
			userConnectionMap = new HashMap<String,ChannelHandlerContext>();
		}
		return userConnectionMap;
	}
	/**
	 * 获登录用户订阅告警信息
	 * @return
	 */
	public synchronized static HashMap<String,ArrayList> getUserOrderAlarmMap(){
		if(userOrderAlarmMap==null){
			userOrderAlarmMap = new HashMap<String,ArrayList>();
		}
		return userOrderAlarmMap;
	}
	/**
	 * 获登录用户订阅告警信息
	 * @return
	 */
	public synchronized static HashMap<String,ArrayList<ChannelHandlerContext>> getUserOrderAlarmSessionMap(){
		if(userOrderAlarmSessionMap==null){
			userOrderAlarmSessionMap = new HashMap<String,ArrayList<ChannelHandlerContext>>();
		}
		return userOrderAlarmSessionMap;
	}
	/**
	 * 获ArraList Map
	 * @return
	 */
	public synchronized static HashMap<String,ArrayList> getDataMap(){
		if(dataMap==null){
			dataMap = new HashMap<String,ArrayList>();
		}
		return dataMap;
	}
/**
 * @author ghj
 * @說明：私有会构造函数
 * */
	private HashMapHelper(){
		
	}
	/**
	 * @author ghj
	 * @說明：保存要进行入库的日志HashMap
	 * 
	 * */
	private static HashMap<String,LogRest> logMap ;
	
	public synchronized static HashMap<String,LogRest> getLogMap(){
		if(logMap==null){
			logMap=new HashMap<String, LogRest>();
		}
		return logMap;
	}
	/**
	 * 获取服务器地址
	 * @return
	 */
	public synchronized static HashMap<String,String> getServerMap(){
		if(serverMap==null){
			serverMap = new HashMap<String,String>();
		}
		return serverMap;
	}
	public synchronized static void setServerMap(String first, String second){
		if(serverMap==null){
			serverMap = new HashMap<String,String>();
		}
		serverMap.put(first, second);
	}
}
