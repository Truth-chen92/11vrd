package cn.tedu.dao;

import cn.tedu.entity.Product;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDao {

    public void insert(Product p) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="insert into product values(null,?,?,?,?,0,0,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getAuthor());
            ps.setString(3,p.getIntro());
            ps.setString(4, p.getUrl());
            ps.setLong(5, p.getCreated());
            ps.setInt(6,p.getCategoryId());
            ps.executeUpdate();
        }catch (Exception e) {
        e.printStackTrace();
        }
    }
}
