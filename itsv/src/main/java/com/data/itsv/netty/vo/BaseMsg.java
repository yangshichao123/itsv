// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseMsg.java

package com.data.itsv.netty.vo;

import lombok.Data;

@Data
public class BaseMsg {
	/**
	 * 说明：ProText：协议标识，8个字节，目前为“DT-MOMP”。
	 *
	 */
	private byte[] proText;

	/**
	 * Type：类型，4个字节，0-信令数据，1-流数据。
	 */
	private int type;
	/**
	 * CallId：呼叫ID，32字节，信令全局ID，应答消息需要返回一样的CallId。
	 */
	private byte[] callId;
	// 5) Seq：报文序号，4个字节，每次都累积加1，应答消息需要返回一样的Seq。
	private int seq;
	// 6) Version：协议版本，4个字节，前2字节为主版本号，后2字节为从版本号，目前版本为1.0。
	private int version;
	// 7) Src：源地址，32个字节，消息发起源的节点编码（目前编码为32位）。
	private byte[] src;
	// 8) Dest：目标地址，32个字节，消息最终目标的节点编码（目前编码为32位）。
	private byte[] dest;
	// 9) BodySize：消息体大小，4个字节，为零时表示无XML数据。
	private int bodySize;

	// 协议体
	private byte[] body;




}