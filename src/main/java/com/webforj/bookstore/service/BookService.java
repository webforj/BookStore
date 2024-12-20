package com.webforj.bookstore.service;

import com.webforj.bookstore.repository.Book;
import java.io.IOException;
import java.util.Collection;

/**
 * BookService provides access to book services
 *
 * @author Kevin Hagel
 * @since Dec 17, 2024
 */
public interface BookService {

    /**
     * get an unfiltered, unsorted collection of books.
     *
     * @return a collection of books.
     * @throws IOException any lower problems.
     */
    Collection<Book> getAllBooks() throws IOException;
}
