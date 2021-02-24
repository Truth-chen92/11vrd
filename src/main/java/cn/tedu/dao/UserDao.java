package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User login(String username, String password) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id from vrduser where username=? and password=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {//满足条件登陆成功
                int id=rs.getInt(1);
                return new User(id,username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//如果代码执行到这说明没有查询到 登陆失败
    }

    public Boolean check(String username) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id from vrduser where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        }catch (Exception e) {
        e.printStackTrace();
        }
        return false;
    }

    public void reg(String username, String password) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="insert into vrduser values(null,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();


        }catch (Exception e) {
        e.printStackTrace();
        }
    }
}
