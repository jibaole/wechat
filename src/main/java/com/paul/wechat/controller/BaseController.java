package com.paul.wechat.controller;


import com.paul.wechat.bean.AccessToken;
import com.paul.wechat.utils.Result;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description：基础 controller
 * @author：JBL
 * @date：2015/10/1 14:51
 */
public abstract class BaseController {
    public static AccessToken accessToken=new AccessToken();

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
         /*自动转换日期类型的字段格式*/
        binder.registerCustomEditor( Date.class, new CustomDateEditor( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ), true ) );
    }


    /**
     * ajax失败
     *
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg( msg );
        return result;
    }

    /**
     * ajax成功
     *
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess( true );
        return result;
    }

    /**
     * ajax成功
     *
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess( true );
        result.setMsg( msg );
        return result;
    }

    /**
     * ajax成功
     *
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess( true );
        result.setMsg( "请求成功!" );
        result.setObj( obj );
        return result;
    }

    /**
     * 获取access_token
     * @return
     */
    public String getAccessToken(){
        return accessToken.getAccess_token();
    }
}
