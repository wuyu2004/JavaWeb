package top.soft.bookonline.service.impl;

import top.soft.bookonline.dao.BookDao;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.dao.impl.BookDaoImpl;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.BookService;

import java.util.List;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/10/26 14:29
 */

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    /**
     * 图书列表
     * @return
     */
    @Override
    public List<Book> getBooks() {
        return bookDao.selectAll();
    }

    /**
     * 根据id 查询图书详情
     * @param id
     * @return
     */
    @Override
    public Book getBookById(int id){
        return bookDao.getBookById(id);
    }
}
