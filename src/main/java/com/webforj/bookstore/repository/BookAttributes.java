package com.webforj.bookstore.repository;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.MultiValueAttribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.attribute.SimpleNullableAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;

/**
 * BookAttributes exported by cqengine, used in the books index and for queries and searches.
 *
 * @author Kevin Hagel
 * @since Dec 19, 2024
 */
public interface BookAttributes {

    /**
     * CQEngine attribute for accessing field {@code Book.id}.  This is the primary key.
     */
    SimpleAttribute<Book, String> ID = new SimpleAttribute<>("ID") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getId();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Book.author}.
     */
    Attribute<Book, String> AUTHOR = new SimpleAttribute<>("AUTHOR") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getAuthor();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Book.title}.
     */
    Attribute<Book, String> TITLE = new SimpleAttribute<>("TITLE") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getTitle();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Book.language}.
     */
    Attribute<Book, String> LANGUAGE = new SimpleNullableAttribute<>("LANGUAGE") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getLanguage();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Book.genres}.
     */
    Attribute<Book, String> GENRES =
      new MultiValueAttribute<>(Book.class, String.class, "GENRES") {
          @Override
          public Iterable<String> getValues(Book book, QueryOptions queryOptions) {
              return book.getGenres();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Book.publisher}.
     */
    Attribute<Book, String> PUBLISHER = new SimpleNullableAttribute<>("PUBLISHER") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getPublisher();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Book.isbn}.
     */
    Attribute<Book, String> ISBN = new SimpleAttribute<>("ISBN") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getIsbn();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Book.publicationDate}.
     */
    Attribute<Book, String> PUBLICATION_DATE =
      new SimpleNullableAttribute<>("PUBLICATION_DATE") {
          public String getValue(Book book, QueryOptions queryOptions) {
              return book.getPublicationDate();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Book.notes}.
     */
    Attribute<Book, String> NOTES = new SimpleNullableAttribute<>("NOTES") {
        public String getValue(Book book, QueryOptions queryOptions) {
            return book.getNotes();
        }
    };

}
