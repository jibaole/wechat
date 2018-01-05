package com.paul.wechat.service;

import com.paul.wechat.bean.message.resp.TextMessage;
import com.paul.wechat.utils.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description：
 * @author：JBL
 * @date：2018/1/4
 */
@Service
public class CoreService {
    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return xml
     */
    public String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml( request );
            // 发送方帐号
            String fromUserName = requestMap.get( "FromUserName" );
            // 开发者微信号
            String toUserName = requestMap.get( "ToUserName" );
            // 消息类型
            String msgType = requestMap.get( "MsgType" );

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName( fromUserName );
            textMessage.setFromUserName( toUserName );
            textMessage.setCreateTime( System.currentTimeMillis() );
            textMessage.setMsgType( MessageUtil.RESP_MESSAGE_TYPE_TEXT );

            // 文本消息
            if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_TEXT )) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_IMAGE )) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_VOICE )) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_VIDEO )) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO )) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_LOCATION )) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_LINK )) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_EVENT )) {
                // 事件类型
                String eventType = requestMap.get( "Event" );
                // 关注
                if (eventType.equals( MessageUtil.EVENT_TYPE_SUBSCRIBE )) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals( MessageUtil.EVENT_TYPE_UNSUBSCRIBE )) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals( MessageUtil.EVENT_TYPE_SCAN )) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals( MessageUtil.EVENT_TYPE_LOCATION )) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals( MessageUtil.EVENT_TYPE_CLICK )) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get( "EventKey" );
                    switch (eventKey) {
                        case "weather":
                            respContent = "谢谢您点击：天气预报!";
                            break;
                        case "busserach":
                            respContent = "谢谢您点击：公交查询!";
                            break;
                        default:
                            System.out.println( "default" );
                            break;
                    }

                }
            }
            // 设置文本消息的内容
            textMessage.setContent( respContent );
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml( textMessage );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
}