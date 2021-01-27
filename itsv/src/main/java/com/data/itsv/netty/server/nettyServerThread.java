package com.data.itsv.netty.server;

import com.data.itsv.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
@Order(10)
public class nettyServerThread {


    public nettyServerThread() {
        this.execuid();
        System.out.println("nettyServerThread   开始执行");
    }

    private void execuid() {


        new Thread(() -> {
            while (true) {
                try {
                    String nettyServerPost = Properties.getNettyServerPost();
                    InetSocketAddress address=new InetSocketAddress(Integer.parseInt(nettyServerPost));
                    new NettyServer().start(address);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }).start();

    }
}
