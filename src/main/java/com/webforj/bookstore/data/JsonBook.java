package com.webforj.bookstore.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import lombok.Data;

/**
 * JsonBook a pojo for parsing the json books
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Data
public class JsonBook {
    private final String id = UUID.randomUUID().toString();
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    @JsonProperty("year_published")
    private String yearPublished;
    private String language;
    private List<String> genres;

    public String getId() {
        return id;
    }
}
