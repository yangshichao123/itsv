package com.data.itsv.model;


/**
 * OSD信息
 *
 * @author gaohe
 * */
public class OSDModel extends BaseModel {
    /**
     * 是否隐藏标志 0显示，1隐藏
     *
     * @author gaohe
     * */
    private int enableHide;

    /**
     * 是否隐藏标志 0显示，1隐藏
     *
     * @author gaohe
     * */
    private String enableHides;
    /**
     * 显示位置的x坐标
     *
     * @author gaohe
     * */
    private int x;
    /**
     * 显示位置的y坐标
     *
     * @author gaohe
     * */
    private int y;
    /**
     * 显示内容
     *
     * @author gaohe
     * */
    private String content;


    public int getEnableHide() {
        return enableHide;
    }

    public void setEnableHide(int enableHide) {
        this.enableHide = enableHide;
        if (enableHide == 0) {
            this.enableHides = "显示";
        } else {
            this.enableHides = "隐藏";
        }
    }

    public String getEnableHides() {
        return enableHides;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
