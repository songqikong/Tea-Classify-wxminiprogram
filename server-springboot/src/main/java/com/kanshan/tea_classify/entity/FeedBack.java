package com.kanshan.tea_classify.entity;

public class FeedBack {
    int id;
    String openid;
    String img_src;
    String label;
    int permission;

    public FeedBack(int id, String openid, String img_src, String label, int permission) {
        this.id = id;
        this.openid = openid;
        this.img_src = img_src;
        this.label = label;
        this.permission = permission;
    }

    public FeedBack(String openid, String img_src, String label, int permission) {
        this.openid = openid;
        this.img_src = img_src;
        this.label = label;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
