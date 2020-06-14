package com.example.Image;

public class Image {
    private String url;//图片网络地址
    private String size;//图片分辨率

    public Image() {
    }

    public Image(String url, String size) {
        this.url = url;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
