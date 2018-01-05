package com.paul.wechat.task;

import com.paul.wechat.bean.AccessToken;
import com.paul.wechat.config.WechatConfig;
import com.paul.wechat.controller.BaseController;
import com.paul.wechat.utils.WechatApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * 定时更新accessToken任务
 */
@Component
@EnableScheduling
public class AccessTokenTask {
	private static Logger logger= LoggerFactory.getLogger( AccessTokenTask.class);
	@Autowired
	private  WechatConfig wechatConfig;

	/**
	 * 每1小时执行一次
	 */
	@Scheduled(fixedRate=1000 * 60 * 60 * 1)
	public void getAccessToken() {
		logger.info("into  getAccessToken");
		RestTemplate restTemplate =new RestTemplate();
		String  tokenUrl= WechatApi.token.replace("APPID", wechatConfig.getAppid()).replace("APPSECRET",wechatConfig.getSecret());
		BaseController.accessToken=restTemplate.getForObject(tokenUrl,AccessToken.class);
		logger.info("access_token:"+BaseController.accessToken.getAccess_token());
		logger.info("expires_in:"+BaseController.accessToken.getExpires_in());
	}
}
