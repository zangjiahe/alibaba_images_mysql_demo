package com.example.controller;

import com.example.Image.Image;
import com.example.service.ImageService;
import com.example.service.Impl.ImageServiceImpl;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class GetImages {
    public static void main(String[] args) throws Exception {
        GetImages getImages = new GetImages();
        for (int i = 22701; i>0; i--) {
            getImages.getImg("http://www.netbian.com/desk/" + i + ".htm");
        }
    }

    public void getImg(String url) throws Exception {
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements links = document.getElementsByClass("pic");
        //获取第一步非高清图页面
        String imgUrl = "http://www.netbian.com" + getSubString(links.toString(), "<p><a href=\"", "\"");
        String size = getSubString(imgUrl, "-", ".htm");
        //获取高清图地址
        connect = Jsoup.connect(imgUrl);
        Document imgDocument = connect.get();
        Element imgElement = imgDocument.getElementById("endimg");
        Elements img = imgElement.getElementsByTag("img").eq(0);
        String finalUrl = img.attr("src");//getSubString(imgElement.toString(), "<a href=\"", "\"");
        String endWith = finalUrl.substring(finalUrl.lastIndexOf("."));
        String fileName = "\\" + new Date().getTime() + (int) Math.random() + "";
        Image image=new Image();
        image.setSize(size);
        image.setUrl(finalUrl);
        svae2Mysql(image);
        download(finalUrl, endWith, fileName);
        System.out.println("图片地址：" + finalUrl);
    }

    public String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }

    public void download(String url, String endWith, String fileName) throws Exception {

        File file = new File("d:/img2/");//地址改成自己本地的文件目录
        System.out.println(file.getPath() + fileName + endWith);
        if (!file.exists()) {
            file.mkdir();
        } else {
            file.delete();
            file.mkdir();
        }
        URL url2 = new URL(url);
        InputStream is = url2.openConnection().getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getPath() + fileName + endWith));
        byte[] bs = new byte[2048];
        int len;
        while ((len = is.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        if (is != null) is.close();
        if (bos != null) bos.close();
    }

    public void svae2Mysql(Image image){
        ImageService imageService=new ImageServiceImpl();
        imageService.save(image);
    }
}
