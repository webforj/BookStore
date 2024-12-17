package com.webforj.bookstore.data;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import java.util.Collection;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * JsonBooksCache provides a cache and cache methods for a caffeine cache of {@link JsonBook}s.
 *
 * @author Kevin Hagel
 * @since Dec 17, 2024
 */
@Component
public class JsonBooksCache {

    private static final Logger log = LogManager.getLogger(JsonBooksCache.class);

    Cache<String, JsonBook> jsonBookCache = Caffeine.newBuilder()
      .evictionListener((RemovalListener<String, JsonBook>) (key, book, cause) -> {
          log.atWarn().log("evicted key: {}, book: {}, cause: {}", key, book, cause);
      })
      .recordStats()
      .removalListener((RemovalListener<String, JsonBook>) (key, book, cause) -> {
          log.atWarn().log("removed key: {}, book: {}, cause: {}", key, book, cause);
      })
      .build();

    public void add(JsonBook jsonBook) {
        jsonBookCache.put(jsonBook.getId(), jsonBook);
    }

    public void addAll(Collection<JsonBook> jsonBooks) {
        jsonBooks.forEach(this::add);
    }

    public Collection<JsonBook> getAll() {
        return jsonBookCache.asMap().values();
    }

    public Optional<JsonBook> get(String id) {
        return Optional.ofNullable(jsonBookCache.getIfPresent(id));
    }
}
