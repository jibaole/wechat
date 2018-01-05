package com.paul.wechat.bean.message.resp;

/**
 * @description：音乐消息
 * @author：JBL
 * @date：2018/1/3
 */

public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
