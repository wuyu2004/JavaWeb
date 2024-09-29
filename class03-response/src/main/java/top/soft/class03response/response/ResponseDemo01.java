package top.soft.class03response.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 11448
 * @description: Response 对象
 * @date 2024/9/28 13:58
 */
@WebServlet("/responseDemo01")
public class ResponseDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("ResponseDemo01 被请求了......");
        if (username.equals("zhangsan")){
            resp.setStatus(200);
        }else {
            resp.setStatus(404);
        }
    }
}
