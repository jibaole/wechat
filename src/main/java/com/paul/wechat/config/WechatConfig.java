package com.paul.wechat.config;

import com.alibaba.fastjson.JSONObject;
import com.paul.wechat.controller.BaseController;
import com.paul.wechat.utils.HttpsUtils;
import com.paul.wechat.utils.SignUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description：读取微信appid和secret的配置文件
 * @author：JBL
 * @date：2018/1/4
 */
@Component
@ConfigurationProperties(prefix = "weixin")
public class WechatConfig extends BaseController {

    public String appid;

    private String secret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    /**
     * 方法名：getWxConfig</br>
     * 详述：获取微信的配置信息 </br>
     * 开发人员：souvc  </br>
     * 创建时间：2016-1-5  </br>
     * @param request
     * @return 说明返回值含义
     */
    public Map<String, Object> getWxConfig(HttpServletRequest request) {
        Map<String, Object> ret = new HashMap<String, Object>();

        String requestUrl = request.getRequestURL().toString();
        String jsapi_ticket = "";
        /** 必填，生成签名的时间戳*/
        String timestamp = Long.toString( System.currentTimeMillis() / 1000 );
        /**必填，生成签名的随机串*/
        String nonceStr = UUID.randomUUID().toString();
        String signature = "";

        String  url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi";
        JSONObject json = HttpsUtils.get( url );
        if (json != null) {
            jsapi_ticket = json.getString( "ticket" );
        }
        //注意这里参数名必须全部小写，且必须有序
        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + requestUrl;
        try{
            MessageDigest crypt = MessageDigest.getInstance( "SHA-1" );
            crypt.reset();
            crypt.update( sign.getBytes( "UTF-8" ) );
            signature = SignUtil.byteToHex( crypt.digest() );
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        ret.put( "appId", appid );
        ret.put( "timestamp", timestamp );
        ret.put( "nonceStr", nonceStr );
        ret.put( "signature", signature );
        return ret;
    }
}
