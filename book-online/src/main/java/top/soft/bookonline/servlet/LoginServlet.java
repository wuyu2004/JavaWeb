package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.UserService;
import top.soft.bookonline.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 获取表单数据
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            String remember = req.getParameter("remember");

            // 进行简单的输入验证（这里示例验证账号和密码不为空，实际可根据具体要求完善验证逻辑）
            if (account == null || account.isEmpty() || password == null || password.isEmpty()) {
                sendLoginFailureResponse(resp, "账号和密码不能为空");
                return;
            }

            // 调用登录功能
            User user = userService.signIn(account, password);
            if (user!= null) {
                // 登陆成功，将用户对象计入 session
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                if ("true".equals(remember)) { // 这里假设前端传递的remember值为字符串类型的"true"或其他表示选中的值，根据实际情况调整
                    setRememberMeCookies(resp, account, password);
                }

                // 重定向回到 /index，进入IndexServlet
                resp.sendRedirect("/index");
            } else {
                sendLoginFailureResponse(resp, "账号或密码错误");
            }
        } catch (Exception e) {
            LOGGER.severe("登录过程出现异常: " + e.getMessage());
            sendLoginFailureResponse(resp, "登录出现异常，请稍后重试");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 设置记住我功能的Cookies
     *
     * @param resp    HttpServletResponse对象，用于添加Cookie到响应中
     * @param account 用户名
     * @param password 密码
     */
    private void setRememberMeCookies(HttpServletResponse resp, String account, String password) {
        Cookie usernameCookie = new Cookie("username", account);
        Cookie passwordCookie = new Cookie("password", password);
        usernameCookie.setMaxAge(60 * 60 * 24 * 7);
        passwordCookie.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(usernameCookie);
        resp.addCookie(passwordCookie);
    }

    /**
     * 发送登录失败的响应信息给客户端
     *
     * @param resp    HttpServletResponse对象，用于设置响应内容和状态码等
     * @param message 登录失败的提示消息
     */
    private void sendLoginFailureResponse(HttpServletResponse resp, String message) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('" + message + "');location.href='/';</script>");
        } catch (IOException e) {
            LOGGER.severe("发送登录失败响应出现异常: " + e.getMessage());
        }
    }
}