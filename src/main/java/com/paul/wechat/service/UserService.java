package com.paul.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.paul.wechat.bean.Oauth2Token;
import com.paul.wechat.bean.SNSUser;
import com.paul.wechat.bean.User;
import com.paul.wechat.task.AccessTokenTask;
import com.paul.wechat.utils.HttpsUtils;
import com.paul.wechat.utils.WechatApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @description：
 * @author：JBL
 * @date：2018/1/4
 */
@Service
public class UserService {
    private static Logger logger= LoggerFactory.getLogger( AccessTokenTask.class);

    /**
     * 获取用户信息
     *
     * @param accessToken 接口访问凭证
     * @param openId 用户标识
     * @return WeixinUserInfo
     */
    public  User getUser(String accessToken, String openId) {
        User user = null;
        // 拼接请求地址
        String requestUrl = WechatApi.user_url;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = HttpsUtils.get( requestUrl );

        if (null != jsonObject) {
            try {
                user = new User();
                // 用户的标识
                user.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                user.setSubscribe(jsonObject.getIntValue("subscribe"));
                // 用户关注时间
                user.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                user.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                user.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                user.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                user.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                user.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                user.setLanguage(jsonObject.getString("language"));
                // 用户头像
                user.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == user.getSubscribe()) {
                    logger.error("用户{}已取消关注", user.getOpenId());
                } else {
                    int errorCode = jsonObject.getIntValue("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }
        return user;
    }

    /**
     * 获取网页授权凭证
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public  Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        Oauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = HttpsUtils.get( requestUrl );
        if (null != jsonObject) {
            try {
                wat = new Oauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getIntValue("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    /**
     * 通过网页授权获取用户信息
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUser
     */
    public  SNSUser getSNSUser(String accessToken, String openId) {
        SNSUser snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = WechatApi.snsuser_url;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = HttpsUtils.get( requestUrl );

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUser();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                //snsUserInfo.setPrivilegeList( (List<String>) JSON.parseObject(jsonObject.getString("privilege")) );
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getIntValue("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }
}
