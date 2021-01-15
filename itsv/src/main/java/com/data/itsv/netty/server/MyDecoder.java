package com.data.itsv.netty.server;


import com.data.itsv.netty.UserClientMsgProtocolModel;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.util.UtilHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class MyDecoder<T> extends ByteToMessageDecoder {
    private T target;
    private int BASE_LENGTH = 120;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        int i = buffer.readerIndex();
        String ms = buffer.toString(CharsetUtil.UTF_8);
        String substring="";
        if (ms.contains(UserClientMsgProtocolModel.HEADER) && ms.contains(UserClientMsgProtocolModel.TAIL)) {
            substring = ms.substring(ms.indexOf(UserClientMsgProtocolModel.HEADER) , ms.indexOf(UserClientMsgProtocolModel.TAIL)+5);
            byte[] bytes=substring.getBytes();
            buffer.readerIndex(i+bytes.length);

        }else{
            return;
        }

        out.add(substring);
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
