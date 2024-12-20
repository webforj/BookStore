package com.webforj.bookstore.service;

import com.webforj.bookstore.data.AuthorsIndex;
import com.webforj.bookstore.repository.Author;
import java.io.IOException;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * AuthorsServiceImpl
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsIndex authorsIndex;


    public AuthorsServiceImpl(AuthorsIndex authorsIndex) {
        this.authorsIndex = authorsIndex;
    }

    /**
     * @return an unfiltered unordered Set of {@link Author} instances.  All of them.
     * @throws IOException if anything unnatural happens.
     */
    public Collection<Author> getAllAuthors() throws IOException {
        return authorsIndex.getAuthors();
    }



}
