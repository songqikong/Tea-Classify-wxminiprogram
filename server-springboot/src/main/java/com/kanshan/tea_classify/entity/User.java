package com.kanshan.tea_classify.entity;

public class User {
    String openid;
    String ava_src;
    String name;
    int group;

    public User(String openid, String ava_src, String name, int group) {
        this.openid = openid;
        this.ava_src = ava_src;
        this.name = name;
        this.group = group;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAva_src() {
        return ava_src;
    }

    public void setAva_src(String ava_src) {
        this.ava_src = ava_src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
