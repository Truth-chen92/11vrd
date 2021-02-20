package cn.tedu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BmiServlet",urlPatterns = "/bmi")
public class BmiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String h=request.getParameter("h");
        String w = request.getParameter("w");
        float height=Float.parseFloat(h);
        float weight=Float.parseFloat(w);
        float bmi=weight/(height*height);
        String info=null;
        if (bmi<18.5) {
            info="太瘦了";
        }else if (bmi <=24 ) {
            info="正常";
        }else if (bmi <= 28) {
            info="胖了";
        }else {
            info="太胖了";
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(info);
        pw.close();
    }
}
