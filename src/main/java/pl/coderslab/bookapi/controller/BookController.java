package pl.coderslab.bookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.bookapi.model.Book;
import pl.coderslab.bookapi.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> allBooks() {
        return bookService.readAll();
    }

    @GetMapping("/{bookId}")
    public Book readBook(@PathVariable Long bookId){
        return bookService.read(bookId);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.create(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book book){
        book.setId(bookId);
        return bookService.update(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.delete(bookId);
    }
}

