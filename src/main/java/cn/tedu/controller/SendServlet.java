package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title=request.getParameter("title");
        String author=request.getParameter("author");
        String intro=request.getParameter("intro");
        String categoryId=request.getParameter("categoryId");
        System.out.println(title+','+author+','+intro+','+categoryId);
//        获取上传文件
        Part part=request.getPart("file");
        String info=part.getHeader("content-disposition");
        String suffix=info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println("后缀名:"+suffix);
        String fileName= UUID.randomUUID()+suffix;
        System.out.println("文件名:"+fileName);
//        得到和日期相关路径
        SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd/");
//        得到今天日期对象 util包
        Date date=new Date();
//        得到日期路径
        String datePath=format.format(date);
        System.out.println(datePath);
//        得到Tomcat管辖范围内的路劲
        String path=request.getServletContext().getRealPath("images"+datePath);
        System.out.println(path);
//        创建文件夹 一定要选s的方法
        new File(path).mkdirs();
//        把图片保存到文件夹中
        part.write(path+fileName);
//        把参数和图片路径封装到对象中
        Product p = new Product(0,title,author,intro,"images"+datePath+fileName,0,0,System.currentTimeMillis(),Integer.parseInt(categoryId));
        ProductDao dao = new ProductDao();
        dao.insert(p);
        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
