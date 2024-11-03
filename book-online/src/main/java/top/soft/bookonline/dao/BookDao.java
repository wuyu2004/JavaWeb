package top.soft.bookonline.dao;

import top.soft.bookonline.entity.Book;

import java.util.List;

public interface BookDao {
    /**
     * 查询所有图书
     *
     * @retur List<Book></>
     */
    List<Book>selectAll();

    /**
     *
     * @param id
     * @return Book
     */
    Book getBookById(Integer id);
}