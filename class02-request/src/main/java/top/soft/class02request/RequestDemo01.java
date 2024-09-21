package top.soft.class02request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/requestDemo01")
public class RequestDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       1.获取请求方法
        String method = req.getMethod();
        System.out.println("获取请求方法" + method);

//        2.获取 Servlet 路径
        String servletPath = req.getServletPath();
        System.out.println("Servlet 路径" + servletPath);

//        3.获取请求参数
        String queryString = req.getQueryString();
        System.out.println("获取请求参数" + queryString);

//        4.获取请求的 URI 和URL
        String requestURI = req.getRequestURI();
        System.out.println("请求URI" + requestURI);
        String requestURL = req.getRequestURL().toString();
        System.out.println("获取请求URL" + requestURL);

//        5.获取协议版本
        String protocol = req.getProtocol();
        System.out.println("协议版本" + protocol);

//        6.获取虚拟目录
        String contextPath = req.getContextPath();
        System.out.println("虚拟目录" + contextPath);

    }
}
