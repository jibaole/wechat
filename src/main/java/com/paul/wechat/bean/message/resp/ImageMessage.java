package com.paul.wechat.bean.message.resp;

/**
 * @description： 回复图片消息
 * @author：JBL
 * @date：2018/1/3
 */

public class ImageMessage extends BaseMessage {

    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
