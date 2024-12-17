package com.webforj.bookstore.data;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES;
import static com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED;
import static com.fasterxml.jackson.core.JsonParser.Feature.STRICT_DUPLICATE_DETECTION;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * JsonBooksParser parses the json source into a {@link JsonBook} collection.
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Getter
@Accessors(chain = true)
public class JsonAuthorsParser {

    private final ObjectMapper mapper;

    private TypeReference<List<JsonAuthor>> typeReference = new TypeReference<>() {
    };

    public JsonAuthorsParser() {
        this.mapper = new ObjectMapper();
        mapper
          .configure(ALLOW_SINGLE_QUOTES, true)
          .configure(STRICT_DUPLICATE_DETECTION, true)
          .configure(IGNORE_UNDEFINED, false);
    }


    @PostConstruct
    public Set<JsonAuthor> parseJsonAuthors(Resource authorsResource) throws IOException {
        return Try.withResources(() -> authorsResource.getInputStream())
          .of(inputStream -> mapper.readValue(inputStream, typeReference))
          .map(HashSet::new)
          .getOrElseThrow(throwable -> new IOException(throwable.getMessage()));
    }



}
