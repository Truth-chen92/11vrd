package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置字符集
        request.setCharacterEncoding("utf-8");
//        获取参数
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+','+password);
//        创建Dao并调用登陆方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if (user != null) {
            response.sendRedirect("/home");
        }else {
            response.sendRedirect("/showlogin");
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
