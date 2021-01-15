package com.data.itsv.netty.client;


import com.alibaba.fastjson.JSON;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.util.CRCUtil;
import com.data.itsv.util.UtilHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MyDecoder<T> extends ByteToMessageDecoder {
    private T target;
    private int BASE_LENGTH = 120;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        /**
         * 说明：ProText：协议标识，8个字节，目前为“DT-MOMP”。
         *
         */
        byte[] proText;

        /**
         * Type：类型，4个字节，0-信令数据，1-流数据。
         */
        int type;
        /**
         * CallId：呼叫ID，32字节，信令全局ID，应答消息需要返回一样的CallId。
         */
        byte[] callId = new byte[32];
        /**
         * Seq：报文序号，4个字节，每次都累积加1，应答消息需要返回一样的Seq。
         *
         */
        int seq;
        /**
         * 6) Version：协议版本，4个字节，前2字节为主版本号，后2字节为从版本号，目前版本为1.0。
         *
         */
        int version = 0;
        /**
         * 7) Src：源地址，32个字节，消息发起源的节点编码（目前编码为20位）。
         *
         */
        byte[] src = new byte[32];
        /**
         * 8) Dest：目标地址，32个字节，消息最终目标的节点编码（目前编码为20位）。
         *
         */
        byte[] dest = new byte[32];
        /**
         * 9) BodySize：消息体大小，4个字节，为零时表示无XML数据。
         *
         */
        int bodySize;

        /**
         * 协议体
         *
         */
        byte[] body;

        //创建字节数组,buffer.readableBytes可读字节长度
        // 可读长度必须大于基本长度
        if (buffer.readableBytes() >= BASE_LENGTH) {
            // 防止socket字节流攻击
            // 防止，客户端传来的数据过大
            // 因为，太大的数据，是不合理的
            if (buffer.readableBytes() > 2048) {
                buffer.skipBytes(buffer.readableBytes());
            }

            // 记录包头开始的index
            int beginReader;

            while (true) {
                proText = new byte[8];
                // 获取包头开始的index
                beginReader = buffer.readerIndex();
                // 标记包头开始的index
                buffer.markReaderIndex();
                buffer.readBytes(proText);
                // 读到了协议的开始标志，结束while循环
                if ("DT-MOMP".equals(UtilHelper.bytes2String(proText)
                        .substring(0, 7))) {
                    break;
                }

                // 未读到包头，略过一个字节
                // 每次略过，一个字节，去读取，包头信息的开始标记
                buffer.resetReaderIndex();
                buffer.readByte();

                // 当略过，一个字节之后，
                // 数据包的长度，又变得不满足
                // 此时，应该结束。等待后面的数据到达
                if (buffer.readableBytes() < BASE_LENGTH) {
                    return;
                }
            }
            BaseMsg baseMsg = null;
            if (target instanceof BaseMsg)
                baseMsg = (BaseMsg) target;
            if (baseMsg == null)
                return;
            baseMsg.setProText(proText);
            System.out.println(UtilHelper.bytes2String(proText));

            byte[] tmp = new byte[4];
            buffer.readBytes(tmp);
            type= UtilHelper.hBytesToInt(tmp);
            baseMsg.setType(type);
            buffer.readBytes(tmp);
            bodySize = UtilHelper.hBytesToInt(tmp);
            baseMsg.setBodySize(bodySize);

            buffer.readBytes(tmp);
            seq = UtilHelper.hBytesToInt(tmp);
            baseMsg.setSeq(seq);

            buffer.readBytes(tmp);
            version= UtilHelper.bytesToInt(tmp);
            baseMsg.setVersion(version);

            buffer.readBytes(callId);
            baseMsg.setCallId(callId);

            buffer.readBytes(src);
            baseMsg.setSrc(src);

            buffer.readBytes(dest);
            baseMsg.setDest(dest);

            body =new byte[bodySize];
            buffer.readBytes(body);
            baseMsg.setBody(body);
            out.add(baseMsg);
        }

    }

    public MyDecoder(T target) {
        this.target = target;
    }

    public MyDecoder() {
    }

    public String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static String toHexString1(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }
}
