package top.soft.bookonline.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author 11448
 * @description: 登录过滤器
 * @date 2024/11/23 15:47
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=utf-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 放行不需要登录就可以访问的路径
        String[] urls = {"/images", "/css/", "login.html", "login-page", "/login"};
        String requestUrl = request.getRequestURL().toString();

        for (String url : urls) {
            if (requestUrl.contains(url)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (user == null) {
            // 用户未登录，重定向到登录页面
            response.sendRedirect("login.html");
        } else {
            // 用户已登录，放行请求
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
