package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        ProductDao dao=new ProductDao();

        HttpSession session=request.getSession();
        String viewId=(String)session.getAttribute("view"+id);
        if (viewId == null) {
            //        让浏览量+1
            dao.viewById(id);
            session.setAttribute("view"+id,id);
        }


        Product product=dao.findById(id);
        System.out.println(product);
        Context context = new Context();
        context.setVariable("product",product);
//        分类
        CategoryDao cdao = new CategoryDao();
        List<Category>list=cdao.findAll();
        context.setVariable("list",list);
//        浏览最多
        context.setVariable("vList",dao.findViewList());
//        最受欢迎
        context.setVariable("lList",dao.findLikeList());
//        把登录的用户对象传递到页面中
        context.setVariable("user",request.getSession().getAttribute("user"));

        ThUtils.print("detail.html",context,response);



    }
}
