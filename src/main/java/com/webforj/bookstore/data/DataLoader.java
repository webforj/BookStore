package com.webforj.bookstore.data;

import com.webforj.bookstore.repository.Author;
import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * DataLoader is our non-database database loader, for this example project.
 *
 * @author Kevin Hagel
 * @since Dec 14, 2024
 */
@Component
public class DataLoader {
    private static final Logger log = LogManager.getLogger(DataLoader.class);
    private final AuthorsIndex authorsIndex;
    private final BooksIndex booksIndex;


    @Value("${data-loader.books-source}")
    private Resource booksSource;

    @Value("${data-loader.authors-source}")
    private Resource authorsSource;


    /**
     * Create the data loader with the caches.
     */
    public DataLoader(AuthorsIndex authorsIndex, BooksIndex booksIndex) {
        this.authorsIndex = authorsIndex;
        this.booksIndex = booksIndex;
    }

    /**
     * Spring will call this method after construction.
     *
     * @throws IOException if anything awful happens.
     */
    @PostConstruct
    public void init() throws IOException {
        loadAuthors();
        loadBooks();
    }

    /**
     * @throws IOException file io problems mostly.
     */
    private void loadBooks() throws IOException {
        JsonBooksParser booksParser = new JsonBooksParser();
        var books = Try.of(() -> booksParser.parseJsonBooks(booksSource))
          .onFailure(t -> log.error(t.getMessage(), t))
          .getOrElseThrow(throwable -> new IOException("Failed to load from book source", throwable));
        log.atInfo().withLocation().log("Loaded {} books ", books.size());
        booksIndex.addAll(books);

    }

    /**
     * Load the authors into the authorsIndexedCollection.
     *
     * @throws IOException file io problems mostly.
     */
    private void loadAuthors() throws IOException {
        JsonAuthorsParser authorsParser = new JsonAuthorsParser();
        var authors = Try.of(() -> authorsParser.parseJsonAuthors(authorsSource))
          .onFailure(t -> log.error(t.getMessage(), t))
          .getOrElseThrow(throwable -> new IOException("Failed to load from book source", throwable));
        log.atInfo().withLocation().log("Loaded {} authors ", authors.size());
        authorsIndex.addAll(authors);

    }



    public Collection<Author> getAuthors() {
        return authorsIndex.getAuthors();
    }
}
