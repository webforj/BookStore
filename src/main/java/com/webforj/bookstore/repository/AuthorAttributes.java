package com.webforj.bookstore.repository;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.MultiValueAttribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.attribute.SimpleNullableAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import java.util.Date;

/**
 * AuthorAttributes
 *
 * @author Kevin Hagel
 * @since Dec 18, 2024
 */
public abstract class AuthorAttributes {

    /**
     * CQEngine attribute for accessing field {@code Author.id}.  THis is the primary key field for Author.
     */
    public static final SimpleAttribute<Author, String> ID = new SimpleAttribute<>("ID") {
        @Override
        public String getValue(Author author, QueryOptions queryOptions) {
            return author.getId();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Author.name}.
     */
    public static final SimpleAttribute<Author, String> NAME = new SimpleAttribute<>("NAME") {
        @Override
        public String getValue(Author author, QueryOptions queryOptions) {
            return author.getName();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Author.penName}.
     */
    public static final Attribute<Author, String> PEN_NAME = new SimpleNullableAttribute<>("PEN_NAME") {
        @Override
        public String getValue(Author author, QueryOptions queryOptions) {
            return author.getPenName();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Author.fullName}.
     */
    public static final SimpleAttribute<Author, String> FULL_NAME = new SimpleAttribute<>("FULL_NAME") {
        @Override
        public String getValue(Author author, QueryOptions queryOptions) {
            return author.getFullName();
        }
    };

    /**
     * CQEngine attribute for accessing field {@code Author.dateOfBirth}.
     */
    public static final SimpleAttribute<Author, Date> DATE_OF_BIRTH =
      new SimpleAttribute<Author, Date>("DATE_OF_BIRTH") {
          @Override
          public Date getValue(Author author, QueryOptions queryOptions) {
              return author.getDateOfBirth();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.dateOfDeath}.
     */
    public static final Attribute<Author, Date> DATE_OF_DEATH =
      new SimpleNullableAttribute<>("DATE_OF_DEATH") {
          @Override
          public Date getValue(Author author, QueryOptions queryOptions) {
              return author.getDateOfDeath();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.languages}.
     */
    public static final Attribute<Author, String> LANGUAGES =
      new MultiValueAttribute<>(Author.class, String.class, "LANGUAGES") {
          @Override
          public Iterable<String> getValues(Author author, QueryOptions queryOptions) {
              return author.getLanguages();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.impacts}.
     */
    public static final Attribute<Author, String> IMPACTS =
      new MultiValueAttribute<>(Author.class, String.class, "IMPACTS") {
          @Override
          public Iterable<String> getValues(Author author, QueryOptions queryOptions) {
              return author.getImpacts();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.influences}.
     */
    public static final Attribute<Author, String> INFLUENCES =
      new MultiValueAttribute<>(Author.class, String.class, "INFLUENCES") {
          @Override
          public Iterable<String> getValues(Author author, QueryOptions queryOptions) {
              return author.getInfluences();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.professions}.
     */
    public static final Attribute<Author, String> PROFESSIONS =
      new MultiValueAttribute<>(Author.class, String.class, "PROFESSIONS") {
          @Override
          public Iterable<String> getValues(Author author, QueryOptions queryOptions) {
              return author.getProfessions();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.genres}.
     */
    public static final Attribute<Author, String> GENRES =
      new MultiValueAttribute<>(Author.class, String.class, "GENRES") {
          @Override
          public Iterable<String> getValues(Author author, QueryOptions queryOptions) {
              return author.getGenres();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.publishers}.
     */
    public static final Attribute<Author, String> PUBLISHERS =
      new MultiValueAttribute<>(Author.class, String.class, "PUBLISHERS") {
          @Override
          public Iterable<String> getValues(Author author, QueryOptions queryOptions) {
              return author.getPublishers();
          }
      };

    /**
     * CQEngine attribute for accessing field {@code Author.nationality}.
     */
    public static final SimpleAttribute<Author, String> NATIONALITY =
      new SimpleAttribute<>("NATIONALITY") {
          @Override
          public String getValue(Author author, QueryOptions queryOptions) {
              return author.getNationality();
          }
      };
}
