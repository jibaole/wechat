package com.paul.wechat.utils;

/**
 * @description：微信API常量类
 * @author：JBL
 * @date：2018/1/4
 */

public class WechatApi {

    /**获取access token*/
    public  static  String  token="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**###########菜单管理#############*/

    /**菜单创建（POST） 限100（次/天）*/
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**获取菜单（get） 限100（次/天）*/
    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    /**删除菜单（get） 限100（次/天）*/
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**###########用户管理############*/
    /**权获取用户信息*/
     public static String user_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
    /**通过网页授权获取用户信息*/
    public static String snsuser_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
}

