package top.soft.class03response.servletContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/9/29 19:28
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数，文件名称
        String filename = request.getParameter("filename");

        // 找到文件服务器路径（假设 webapp 为项目的根目录）
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/images/" + "image.png");

        // 用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        // 获取文件的 mime 类型
        String mimeType = servletContext.getMimeType(filename);

        // 设置响应头类型：content-type
        response.setHeader("content-type", mimeType);

        // 设置响应头打开方式:content-disposition
        response.setHeader("content-disposition", "attachment;filename="
                + filename);

        // 将输入流的数据写出到输出流中
        OutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len;
        while ((len = fis.read(buff))!= -1) {
            sos.write(buff, 0, len);
        }
        fis.close();
    }
}
