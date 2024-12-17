package com.webforj.bookstore.service;

import com.webforj.bookstore.repository.Author;
import com.webforj.bookstore.repository.AuthorDto;
import java.io.IOException;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * AuthorsService
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
public interface AuthorsService {

    Set<AuthorDto> getAllAuthors() throws IOException;
}
