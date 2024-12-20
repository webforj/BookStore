package com.webforj.bookstore.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.persistence.support.serialization.PojoSerializer;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;

/**
 * AuthorSerializer a pojo serialize so we can use disk index or off-heap index for whatever.
 *
 * @author Kevin Hagel
 * @since Dec 18, 2024
 */
@Log4j2
public class AuthorSerializer implements PojoSerializer<Author> {

    private final Class<Author> objectType;
    private final boolean polymorphic;
    private ObjectMapper objectMapper = new ObjectMapper();

    public AuthorSerializer(Class<Author> objectType, PersistenceConfig persistenceConfig) {
        this.objectType = objectType;
        this.polymorphic = persistenceConfig.polymorphic();
    }

    @Override
    public byte[] serialize(Author object) {
        return Try.of(() -> objectMapper.writeValueAsBytes(object))
          .onFailure(e -> log.error("Failed to serialize author", e))
          .getOrElseThrow(throwable -> new IllegalStateException("Failed to serialize author", throwable));
    }

    @Override
    public Author deserialize(byte[] bytes) {
        return Try.of(() -> objectMapper.readValue(bytes, Author.class))
          .onFailure(e -> log.error("Failed to deserialize author", e))
          .getOrElseThrow(throwable -> new IllegalStateException("Failed to deserialize author", throwable));

    }
}
