package com.data.itsv.netty.client;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.data.itsv.netty.ITSV2CMSClientHeartBeatJob;
import com.data.itsv.netty.ProtocolDecoderProcesser;
import com.data.itsv.model.RequestModel;
import com.data.itsv.netty.ProtocolResponseProcesser;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.service.ProtocolService;
import com.data.itsv.service.UserService;
import com.data.itsv.support.SpringContextHolder;
import com.data.itsv.util.Properties;
import com.data.itsv.util.ProtocolWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

/**
 *
 * I/O数据读写处理类
 *
 */
public class BootNettyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter{

    public static Long keepAlivePeriod;
    private UserService serviceNetty;
    public static ChannelHandlerContext ctx;
    public static Boolean boo=false;
    private ByteBuf buf;
    private ProtocolDecoderProcesser protocolDecoderProcesser;
    /**
     * @author ghj
     * @说明：心跳Job
     * */

    public static ITSV2CMSClientHeartBeatJob heartBeatJob = new ITSV2CMSClientHeartBeatJob();

    // 日志记录器
    private static final Logger logger = LogManager.getLogger(BootNettyChannelInboundHandlerAdapter.class);


    public BootNettyChannelInboundHandlerAdapter() {
      //  serviceNetty=(UserService)nettyThread.applicationContext.getBean("ServiceNetty");
    }



    /**
     * 从服务端收到新的数据时，这个方法会在收到消息时被调用
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException
    {
        System.out.println(msg);

        BaseMsg bm=(BaseMsg)msg;
        System.out.printf(bm.toString());
        RequestModel rm=new RequestModel();
        rm.setBm(bm);
        rm.setCtx(ctx);
        protocolDecoderProcesser.process(bm.getBody(),rm);
       // serviceNetty.receiveData(ctx,msg);
      //  System.out.println("channelRead:read msg:"+msg.toString());
        //回应服务端

    }

    /**
     * 从服务端收到新的数据、读取完成时调用
     *
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException
    {
       // System.out.println("channelReadComplete");
        ctx.flush();
    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws IOException
    {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        logger.error( cause.getMessage());
        ctx.close();//抛出异常，断开与客户端的连接
    }

    /**
     * 客户端与服务端第一次建立连接时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception, IOException
    {

        super.channelActive(ctx);

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        logger.error("通道已经连接:"+clientIp+":"+ Properties.getNettyPost());
        /*ByteBuf message = null;
        byte[] req = ("I am client once").getBytes();
        for(int i = 0; i < 5; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            Thread.sleep(5000);
            ctx.writeAndFlush(message);
        }
*/
        Thread.sleep(500);
        BootNettyChannelInboundHandlerAdapter.ctx=ctx;
      //  serviceNetty.sendRegister();
        ProtocolDecoderProcesser shiroFilterFactoryBean = SpringContextHolder.getBean(ProtocolDecoderProcesser.class);
        this.protocolDecoderProcesser=shiroFilterFactoryBean;
        SpringContextHolder.getBean(ProtocolResponseProcesser.class).register2CMS();

    }

    /**
     * 客户端与服务端 断连时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException
    {
        super.channelInactive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close(); //断开连接时，必须关闭，否则造成资源浪费
        ctx=null;
        BootNettyChannelInboundHandlerAdapter.ctx=null;
        BootNettyChannelInboundHandlerAdapter.boo=false;
        logger.error("连接通道已断开："+clientIp+":"+ Properties.getNettyPost());
    }

    /**
     * @author ghj
     * @说明：向CMS服務器发送心跳
     * */
    public static void heartBeat() {
        if(ctx!=null){
            ctx.write(BootNettyChannelInboundHandlerAdapter.keepAlive(keepAlivePeriod+""));
        }

    }
    private static String XMLHEADER = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>";
    public static BaseMsg keepAlive(String keepAlivePeriod) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='KeepAlive'>");
        sf.append("<parameters>");
        sf.append("<keepAlivePeriod>" + keepAlivePeriod + "</keepAlivePeriod>");
        sf.append("</parameters>");
        sf.append("</request>");
        logger.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }


}