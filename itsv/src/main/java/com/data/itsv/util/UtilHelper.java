package com.data.itsv.util;

import java.io.UnsupportedEncodingException;

/**
 * @author Administrator 说明：公共帮助类
 * */
public class UtilHelper {
	/**
	 * 说明：客户端和服务保持心跳周期
	 * */
	public static int HEART_BEAT_CYCLE_TIME=120;
	/**
	 * 说明：私有化构造方法
	 * */
	private UtilHelper() {

	}

	/**
	 * @说明：字符串转成Byte[]数据
	 * @param aimLength
	 *            :目标长度，sourceStr:源字符串
	 * 
	 * */
	public static byte[] string2Bytes(int aimLength, String sourceStr) {
		byte[] temp = sourceStr.getBytes();
		byte[] aimTemp = new byte[aimLength];
		// 将源字符串数组copy到目标byte[]
		for (int i = 0; i < temp.length; i++) {
			aimTemp[i] = temp[i];
		}
		// 目标长度与源字符串长度差
		int tempLength = aimLength - temp.length;
		if (tempLength > 0) {

			// 字符串不夠需要的長度將用0补齐
			for (int i = 0; i < tempLength; i++) {
				byte tempByte = 1;
//				aimTemp[temp.length + i] = tempByte;
			}
		}
		return aimTemp;
	}

	/**
	 * @说明：字符串转成Byte[]数据
	 * @param aimLength
	 *            :目标长度，sourceStr:源字符串
	 * 
	 * */
	public static String bytes2String(byte[] source) {
		String result = null;
		try {
			result = new String(source, "gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @说明：字符串转成Byte[]数据
	 * @param aimLength
	 *            :目标长度，sourceStr:源字符串
	 * 
	 * */
	public static String bytes2String2(byte[] source) {
		String result = null;
		try {
			result = new String(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @说明：比较两个字符串，相应字符进行与计算，取得新值
	 * @param str1
	 *            字符串长度为8, str2 字符串长度为8
	 * @return int
	 */
	public static String compareByteOr(String str1, String str2) {
		String result = "";
		if (str1.trim().length() == str2.trim().length()) {
			// 第一位
			char temp1 = str1.charAt(0);
			char temp2 = str2.charAt(0);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
			// 第二位
			temp1 = str1.charAt(1);
			temp2 = str2.charAt(1);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
			// 第三位
			temp1 = str1.charAt(2);
			temp2 = str2.charAt(2);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}

			// 第四位
			temp1 = str1.charAt(3);
			temp2 = str2.charAt(3);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
			// 第五位
			temp1 = str1.charAt(4);
			temp2 = str2.charAt(4);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
			// 第六位
			temp1 = str1.charAt(5);
			temp2 = str2.charAt(5);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
			// 第七位
			temp1 = str1.charAt(6);
			temp2 = str2.charAt(6);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
			// 第八位
			temp1 = str1.charAt(7);
			temp2 = str2.charAt(7);
			if ((temp1 == '1') || (temp2 == '1')) {
				result = result + "1";
			} else {
				result = result + "0";

			}
		}

		return result;
	}
	/**
	 * 将高字节数组转换为int
	 * 
	 * @param b
	 *            byte[]
	 * @return int
	 */
	public static int hBytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[i] >= 0) {
				s = s + b[i];
			} else {
				s = s + 256 + b[i];
			}
			s = s * 256;
		}
		if (b[3] >= 0) {
			s = s + b[3];
		} else {
			s = s + 256 + b[3];
		}
		return s;
	}

	/**
	 * 将低字节数组转换为int
	 * 
	 * @param b
	 *            byte[]
	 * @return int
	 */
	public static int lBytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[3 - i] >= 0) {
				s = s + b[3 - i];
			} else {
				s = s + 256 + b[3 - i];
			}
			s = s * 256;
		}
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		return s;
	}
	public static void main(String[] a) {
//	  UtilHelper.string2Bytes(9, "DT-MOMP");
//	   System.out.println(UtilHelper.bytes2String( UtilHelper.string2Bytes(7, "DT-MOMP")));
		System.out.println(appendStr(3,"q"));
	}
	 

	 
	/**
	 * @说明：字符串补位
	 * @param aimLength
	 *            :目标长度，sourceStr:源字符串
	 * 
	 * */
	public static String appendStr(int aimLength, String sourceStr) {
		String result = sourceStr;
		 for(int i=0;i<aimLength-sourceStr.length();i++){
			 result=result+"\0";
		 }
		return result;
	}

	/**
	 * 将整数转换为byte数组并指定长度
	 */
	public static byte[] intToBytes(int a, int length) {
		byte[] bs = new byte[length];
		for (int i = bs.length - 1; i >= 0; i--) {
			bs[i] = (byte) (a % 0xFF);
			a = a / 0xFF;
		}
		return bs;
	}

	/**
	 * 将byte数组转换为整数
	 */
	public static int bytesToInt(byte[] bs) {
		int a = 0;
		for (int i = bs.length - 1; i >= 0; i--) {
			a += bs[i] * Math.pow(0xFF, bs.length - i - 1);
		}
		return a;
	}
}
