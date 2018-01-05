package com.paul.wechat.bean.message.req;

/**
 * @description：视频-请求消息
 * @author：JBL
 * @date：2018/1/3
 */

public class VideoMessage extends BaseMessage{

    /**
     * 媒体ID
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    public String getThumbMediaId() {
        return ThumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }



}
