package com.data.itsv.netty.client;

import com.data.itsv.netty.vo.BaseMsg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 通道初始化
 *
 */
public class BootNettyChannelInitializer<SocketChannel> extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        // ChannelOutboundHandler，依照逆序执行（从下往上）
        ch.pipeline().addLast("encoder", new MyEncoder());
        // 属于ChannelInboundHandler，依照顺序执行（从上往下）
        ch.pipeline().addLast("decoder", new MyDecoder(new BaseMsg()));
        //ch.pipeline().addLast("decoder", new StringDecoder());
        /**
         * 自定义ChannelInboundHandlerAdapter
         */
        ch.pipeline().addLast(new BootNettyChannelInboundHandlerAdapter());
      //  ch.pipeline().addLast(new IdleStateHandler(61, 30, 0, TimeUnit.SECONDS));
    }

}