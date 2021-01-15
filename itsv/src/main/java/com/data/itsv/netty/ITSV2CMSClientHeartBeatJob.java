package com.data.itsv.netty;


import com.data.itsv.netty.client.BootNettyChannelInboundHandlerAdapter;

/**
 * @author ghj
 * @说明：日志处理JOb
 * */
public class ITSV2CMSClientHeartBeatJob extends Thread {
	public ITSV2CMSClientHeartBeatJob() {
		 
		this.start();
	}
	/**
	 * @author ghj
	 * @说明：发送心跳启动标志，true启动，false停止
	 * */
    public static boolean heartBeatFlag=false;
    
	public void run() {
		
			while (true) {
				try {
				if(heartBeatFlag){
					BootNettyChannelInboundHandlerAdapter.heartBeat();
				}
				
				Thread.sleep(BootNettyChannelInboundHandlerAdapter.keepAlivePeriod*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
	}

	 
}
