package com.paul.wechat.bean.message.resp;

/**
 * @description：回复文本消息
 * @author：JBL
 * @date：2018/1/3
 */

public class TextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
