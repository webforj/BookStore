package com.webforj.bookstore.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        books.forEach(System.out::println);
        System.out.println("number of books " + books.size());
        //        var fixBooks = books.stream()
        //          .map(jsonBook ->
        //            JsonBookFixPublisher.builder()
        //              .isbn(jsonBook.getIsbn())
        //              .title(jsonBook.getTitle())
        //              .author(jsonBook.getAuthor())
        //              .notes(jsonBook.getNotes())
        //              .language(jsonBook.getLanguage())
        //              .publicationDate(jsonBook.getPublicationDate())
        //              .genres(jsonBook.getGenres())
        //              .publisher(jsonBook.getPublishers().getFirst())
        //              .notes(jsonBook.getNotes())
        //              .language(jsonBook.getLanguage())
        //              .build()
        //          )
        //          .toList();
        //        var json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fixBooks);
        //        System.out.println(json);
    }

}
