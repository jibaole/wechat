package com.paul.wechat.bean.message.req;

/**
 * @description：图片-请求消息
 * @author：JBL
 * @date：2018/1/3
 */

public class ImageMessage extends BaseMessage {
    /**
     * 图片链接
     */
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}
