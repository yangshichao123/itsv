package com.data.itsv.task;

import com.data.itsv.netty.client.nettyThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class taskThread {
    static ApplicationContext applicationContext; // Spring应用上下文
  //  private Service service;
   // private ServiceNetty serviceNetty;
    // 日志记录器
    private static final Logger logger = LogManager.getLogger(nettyThread.class);


    public taskThread(ApplicationContext applicationContext) {
        taskThread.applicationContext = applicationContext;
     //   service = (Service) applicationContext.getBean("Service");
      //  serviceNetty = (ServiceNetty) applicationContext.getBean("ServiceNetty");
        this.execuid();
    }

    private void execuid() {


        new Thread(() -> {
          /*  while (true) {
                GwaqscJxglServicePortType portType = null;
                try {
                    FZMap.clientTokenLock.readLock().lock();
                    portType = (GwaqscJxglServicePortType) FZMap.clientToken.get("GW");

                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    FZMap.clientTokenLock.readLock().unlock();
                }
                if (portType == null) {
                    service.getportType();
                }

                logger.info("---------------获取公务连接信息---------------------");
                *//*try {
                    long l=1000;
                    Thread.sleep(l);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }*//*
            }*/
        }).start();

    }

}
