package com.paul.wechat.controller;

import com.paul.wechat.bean.Oauth2Token;
import com.paul.wechat.bean.SNSUser;
import com.paul.wechat.bean.User;
import com.paul.wechat.config.WechatConfig;
import com.paul.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description：自定义菜单-管理
 * @author：JBL
 * @date：2017/12/12
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 获取用户信息
     * @param model
     * @param openId
     * @return
     */
    @RequestMapping(value = "/get/{openId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(Model model, @PathVariable String openId) {
        User result = null;
        try {
            result = userService.getUser( getAccessToken() ,openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
      return renderSuccess(result);
    }



    /**
     * 获取用户信息
     * 参考地址：http://www.cnblogs.com/liuhongfeng/p/5099149.html
     * @param model
     * @return
     */
    @RequestMapping(value = "/checked", method = RequestMethod.GET)
    public String getOauth2(Model model, HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        User result = null;
        try {
            Oauth2Token accessToken = userService.getOauth2AccessToken( wechatConfig.getAppid(), wechatConfig.getSecret(), code );
            // 用户标识
            String openId = accessToken.getOpenId();
            /**
             * 获取用户信息(获取)
             * 通过code换取网页授权access_token（与基础支持中的access_token不同）
             */
            SNSUser snsUser = userService.getSNSUser(accessToken.getAccessToken(), openId);
            model.addAttribute( "snsUser", snsUser);
            model.addAttribute( "state",state );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/snsUser";
    }


}
