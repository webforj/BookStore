package com.webforj.bookstore.data;

import static com.googlecode.cqengine.query.QueryFactory.contains;
import static com.googlecode.cqengine.query.QueryFactory.equal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.query.parser.sql.SQLParser;
import com.webforj.bookstore.repository.Author;
import com.webforj.bookstore.repository.AuthorAttributes;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest
class AuthorsIndexTest {

    private AuthorsIndex authorsIndex;

    @Value("classpath:db/authors.json")
    Resource authorsResource;

    private JsonAuthorsParser jsonAuthorsParser;

    @BeforeEach
    void setUp() {
        this.authorsIndex = new AuthorsIndex();
        authorsIndex.init();
        this.jsonAuthorsParser = new JsonAuthorsParser();
        System.out.println("authorsIndex: " + authorsIndex);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenAuthorsParse_whenAdded_thenCorrect() throws IOException {
        var authors = assertDoesNotThrow(() -> jsonAuthorsParser.parseJsonAuthors(authorsResource));
        assertTrue(authorsIndex.add(authors.stream().findFirst().get()));
    }

    @Test
    void givenAuthorsParsed_whenCollectionAdded_thenCorrect() throws IOException {
        var authors = assertDoesNotThrow(() -> jsonAuthorsParser.parseJsonAuthors(authorsResource));
        assertTrue(authorsIndex.addAll(authors));
    }

    @Test
    void givenValidIndex_whenQueriesForName_thenCorrect() {
        var authors = assertDoesNotThrow(() -> jsonAuthorsParser.parseJsonAuthors(authorsResource));
        assertTrue(authorsIndex.addAll(authors));
        Query<Author> query = equal(AuthorAttributes.NAME, "Harper Lee");
        try(var result = authorsIndex.retrieve(query)) {
            Set<Author> set = result.stream().collect(Collectors.toSet());
            assertThat(set).isNotNull().isNotEmpty().hasSize(1);
        }
    }

    @Test
    void givenValidIndex_whenQueriesForNationality_thenCorrect() {
        var authors = assertDoesNotThrow(() -> jsonAuthorsParser.parseJsonAuthors(authorsResource));
        assertTrue(authorsIndex.addAll(authors));
        Query<Author> query = equal(AuthorAttributes.NATIONALITY, "American");
        try(var result = authorsIndex.retrieve(query)) {
            Set<Author> set = result.stream().collect(Collectors.toSet());
            assertThat(set).isNotNull().isNotEmpty();
            assertThat(set.size()).isGreaterThan(1);
        }
    }

    @Test
    void givenValidIndex_whenSQLQueries_thenCorrect() {
        var authors = assertDoesNotThrow(() -> jsonAuthorsParser.parseJsonAuthors(authorsResource));
        assertTrue(authorsIndex.addAll(authors));
        Map<String, Attribute<Author, ?>> attributes =
          authorsIndex.attributes();
        SQLParser<Author> parser = SQLParser.forPojoWithAttributes(Author.class, authorsIndex.attributes());
        try(var results = parser.retrieve(authorsIndex.getAuthorsIndex(), "SELECT * FROM authors WHERE NATIONALITY IN('British','English') ")) {
            Set<Author> set = results.stream().collect(Collectors.toSet());
            assertThat(set).isNotNull().isNotEmpty();
            assertThat(set.size()).isGreaterThan(1);
        }
    }

    @Test
    void givenValidIndex_whenSearchGenres_thenCorrect() {
        var authors = assertDoesNotThrow(() -> jsonAuthorsParser.parseJsonAuthors(authorsResource));
        assertTrue(authorsIndex.addAll(authors));
        try(var results = authorsIndex.retrieve(contains(AuthorAttributes.GENRES, "Fantasy"))) {
            Set<Author> set = results.stream().collect(Collectors.toSet());
            set.stream().forEach(author -> System.out.println(author.getName() + " " + author.getGenres()));
            assertThat(set).isNotNull().isNotEmpty();
            assertThat(set.size()).isGreaterThan(1);
        }
    }

    @Test
    void experimentsWithFindingByGenre() {

    }

}
