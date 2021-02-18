package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context=new Context();
//        创建CategoryDao 并调用查询所有分类的方法
        CategoryDao dao=new CategoryDao();
        List<Category>list = dao.findAll();
        context.setVariable("list",list);
//        创建BannerDao 并调用查询所有Banner
        BannerDao bDao=new BannerDao();
        List<Banner>blist = bDao.findAll();
        context.setVariable("blist",blist);
//        取出Session里面的用户对象
        User user=(User)request.getSession().getAttribute("user");
//        if (user != null) {
//            System.out.println("登录过");
//        }else {
//            System.out.println("没登录过");
//        }
        context.setVariable("user",user);

        ThUtils.print("home.html",context,response);
    }
}
