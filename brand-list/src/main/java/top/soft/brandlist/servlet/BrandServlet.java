package top.soft.brandlist.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.brandlist.entity.Brand;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 11448
 * @description: 用于处理获取品牌列表以及可能的删除操作的请求
 * @date 2024/11/9 16:13
 */
@WebServlet("/brand")
public class BrandServlet extends HttpServlet {
    private List<Brand> getBrandList() {
        return List.of(
                Brand.builder().id(101).companyName("apple").brandName("iPhone16").description("苹果-iPhone16").ordered(1).build(),
                Brand.builder().id(102).companyName("huawei").brandName("mate60").description("华为-mate60").ordered(2).build(),
                Brand.builder().id(103).companyName("benz").companyName("mercedes").description("奔驰-梅赛德斯").ordered(3).build());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数中的操作类型（这里新增用于判断是否是删除操作）
        String operation = req.getParameter("operation");
        // 获取请求参数中的品牌ID（用于删除操作）
        String brandIdStr = req.getParameter("brandId");

        resp.setContentType("application/json;charset=utf-8");
        ServletContext servletContext = req.getServletContext();
        Object brands = servletContext.getAttribute("brands");

        List<Brand> brandList = null;
        if (brands instanceof List) {
            // 使用CopyOnWriteArrayList进行包装，以处理并发修改问题（假设可能存在多线程环境）
            brandList = new CopyOnWriteArrayList<>((List<Brand>) brands);
        }
        brandList = brandList == null? getBrandList() : brandList;

        // 如果是删除操作
        if ("delete".equals(operation) && brandIdStr!= null) {
            int brandId = Integer.parseInt(brandIdStr);

            // 先判断brandList是否为null
            if (brandList!= null) {
                // 遍历品牌列表，找到要删除的品牌并移除
                for (Iterator<Brand> it = brandList.iterator(); it.hasNext();) {
                    Brand brand = it.next();
                    // 通过getter方法获取id的值进行比较
                    if (brand.getId() == brandId) {
                        it.remove();
                        break;
                    }
                }
            } else {
                // 返回一个错误信息给客户端，表示品牌列表为空无法删除
                resp.setContentType("text/plain;charset=utf-8");
                resp.getWriter().write("品牌列表为空，无法执行删除操作");
            }
        }

        servletContext.setAttribute("brands", brandList);
        req.getServletContext().setAttribute("brands", brandList);
        String jsonString = JSON.toJSONString(brandList);
        resp.getWriter().write(jsonString);
    }
}