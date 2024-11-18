package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;

import javax.imageio.ImageIO;

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
    String enteredCaptcha = req.getParameter("captcha");

    // 获取当前会话
    HttpSession session = req.getSession(false);
    if (session == null) {
        out.print("{\"message\":\"会话已失效，请重新获取验证码并注册\"}");
        out.flush();
        return;
    }

    // 从会话中获取存储的验证码
    String storedCaptcha = (String) session.getAttribute("captchaCode");
    if (storedCaptcha == null) {
        out.print("{\"message\":\"请先获取验证码再注册\"}");
        out.flush();
        return;
    }

    // 检查验证码是否正确
    if (!enteredCaptcha.equals(storedCaptcha)) {
        out.print("{\"message\":\"验证码错误，请重新输入\"}");
        out.flush();
        return;
    }

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
            .createTime(java.time.LocalDateTime.now())
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 如果请求参数中有"refreshCaptcha"且值为"true"，则重新生成验证码
    boolean refreshCaptcha = "true".equals(req.getParameter("refreshCaptcha"));

    HttpSession session = req.getSession(true);
    String captchaCode;

    if (refreshCaptcha) {
        captchaCode = generateCaptchaCode(4);
        session.setAttribute("captchaCode", captchaCode);
    } else {
        captchaCode = (String) session.getAttribute("captchaCode");
        if (captchaCode == null) {
            captchaCode = generateCaptchaCode(4);
            session.setAttribute("captchaCode", captchaCode);
        }
    }

    byte[] captchaImageBytes = generateCaptchaImage(captchaCode);

    resp.setContentType("image/png");
    resp.getOutputStream().write(captchaImageBytes);
    resp.getOutputStream().flush();
}


    /**
     * 生成指定长度的随机验证码字符串
     *
     * @param length 验证码长度
     * @return 生成的验证码字符串
     */
    private String generateCaptchaCode(int length) {
        // 验证码字符集，可以根据需要调整
        String captchaChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder captchaCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(captchaChars.length());
            captchaCode.append(captchaChars.charAt(index));
        }

        return captchaCode.toString();
    }

    /**
     * 根据验证码字符串生成验证码图片
     *
     * @param captchaCode 验证码字符串
     * @return 生成的验证码图片字节数组
     * @throws IOException
     */
    private byte[] generateCaptchaImage(String captchaCode) throws IOException {
        // 设置验证码图片的宽度和高度
        int width = 120;
        int height = 40;

        // 创建一个BufferedImage对象，指定图像类型为RGB
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 设置背景颜色为白色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 设置字体
        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);

        // 设置文本颜色为黑色
        g2d.setColor(Color.BLACK);

        // 在图片上绘制验证码字符
        for (int i = 0; i < captchaCode.length(); i++) {
            g2d.drawString(captchaCode.charAt(i) + "", (i * 25) + 15, 35);
        }

        // 添加一些干扰线
        g2d.setColor(Color.GRAY);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // 释放图形上下文资源
        g2d.dispose();

        // 将BufferedImage转换为字节数组，以便在网络上传输（例如作为图片数据发送给前端）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}