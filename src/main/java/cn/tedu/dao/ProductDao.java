package cn.tedu.dao;

import cn.tedu.entity.Product;
import cn.tedu.utils.DBUtils;
import javassist.compiler.ast.Keyword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Product> findAll() {
        ArrayList<Product>list = new ArrayList<>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product limit 0,3";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url=rs.getString(5);
                int viewCount=rs.getInt(6);
                int likeCount=rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId=rs.getInt(9);
                list.add(new Product(id,title,author,intro,url,viewCount, likeCount,created,categoryId));
            }
        }catch (Exception e) {
        e.printStackTrace();
        }
        return list;
    }

    public List<Product> findViewList() {
        ArrayList<Product>list = new ArrayList<>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product order  by viewCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
            }catch (Exception e) {
        e.printStackTrace();
        }
        return list;
    }

    public List<Product> findLikeList() {
        ArrayList<Product>list = new ArrayList<>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product order  by likeCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByCid(String cid) {
        ArrayList<Product>list = new ArrayList<>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product where category_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(cid));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url=rs.getString(5);
                int viewCount=rs.getInt(6);
                int likeCount=rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId=rs.getInt(9);
                list.add(new Product(id,title,author,intro,url,viewCount, likeCount,created,categoryId));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByKeyword(String keyword) {
        ArrayList<Product>list = new ArrayList<>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product where title like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+ keyword+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url=rs.getString(5);
                int viewCount=rs.getInt(6);
                int likeCount=rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId=rs.getInt(9);
                list.add(new Product(id,title,author,intro,url,viewCount, likeCount,created,categoryId));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
           String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product where id=?";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setInt(1, Integer.parseInt(id));
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               int oid = rs.getInt(1);
               String title = rs.getString(2);
               String author = rs.getString(3);
               String intro = rs.getString(4);
               String url=rs.getString(5);
               int viewCount=rs.getInt(6);
               int likeCount=rs.getInt(7);
               long created = rs.getLong(8);
               int categoryId=rs.getInt(9);
               return new Product(oid,title,author,intro,url,viewCount, likeCount,created,categoryId);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void likeById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="update product set likeCount=likeCount+1 where id=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="update product set viewCount=viewCount+1 where id=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="delete from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();

        }catch (Exception e) {
        e.printStackTrace();
        }
    }

    public String findUrlById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select url from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String url = rs.getString(1);
                return url;
            }
        }catch (Exception e) {
        e.printStackTrace();
        }
        return null;
    }

    public List<Product> loadMore(String count) {
        ArrayList<Product>list = new ArrayList<>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,author,intro,url,viewCount,likeCount,created,category_id from product limit ?,3";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(count));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url=rs.getString(5);
                int viewCount=rs.getInt(6);
                int likeCount=rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId=rs.getInt(9);
                list.add(new Product(id,title,author,intro,url,viewCount, likeCount,created,categoryId));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
