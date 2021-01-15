package com.data.itsv.netty.server;

import com.data.itsv.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class nettyServerThread {


    public nettyServerThread(ApplicationContext applicationContext) {
        this.execuid();
    }

    private void execuid() {


        new Thread(() -> {
            while (true) {
                try {
                    InetSocketAddress address=new InetSocketAddress(Integer.parseInt(Properties.getNettyServerPost()));
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
