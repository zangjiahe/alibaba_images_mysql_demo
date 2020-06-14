package com.example.db;

import java.sql.*;

public class DBCon {
    Connection con;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://你云数据库的外网地址/数据库名?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                    "数据库用户名", "数据库密码");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return con;
    }
    public static void MyClose( PreparedStatement ps, Connection con) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
