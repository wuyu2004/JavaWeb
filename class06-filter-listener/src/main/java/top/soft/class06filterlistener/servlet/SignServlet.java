package top.soft.class06filterlistener.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/11/23 16:42
 */
@WebServlet("/signServlet")
public class SignServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        resp.setContentType("text/html;charset=utf-8");
        req.getSession().setAttribute("username", username);
        resp.sendRedirect("/detail.jsp");
    }
}
