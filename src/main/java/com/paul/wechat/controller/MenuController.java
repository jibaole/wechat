package com.paul.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.paul.wechat.bean.menu.Button;
import com.paul.wechat.bean.menu.CommonButton;
import com.paul.wechat.bean.menu.ComplexButton;
import com.paul.wechat.bean.menu.Menu;
import com.paul.wechat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description：自定义菜单-管理
 * @author：JBL
 * @date：2017/12/12
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(Model model, HttpServletRequest request, HttpServletResponse response) {
        int result =menuService.create(getMenu(),getAccessToken());
        // 判断菜单创建结果
        if (0 == result) {
            System.out.println( "菜单创建成功！" );
        }else{
            System.out.println("菜单创建失败，错误码：" + result);
    }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Object get(Model model) {
        JSONObject result = null;
        try {
            result = menuService.get(getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
      return renderSuccess(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(Model model) {
        JSONObject result = null;
        try {
            result = menuService.delete(getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return renderSuccess(result);
    }



    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("weather");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("busserach");

        CommonButton btn13 = new CommonButton();
        btn13.setName("周边搜索");
        btn13.setType("view");
        btn13.setUrl( "http://www.soso.com/" );

        CommonButton btn14 = new CommonButton();
        btn14.setName("拍照功能");
        btn14.setType("pic_sysphoto");
        btn14.setKey("24");



        CommonButton btn22 = new CommonButton();
        btn22.setName("扫码带提示");
        btn22.setType("scancode_push");
        btn22.setKey("rselfmenu_0_0");

        CommonButton btn23 = new CommonButton();
        btn23.setName("美女电台");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("人脸识别");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("聊天唠嗑");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q友圈");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("电影排行榜");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("幽默笑话");
        btn33.setType("click");
        btn33.setKey("33");


        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13,btn14 });


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("扫一扫");
        mainBtn2.setSub_button(new CommonButton[] {  btn22, btn23, btn24, btn25 });


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });


        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
}
