package com.kanshan.tea_classify.entity;

public class Tea {
    int label;
    String name;
    String description;
    String url;

    public Tea(int label, String name, String description, String url) {
        this.label = label;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
