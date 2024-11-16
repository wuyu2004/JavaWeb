package top.soft.brandlist.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.brandlist.entity.Brand;

import java.io.IOException;
import java.util.List;

@WebServlet("/deleteBrand")
public class DeleteBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要删除的品牌的id，假设通过请求参数传递，名为id
        String idParam = req.getParameter("id");
        if (idParam!= null) {
            try {
                int id = Integer.parseInt(idParam);
                // 从ServletContext中获取品牌列表
                List<Brand> brands = (List<Brand>) req.getServletContext().getAttribute("brands");
                if (brands!= null) {
                    // 查找要删除的品牌在列表中的位置并删除
                    brands.removeIf(brand -> brand.getId() == id);
                    // 更新ServletContext中的品牌列表
                    req.getServletContext().setAttribute("brands", brands);
                    resp.getWriter().write("品牌删除成功");
                } else {
                    resp.getWriter().write("品牌列表为空，无法删除");
                }
            } catch (NumberFormatException e) {
                resp.getWriter().write("传入的id参数格式不正确");
            }
        } else {
            resp.getWriter().write("请传入要删除的品牌id参数");
        }
    }
}