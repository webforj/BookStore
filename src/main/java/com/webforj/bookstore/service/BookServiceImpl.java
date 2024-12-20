package com.webforj.bookstore.service;

import com.webforj.bookstore.data.AuthorsIndex;
import com.webforj.bookstore.data.BooksIndex;
import com.webforj.bookstore.repository.Book;
import java.io.IOException;
import java.util.Collection;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * BookServiceImpl
 *
 * @author Kevin Hagel
 * @since Dec 19, 2024
 */
@Service
@Log4j2
public class BookServiceImpl implements BookService {

    private final AuthorsIndex authorsIndex;
    private final BooksIndex booksIndex;

    public BookServiceImpl(AuthorsIndex authorsIndex, BooksIndex booksIndex) {
        this.authorsIndex = authorsIndex;
        this.booksIndex = booksIndex;
    }

    /**
     * get an unfiltered, unsorted collection of books.
     *
     * @return a collection of books.
     * @throws IOException any lower problems.
     */
    @Override
    public Collection<Book> getAllBooks() throws IOException {
        return booksIndex.getBooks();
    }
}
