package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.BookDaoImpl;
import top.soft.bookonline.entity.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    @Test
    void selectAll() {
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.selectAll();
        System.out.println(books.size());
    }

    @Test
    void selectById() {
        Book book = new BookDaoImpl().selectAll().get(1);
        System.out.println(book);
    }
}