package com.webforj.bookstore.data;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES;
import static com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED;
import static com.fasterxml.jackson.core.JsonParser.Feature.STRICT_DUPLICATE_DETECTION;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webforj.bookstore.repository.Author;
import io.vavr.control.Try;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.core.io.Resource;

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

    private final TypeReference<List<JsonAuthor>> typeReference = new TypeReference<>() {
    };

    public JsonAuthorsParser() {
        this.mapper = new ObjectMapper();
        mapper
          .configure(ALLOW_SINGLE_QUOTES, true)
          .configure(STRICT_DUPLICATE_DETECTION, true)
          .configure(IGNORE_UNDEFINED, false);
    }


    /**
     * Loads the stream parses the json authors, returns a set of {@link Author}s.
     *
     * @param authorsResource the source of the authors json.
     * @return the set of Authors.
     * @throws IOException any problems with the i/o.
     */
    public Set<Author> parseJsonAuthors(Resource authorsResource) throws IOException {
        return Try.withResources(() -> authorsResource.getInputStream())
          .of(inputStream -> mapper.readValue(inputStream, typeReference))
          .map(HashSet::new)
          .getOrElseThrow(throwable -> new IOException(throwable.getMessage())).stream()
          .map(jsonAuthor -> Author.builder()
            .id(jsonAuthor.getId())
            .dateOfBirth(jsonAuthor.getDateOfBirth())
            .dateOfDeath(jsonAuthor.getDateOfDeath())
            .name(jsonAuthor.getName())
            .fullName(jsonAuthor.getFullName())
            .impacts(jsonAuthor.getImpacts())
            .genres(jsonAuthor.getGenres())
            .influences(jsonAuthor.getInfluences())
            .nationality(jsonAuthor.getNationality())
            .penName(jsonAuthor.getPenName())
            .professions(jsonAuthor.getProfessions())
            .publishers(jsonAuthor.getPublishers())
            .languages(jsonAuthor.getLanguages())
            .build())
          .collect(Collectors.toSet());
    }



}
