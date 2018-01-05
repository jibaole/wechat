package com.paul.wechat.bean.message.req;

/**
 * @description：文本-请求消息
 * @author：JBL
 * @date：2018/1/3
 */

public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}