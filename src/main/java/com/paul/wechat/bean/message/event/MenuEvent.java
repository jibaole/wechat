package com.paul.wechat.bean.message.event;

/**
 * @description：自定义菜单事件
 * @author：JBL
 * @date：2018/1/3
 */

public class MenuEvent  extends BaseEvent {
    // 事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
