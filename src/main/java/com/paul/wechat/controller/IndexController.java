package com.paul.wechat.controller;

import com.paul.wechat.config.WechatConfig;
import com.paul.wechat.service.CoreService;
import com.paul.wechat.service.MenuService;
import com.paul.wechat.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @description：快知识-内容
 * @author：JBL
 * @date：2017/12/12
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private  CoreService coreService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        Map<String, Object> config = wechatConfig.getWxConfig( request );
        model.addAttribute( "appId", config.get( "appId" ) );
        model.addAttribute( "timestamp", config.get( "timestamp" )  );
        model.addAttribute( "nonceStr", config.get( "nonceStr" )  );
        model.addAttribute( "signature", config.get( "signature" )  );
        return "/index";
    }

    /**
     * 微信校验
     * @return
     */
    @RequestMapping(value = "/checked1", method = RequestMethod.POST)
    public void index(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("签名校验通过!");
            out.print(echostr);
        }else {
            System.out.println("签名校验失败!");
        }
        // 调用核心业务类接收消息、处理消息
        String respXml = coreService.processRequest(request);
        // 响应消息
        out.print(respXml);
        out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
