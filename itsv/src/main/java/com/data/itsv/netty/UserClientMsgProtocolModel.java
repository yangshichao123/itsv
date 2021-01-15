package com.data.itsv.netty;

public class UserClientMsgProtocolModel {
	 
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
