package com.webforj.bookstore.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.persistence.support.serialization.PojoSerializer;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;

/**
 * BookSerializer
 *
 * @author Kevin Hagel
 * @since Dec 18, 2024
 */
@Log4j2
@SuppressWarnings({"unused"})
public class BookSerializer implements PojoSerializer<Book> {

    private final Class<Book> objectType;
    private final boolean polymorphic;
    private ObjectMapper objectMapper = new ObjectMapper();

    public BookSerializer(Class<Book> objectType, PersistenceConfig persistenceConfig) {
        this.objectType = objectType;
        this.polymorphic = persistenceConfig.polymorphic();
    }

    @Override
    public byte[] serialize(Book object) {
        return Try.of(() -> objectMapper.writeValueAsBytes(object))
          .onFailure(e -> log.error("Failed to serialize book", e))
          .getOrElseThrow(throwable -> new IllegalStateException("Failed to serialize book", throwable));
    }

    @Override
    public Book deserialize(byte[] bytes) {
        return Try.of(() -> objectMapper.readValue(bytes, Book.class))
          .onFailure(e -> log.error("Failed to deserialize book", e))
          .getOrElseThrow(throwable -> new IllegalStateException("Failed to deserialize book", throwable));

    }
}
