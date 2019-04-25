package com.bosssoft.bigdata.manager.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.bosssoft.bigdata.manager.framework.utils.Constants;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author LCN on 2019/8/7
 */
@Component
public class ApplicationStartListener implements ApplicationListener<WebServerInitializedEvent> {


	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		int serverPort = event.getWebServer().getPort();
		String ip = getIp();
		Constants.address = ip + ":" + serverPort;
	}


	private String getIp() {
		String host = null;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return host;
	}
}
