package com.webforj.bookstore.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

/**
 * JsonBook a pojo for parsing the json books
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Data
@Builder
public class JsonBookFixPublisher implements Comparable<JsonBookFixPublisher> {
    private final String id = UUID.randomUUID().toString();
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    @JsonProperty("publication_date")
    private String publicationDate;
    private String language;
    private List<String> genres;
    private String notes;
    public String getId() {
        return id;
    }


    @Override
    public int compareTo(JsonBookFixPublisher o) {
        return title.compareTo(o.title);
    }
}
