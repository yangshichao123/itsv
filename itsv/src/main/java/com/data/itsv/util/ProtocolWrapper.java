package com.data.itsv.util;

import com.data.itsv.netty.vo.BaseMsg;

import java.io.UnsupportedEncodingException;
import java.util.Date;


 
 

public class ProtocolWrapper {
    
	/**
	 * @author ghj
	 * @说明：计数器
	 * */
	private static int counter=0;
	/**
	 * @author ghj
	 * @说明：协议打包
	 * */
	public static BaseMsg wrap(String messageContent, BaseMsg bm){
		
		counter++;
		
		
		 
		try {
			byte[] resutBytes = messageContent.toString().getBytes("utf-8");
			int bodySize = resutBytes.length;
			
			
//			bm.setCallId(UtilHelper.string2Bytes(32, UtilHelper.appendStr(32, ""+counter+(new Date()).getTime())));
//			bm.setDest(UtilHelper.string2Bytes(32, ""));
//			bm.setProText(UtilHelper.string2Bytes(8, "DT-MOMP"));
//			bm.setSeq(0);
//			bm.setSrc(UtilHelper.string2Bytes(32, ""));
//			bm.setType(0x1234);
//			bm.setVersion(1);
			bm.setBody(resutBytes);
			bm.setBodySize(bodySize);
		} catch (UnsupportedEncodingException e) {
			 
			e.printStackTrace();
		}
		
		 return bm;
	}
	
	/**
	 * @author ghj
	 * @说明：协议打包
	 * */
	public static BaseMsg wrap(String messageContent){
		
		counter++;
		
		BaseMsg bm = new BaseMsg();
		 
		try {
			byte[] resutBytes = messageContent.toString().getBytes("utf-8");
			int bodySize = resutBytes.length;
			
			
			bm.setCallId(UtilHelper.string2Bytes(32, UtilHelper.appendStr(32, ""+counter+(new Date()).getTime())));
			bm.setDest(UtilHelper.string2Bytes(32, ""));
			bm.setProText(UtilHelper.string2Bytes(8, "DT-MOMP"));
			bm.setSeq(0);
			bm.setSrc(UtilHelper.string2Bytes(32, ""));
			bm.setType(0x1234);
			bm.setVersion(1);
			bm.setBody(resutBytes);
			bm.setBodySize(bodySize);
		} catch (UnsupportedEncodingException e) {
			 
			e.printStackTrace();
		}
		
		 return bm;
	}
}
