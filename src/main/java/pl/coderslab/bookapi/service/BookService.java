package pl.coderslab.bookapi.service;

import pl.coderslab.bookapi.model.Book;

import java.util.List;

public interface BookService {

    List<Book> readAll();
    Book read(Long id);
    Book update(Book book);
    void delete(Long id);
    Book create(Book book);


}
