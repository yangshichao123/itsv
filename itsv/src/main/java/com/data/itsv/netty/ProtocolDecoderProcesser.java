package com.data.itsv.netty;

import com.data.itsv.model.RequestModel;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.concurrent.locks.ReentrantLock;




/**
 * @author ghj
 * @说明：协议处理器
 * */
@Component
public class ProtocolDecoderProcesser {
	@Resource
	private ProtocolRequestProcesser protocolRequestProcesser;
	@Resource
	private ProtocolResponseProcesser protocolResponseProcesser;
	// 加锁器
	private  ReentrantLock lock = new ReentrantLock();

	private  SAXBuilder saxBuilder = new SAXBuilder();

	public  void process(byte byteXml[], RequestModel rm) {
		// 构造xml
		Document doc = getDoc(byteXml);
		if (doc != null) {
			// 解析xml
			Element rootEle = doc.getRootElement();
			if (rootEle != null
					&& rootEle.getName().trim().equalsIgnoreCase("request")) {
				protocolRequestProcesser.process(byteXml, rm);
			}else if(rootEle != null
					&& rootEle.getName().trim().equalsIgnoreCase("response")){

				protocolResponseProcesser.process(byteXml, rm);
			}
		}

	}

	public  Document getDoc(byte byteXml[]) {
		Document ret = null;

		try {
			lock.lock();
			ret = saxBuilder.build(new ByteArrayInputStream(byteXml));

		} catch (Exception e) {
			e.printStackTrace();
		}

		lock.unlock();
		return ret;
	}

}
