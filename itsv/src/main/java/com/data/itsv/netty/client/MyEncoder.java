package com.data.itsv.netty.client;

import com.data.itsv.netty.vo.BaseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<BaseMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(baseMsg.getProText());
        byteBuf.writeInt(baseMsg.getType());
        byteBuf.writeInt(baseMsg.getBodySize());
        byteBuf.writeInt(baseMsg.getSeq());
        byteBuf.writeInt(baseMsg.getVersion());
        byteBuf.writeBytes(baseMsg.getCallId());
        byteBuf.writeBytes(baseMsg.getSrc());
        byteBuf.writeBytes(baseMsg.getDest());
        byteBuf.writeBytes(baseMsg.getBody());

    }
}
