package com.paul.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.paul.wechat.bean.menu.Menu;
import com.paul.wechat.utils.HttpsUtils;
import com.paul.wechat.utils.WechatApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @description：菜单服务
 * @author：JBL
 * @date：2018/1/4
 */
@Service
public class MenuService {
    private static Logger logger= LoggerFactory.getLogger( MenuService.class);
    /**
     * 创建自定义菜单
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public  int create(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = WechatApi.menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString( menu );
        // 调用接口创建菜单
        JSONObject jsonObject = HttpsUtils.post(url, jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                logger.error("创建菜单失败:", jsonObject.getIntValue("errcode"));
            }
        }

        return result;
    }

    /**
     * 查询自定义菜单
     */
    public JSONObject  get(String accessToken) throws Exception {
        // 拼装创建菜单的url
        String url = WechatApi.menu_get_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsUtils.get(url);
        return jsonObject;
    }

    /**
     * 删除自定义菜单
     */
    public JSONObject  delete(String accessToken) throws Exception {
        String url = WechatApi.menu_delete_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsUtils.get(url);
        return jsonObject;
    }
}
