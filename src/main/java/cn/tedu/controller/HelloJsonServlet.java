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

@WebServlet(name = "HelloJsonServlet",urlPatterns = "/hellojson")
public class HelloJsonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        查询所有作品信息
        ProductDao dao=new ProductDao();
        List<Product>list=dao.findAll();
//        把list集合中的数据 装进 json字符串中
        ObjectMapper om=new ObjectMapper();
        String jsonStr=om.writeValueAsString(list);
        System.out.println("json="+jsonStr);
//        把json字符串返回给客户端
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(jsonStr);
        pw.close();

    }
}
