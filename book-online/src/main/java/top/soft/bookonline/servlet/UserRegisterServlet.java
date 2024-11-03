package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型为JSON
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 获取注册表单数据
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String avatar = req.getParameter("avatar");
        String address = req.getParameter("address");

        // 检查用户名是否已存在
        if (userDao.checkAccountExists(account)) {
            out.print("{\"message\":\"该用户名已被占用，请重新选择用户名\"}");
            out.flush();
            return;
        }

        // 创建用户对象
        User user = User.builder()
                .account(account)
                .password(password)
                .nickname(nickname)
                .avatar(avatar)
                .address(address)
                .createTime(LocalDateTime.now())
                .build();

        // 插入新用户到数据库
        int result = userDao.insertUser(user);
        if (result > 0) {
            out.print("{\"message\":\"注册成功，请登录\"}");
            out.flush();
        } else {
            out.print("{\"message\":\"注册失败，请稍后重试\"}");
            out.flush();
        }
    }
}