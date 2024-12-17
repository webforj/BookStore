package com.webforj.bookstore.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * JsonBooksParser is a parser of the books.json, it is not a component.
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Getter
@Accessors(chain = true)
public class JsonBooksParser {

    @PostConstruct
    public Set<JsonBook> parseJsonBooks(Resource booksResource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        try (InputStream inputStream = booksResource.getInputStream()) {
            TypeReference<List<JsonBook>> typeReference = new TypeReference<>() {
            };
            return new HashSet<>(mapper.readValue(inputStream, typeReference));
        }
    }



}
