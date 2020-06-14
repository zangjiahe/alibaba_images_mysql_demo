package com.example.service.Impl;

import com.example.Image.Image;
import com.example.dao.ImageDao;
import com.example.dao.Impl.ImageDaoImpl;
import com.example.service.ImageService;

public class ImageServiceImpl implements ImageService {
    public void save(Image image) {
        ImageDao imageDao = new ImageDaoImpl();
        imageDao.save(image);
    }
}
