package com.kanshan.tea_classify.entity;

public class Record {
    int id;
    String labels;
    String img_src;
    String openid;

    public Record(int id, String labels, String img_src, String openid) {
        this.id = id;
        this.labels = labels;
        this.img_src = img_src;
        this.openid = openid;
    }

    public Record(String labels, String img_src, String openid) {
        this.labels = labels;
        this.img_src = img_src;
        this.openid = openid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "Record: id = "+ id + ", labels = " + labels + ", img_src = "+ img_src + ", openid = "+ openid;
    }
}
