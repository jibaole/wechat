package com.paul.wechat.bean.menu;

/**
 * @description：父菜单项 :包含有二级菜单项的一级菜单。
 * 这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 * @author：JBL
 * @date：2018/1/4
 */
public class ComplexButton extends Button {

    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}