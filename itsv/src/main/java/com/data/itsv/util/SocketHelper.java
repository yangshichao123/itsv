package com.data.itsv.util;

import java.util.Date;

import com.data.itsv.netty.vo.BaseMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SocketHelper { 
	/**
	 * @author ghj
	 * @说明：xml头
	 * */
	
	private static String XMLHEADER = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>";


	public static void sendResponse(ChannelHandlerContext ctx, BaseMsg bm,
									String content) {
		StringBuffer sf = new StringBuffer();
		sf.append(XMLHEADER);
		sf.append(content);
		log.info("回复内容：" + sf.toString());

		if(ctx.channel().remoteAddress()==null){
			log.warn("远程连接已断开，未进行数据发送！"+sf.toString());
			return ;
		}else{
			ctx.write(ProtocolWrapper.wrap(sf.toString(),bm));
		}

		bm = null;
	}
	
	public static BaseMsg getBm(){
		int counter=0;
		BaseMsg bm = new BaseMsg();
		counter++;
		
		
		bm.setCallId(UtilHelper.string2Bytes(32, UtilHelper.appendStr(32, ""+counter+(new Date()).getTime())));
		bm.setDest(UtilHelper.string2Bytes(32, ""));
		bm.setProText(UtilHelper.string2Bytes(8, "DT-MOMP"));
		bm.setSeq(0);
		bm.setSrc(UtilHelper.string2Bytes(32, ""));
		bm.setType(0x1234);
		bm.setVersion(1);
		return bm;
	}
	/**
	 * @author gaohejie
	 * @说明 字符串协议客户端发送信息
	 * */
	public static void sendUserClientMsg(ChannelHandlerContext ctx,
			String content) {
		 
		 content=wrapProtocol(content);

		if(ctx.channel().remoteAddress()==null){
			log.info("socket连接异常，发送失败！发送内容：" +content);

		}else{
			log.info("发送成功！Ip="+ctx.channel().remoteAddress()+",发送内容：" + content);
			ctx.write(content);
		}
		
	}


	public static String HEADER = "<BOF>";

	public static String TAIL = "<EOF>";

	public static String wrapProtocol(String content) {
		StringBuffer sb = new StringBuffer();
		sb.append(HEADER);
		sb.append(content);
		sb.append(TAIL);
		return sb.toString();
	}
}
