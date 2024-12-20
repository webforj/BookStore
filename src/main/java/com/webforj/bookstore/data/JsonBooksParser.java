package com.webforj.bookstore.data;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES;
import static com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED;
import static com.fasterxml.jackson.core.JsonParser.Feature.STRICT_DUPLICATE_DETECTION;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webforj.bookstore.repository.Book;
import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.core.io.Resource;

/**
 * JsonBooksParser is a parser of the books.json, it is not a component.
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Getter
@Accessors(chain = true)
public class JsonBooksParser {

    private final TypeReference<List<JsonBook>> typeReference = new TypeReference<>() {
    };
    private final ObjectMapper mapper;

    public JsonBooksParser() {
        this.mapper = new ObjectMapper();
        mapper
          .configure(ALLOW_SINGLE_QUOTES, true)
          .configure(STRICT_DUPLICATE_DETECTION, true)
          .configure(IGNORE_UNDEFINED, false);
    }



    @PostConstruct
    public Set<Book> parseJsonBooks(Resource booksResource) throws IOException {
        return Try.withResources(() -> booksResource.getInputStream())
          .of(inputStream -> mapper.readValue(inputStream, typeReference))
          .map(HashSet::new)
          .getOrElseThrow(throwable -> new IOException(throwable.getMessage()))
          .stream()
          .map(jsonBook ->
            Book.builder()
              .id(jsonBook.getId())
              .author(jsonBook.getAuthor())
              .title(jsonBook.getTitle())
              .language(jsonBook.getLanguage())
              .notes(jsonBook.getNotes())
              .genres(jsonBook.getGenres())
              .isbn(jsonBook.getIsbn())
              .publisher(jsonBook.getPublisher())
              .publicationDate(jsonBook.getPublicationDate())
              .build()
          )

          .collect(Collectors.toSet());
    }



}
