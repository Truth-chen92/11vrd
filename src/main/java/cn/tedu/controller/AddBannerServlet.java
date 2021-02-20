package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

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
@WebServlet(name = "AddBannerServlet",urlPatterns = "/addbanner")
public class AddBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
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

        Banner banner=new Banner(0,"images"+datePath+fileName);
        BannerDao dao=new BannerDao();
        dao.insert(banner);

        response.sendRedirect("/showbanner");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
