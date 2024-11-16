package top.soft.brandlist.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.brandlist.entity.Brand;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11448
 * @description: 用于处理添加品牌的请求
 * @date 2024/11/9 16:13
 */
@WebServlet("/addBrand")
public class AddBrandServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.使用缓冲字符流读取请求体数据，按行读取追加到 StringBuilder 变量中
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader br = req.getReader();
        while ((line = br.readLine())!= null) {
            stringBuilder.append(line);
        }

        // 2.将 JSON 字符串转换为 Java 对象
        List<Brand> brands = new ArrayList<>((List<Brand>) req.getServletContext().getAttribute("brands"));
        Brand brand = JSON.parseObject(stringBuilder.toString(), Brand.class);
        brands.add(brand);
        req.getServletContext().setAttribute("brands", brands);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}