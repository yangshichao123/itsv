package com.data.itsv.netty;

import com.data.itsv.model.ProtocolRequestModel;
import com.data.itsv.model.UserKeepAliveRequestModel;
import com.data.itsv.model.UserOrderAlarmRequestModel;
import com.data.itsv.model.UserRegisterRequestModel;
import org.springframework.stereotype.Component;

/**
 * @author ghj
 * @说明 协议处理器
 * */
@Component
public class UserClientProtocolDecoder {

	public ProtocolRequestModel process(String data) {
		ProtocolRequestModel prm = null;
		// 解析命令
		String[] ds = data.split(";");
		if (ds.length > 0) {

			String cmd = ds[0];
			// 用户注册
			if (cmd.trim().equalsIgnoreCase("UserRegister")) {
				prm = new UserRegisterRequestModel();
				String userId = ds[1];

				((UserRegisterRequestModel) prm).setUserId(userId);

			}
			// 用户保持心跳
			else if (cmd.trim().equalsIgnoreCase("UserKeepAlive")) {
				prm = new UserKeepAliveRequestModel();
				String userId = ds[1];

				((UserKeepAliveRequestModel) prm).setUserId(userId);

			}
			// 告警信息订阅
			else if (cmd.trim().equalsIgnoreCase("UserOrderAlarm")) {
				prm = new UserOrderAlarmRequestModel();
				String userId = ds[1];

				((UserOrderAlarmRequestModel) prm).setUserId(userId);

			}
		}

		return prm;
	}

}
