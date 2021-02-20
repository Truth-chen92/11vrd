package cn.tedu.controller;

import cn.tedu.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        ProductDao dao=new ProductDao();

//        查询图片的url
        String fileUrl=dao.findUrlById(id);
//        根据图片的相对路径获取 磁盘中文件的绝对路径
        String path=request.getServletContext().getRealPath(fileUrl);
        new File(path).delete();

        dao.deleteById(id);
        response.sendRedirect("/home");

    }
}
