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
 * JsonAuthorsCache provides a cache and cache methods for a caffeine cache of {@link JsonAuthor}s.
 *
 * @author Kevin Hagel
 * @since Dec 17, 2024
 */
@Component
public class JsonAuthorsCache {

    private static final Logger log = LogManager.getLogger(JsonAuthorsCache.class);

    Cache<String, JsonAuthor> jsonAuthorCache = Caffeine.newBuilder()
      .evictionListener((RemovalListener<String, JsonAuthor>) (key, book, cause) -> {
          log.atWarn().log("evicted key: {}, book: {}, cause: {}", key, book, cause);
      })
      .recordStats()
      .removalListener((RemovalListener<String, JsonAuthor>) (key, book, cause) -> {
          log.atWarn().log("removed key: {}, book: {}, cause: {}", key, book, cause);
      })
      .build();

    public void add(JsonAuthor jsonAuthor) {
        jsonAuthorCache.put(jsonAuthor.getId(), jsonAuthor);
    }

    public void addAll(Collection<JsonAuthor> jsonAuthors) {
        jsonAuthors.forEach(this::add);
    }

    public Collection<JsonAuthor> getAll() {
        return jsonAuthorCache.asMap().values();
    }

    public Optional<JsonAuthor> get(String id) {
        return Optional.ofNullable(jsonAuthorCache.getIfPresent(id));
    }

}
