package com.data.itsv.netty;

import com.data.itsv.model.ProtocolRequestModel;
import com.data.itsv.model.UserKeepAliveRequestModel;
import com.data.itsv.model.UserOrderAlarmRequestModel;
import com.data.itsv.model.UserRegisterRequestModel;
import com.data.itsv.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ghj
 * @说明 协议处理器
 * */
@Component
public class UserClientProtocolRequestProcesser {

	@Resource
	private UserClientProtocolDecoder userClientProtocolDecoder;
	@Resource
	private UserService userService;


	public  void process(ChannelHandlerContext session, String data) {
		ProtocolRequestModel prm = userClientProtocolDecoder.process(data);

		// 用户注册信息
		if (prm instanceof UserRegisterRequestModel) {
			userService.userRegister(session, ((UserRegisterRequestModel) prm)
					.getUserId());

			return;
		}
		// 用户保持心跳信息
		else if (prm instanceof UserKeepAliveRequestModel) {
			userService.userKeepAlive(session, ((UserKeepAliveRequestModel) prm)
					.getUserId());

			return;
		}
		  
		   //订阅告警信息类
		else if (prm instanceof UserOrderAlarmRequestModel) {
			userService.orderAlarmData(session,((UserOrderAlarmRequestModel) prm)
						.getUserId());
				return;
			} 
	}
}
