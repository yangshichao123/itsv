package com.data.itsv.model;


import com.data.itsv.netty.vo.BaseMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * @author ghj
 * @说明：请求类
 * */
@Data
public class RequestModel {
	/**
	 * @author ghj
	 * @说明：请求内容
	 * */
	 private BaseMsg bm;
	/**
	 * @author ghj
	 * @说明：会话
	 * */
	 private ChannelHandlerContext ctx ;

	 
}
