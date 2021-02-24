package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadMoreServlet",urlPatterns = "/loadmore")
public class LoadMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count=request.getParameter("count");
        ProductDao dao=new ProductDao();
        List<Product>list = dao.loadMore(count);
//        把作品集装进json字符串
        ObjectMapper om=new ObjectMapper();
        String jsonStr=om.writeValueAsString(list);
        response.setContentType("text/htm;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(jsonStr);
        pw.close();

    }
}
