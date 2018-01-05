package com.paul.wechat.bean.message.resp;

/**
 * @description：语音消息
 * @author：JBL
 * @date：2018/1/3
 */

public class VoiceMessage extends BaseMessage {
    /**
     * 语音
     */
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
