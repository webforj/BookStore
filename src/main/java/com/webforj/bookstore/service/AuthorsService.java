package com.webforj.bookstore.service;

import com.webforj.bookstore.repository.Author;
import java.io.IOException;
import java.util.Collection;

/**
 * AuthorsService
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
public interface AuthorsService {

    /**
     * get an unfiltered, unsorted collection of authors.
     *
     * @return a collection of authors.
     * @throws IOException any lower problems.
     */
    Collection<Author> getAllAuthors() throws IOException;
}
