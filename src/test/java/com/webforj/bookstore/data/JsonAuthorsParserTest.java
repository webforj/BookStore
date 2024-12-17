package com.webforj.bookstore.data;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class JsonAuthorsParserTest {

    @Value("classpath:db/authors.json")
    Resource authorsResource;

    private JsonAuthorsParser jsonAuthorsParser;

    @BeforeEach
    void setUp() {
        this.jsonAuthorsParser = new JsonAuthorsParser();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void developFormat() {
        Date dateofbirth  = Date.from(Instant.now().minus(10, ChronoUnit.MICROS));
        Date dateOfDeath  = Date.from(Instant.now().plus(10, ChronoUnit.MICROS));
        String span = "";
        dateOfDeath = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        if(dateOfDeath == null) {
            span =         "(born: " +   formatter.format(dateofbirth) + ")";
        } else {
            span = "(" + formatter.format(dateofbirth) + " - " + formatter.format(dateOfDeath) + ")";
        }
        System.out.println("span is: " + span);

        System.out.println("dateOfBirth: " + dateofbirth);


        String dateString = "Fri Oct 6";
    }

    @Test
    void getBooks() throws Exception {
        var authors = jsonAuthorsParser.parseJsonAuthors(authorsResource);
        System.out.println("authors " + authors);
        System.out.println("number of authors " + authors.size());
//        System.out.println("unique genres = " + jsonBooksParser.genres());
//        System.out.println("unique genres count = " + jsonBooksParser.genres().size());
//        System.out.println("unique publishers = " + jsonBooksParser.publishers());
//        System.out.println("unique publishers count = " + jsonBooksParser.publishers().size());
//
//        System.out.println("unique authors = " + jsonBooksParser.authors());
//        System.out.println("unique authors count = " + jsonBooksParser.authors().size());
    }

}
