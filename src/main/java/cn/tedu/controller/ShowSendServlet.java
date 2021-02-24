package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
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

@WebServlet(name = "ShowSendServlet",urlPatterns = "/showsend")
public class ShowSendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        取出Session中的用户对象 判断是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/showlogin");
            return;
        }

        //        查询出所有分类
        CategoryDao dao=new CategoryDao();
        List<Category>list = dao.findAll();
//        查询到的所有分类信息装进容器中
        Context context = new Context();
        context.setVariable("list",list);
        ThUtils.print("send.html",context,response);
    }
}
