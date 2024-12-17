package com.webforj.bookstore.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class JsonBooksParserTest {

    @Value("classpath:db/books.json")
    Resource booksResource;

    private JsonBooksParser jsonBooksParser;

    @BeforeEach
    void setUp() {
        this.jsonBooksParser = new JsonBooksParser();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBooks() throws Exception {
        var books = jsonBooksParser.parseJsonBooks(booksResource);
        System.out.println("books " + books);
        System.out.println("number of books " + books.size());
    }

}
