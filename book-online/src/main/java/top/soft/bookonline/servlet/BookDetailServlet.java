package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.BookService;
import top.soft.bookonline.service.impl.BookServiceImpl;

import java.io.IOException;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/10/26 15:47
 */

@WebServlet(urlPatterns = "/detail/*")
public class BookDetailServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取请求的URI，并去除空格
        String requestPath = req.getRequestURI().trim();
//        获得最后一个“/”的位置
        int position = requestPath.lastIndexOf("/");
//        从该位置到最后取子串，也就是/detail/3中的3，有了这个id，就可以找到集合中对应的图书对象
        String id = requestPath.substring(position + 1);
//        根据id查找图书
        Book book = bookService.getBookById(Integer.parseInt(id));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/book_detail.jsp").forward(req, resp);
    }
}
