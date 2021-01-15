package com.data.itsv.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Properties {

    /**
     * nettyHost url
     */
    private static String nettyHost;
    /**
     * nettyPost 端口
     */
    private static String nettyPost;
    /**
     * nettyPost 端口
     */
    private static String nettyServerPost;



    public static String getNettyHost() {
        return nettyHost;
    }

    @Value("${netty.client.host}")
    public void setNettyHost(String nettyHost) {
        Properties.nettyHost = nettyHost;
    }

    public static String getNettyPost() {
        return nettyPost;
    }

    @Value("${netty.client.port}")
    public void setNettyPost(String nettyPost) {
        Properties.nettyPost = nettyPost;
    }

    public static String getNettyServerPost() {
        return nettyServerPost;
    }
    @Value("${netty.server.port}")
    public  void setNettyServerPost(String nettyServerPost) {
        Properties.nettyServerPost = nettyServerPost;
    }
}
