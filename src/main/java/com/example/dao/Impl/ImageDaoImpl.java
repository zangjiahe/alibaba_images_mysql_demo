package com.example.dao.Impl;

import com.example.Image.Image;
import com.example.dao.ImageDao;
import com.example.db.DBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.db.DBCon.MyClose;

public class ImageDaoImpl implements ImageDao {
    private DBCon db;
    private Connection con;
    private PreparedStatement ps;

    public ImageDaoImpl() {
        super();
        db = new DBCon();
        con = db.getConnection();
    }

    public void save(Image image) {
        try {
            ps = con.prepareStatement("insert into images (id,url,size) values (null,?,?)");
            ps.setString(1,image.getUrl());
            ps.setString(2,image.getSize());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyClose( ps, con);
        }
    }
}
