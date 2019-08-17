package pl.coderslab.bookapi.service;

import org.springframework.stereotype.Service;
import pl.coderslab.bookapi.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemoryBookService implements BookService {

    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    @Override
    public Book create(Book book) {

        long nextId = this.list.stream()
                .mapToLong(Book::getId)
                .max()
                .orElse(0) + 1;

        book.setId(nextId);
        this.list.add(book);

        return book;
    }

    @Override
    public List<Book> readAll() {
        return this.list;
    }

    @Override
    public Book read(Long id) {

        Optional<Book> first = this.list.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        return first.orElseThrow(RuntimeException::new);
    }

    @Override
    public Book update(Book book) {

        Optional<Book> first = this.list.stream()
                .filter(book1 -> book1.getId().equals(book.getId()))
                .peek(book1 -> book1.setAuthor(book.getAuthor()))
                .peek(book1 -> book1.setTitle(book.getTitle()))
                .findFirst();

        return first.orElseThrow(RuntimeException::new);

    }

    @Override
    public void delete(Long id) {
        Book book = read(id);
        if (book != null) {
            this.list.remove(book);
        }
    }
}
