package cn.tedu.filter;

import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter",urlPatterns = {"/showsend","/showbanner","/delete","/deletebanner"})
public class MyFilter implements Filter {
//    过滤器销毁时执行的方法
    public void destroy() {

    }
//    接收到请求时执行的方法
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将父类ServletRequest强转成子类HTTPServletRequest
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //取出Session 中的user对象
        //        取出Session中的用户对象 判断是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/showlogin");
        }else {
            chain.doFilter(req, resp);
        }
    }
//    过滤器初始化时执行的方法
    public void init(FilterConfig config) throws ServletException {

    }

}
