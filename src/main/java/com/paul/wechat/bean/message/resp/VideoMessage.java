package com.paul.wechat.bean.message.resp;

/**
 * @description：视频消息
 * @author：JBL
 * @date：2018/1/3
 */

public class VideoMessage extends BaseMessage {
    /**
     * 视频
     */
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
