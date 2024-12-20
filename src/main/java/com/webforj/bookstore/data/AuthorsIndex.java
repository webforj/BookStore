package com.webforj.bookstore.data;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.index.navigable.NavigableIndex;
import com.googlecode.cqengine.index.offheap.OffHeapIndex;
import com.googlecode.cqengine.persistence.offheap.OffHeapPersistence;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.query.option.QueryOptions;
import com.googlecode.cqengine.resultset.ResultSet;
import com.webforj.bookstore.repository.Author;
import com.webforj.bookstore.repository.AuthorAttributes;
import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * AuthorsIndex is a spring managed bean that wraps adn manages a CQEngine {@link ConcurrentIndexedCollection}.
 *
 * @author Kevin Hagel
 * @since Dec 18, 2024
 */
@Component
@Log4j2
public class AuthorsIndex {


    private final IndexedCollection<Author> authorsIndex;
    private TreeMap<String, Attribute<Author, ?>> attributes;

    /**
     * No-arg constructor
     */
    public AuthorsIndex() {
        this.authorsIndex = new ConcurrentIndexedCollection<>(OffHeapPersistence.onPrimaryKey(AuthorAttributes.ID));
    }

    public IndexedCollection<Author> getAuthorsIndex() {
        return authorsIndex;
    }

    /**
     * IN case we want to do some other initializing.
     */
    @PostConstruct
    public void init() {
        authorsIndex.addIndex(NavigableIndex.onAttribute(AuthorAttributes.ID));
        authorsIndex.addIndex(OffHeapIndex.onAttribute(AuthorAttributes.NAME));
        authorsIndex.addIndex(OffHeapIndex.onAttribute(AuthorAttributes.NATIONALITY));

        this.attributes = new TreeMap<>();

        attributes.put(AuthorAttributes.ID.getAttributeName(), AuthorAttributes.ID);
        attributes.put(AuthorAttributes.NAME.getAttributeName(), AuthorAttributes.NAME);
        attributes.put(AuthorAttributes.NATIONALITY.getAttributeName(), AuthorAttributes.NATIONALITY);
        attributes.put(AuthorAttributes.FULL_NAME.getAttributeName(), AuthorAttributes.FULL_NAME);
        attributes.put(AuthorAttributes.PEN_NAME.getAttributeName(), AuthorAttributes.PEN_NAME);
        attributes.put(AuthorAttributes.DATE_OF_BIRTH.getAttributeName(), AuthorAttributes.DATE_OF_BIRTH);
        attributes.put(AuthorAttributes.DATE_OF_BIRTH.getAttributeName(), AuthorAttributes.DATE_OF_DEATH);
        attributes.put(AuthorAttributes.GENRES.getAttributeName(), AuthorAttributes.GENRES);
        attributes.put(AuthorAttributes.LANGUAGES.getAttributeName(), AuthorAttributes.LANGUAGES);
        attributes.put(AuthorAttributes.PUBLISHERS.getAttributeName(), AuthorAttributes.PUBLISHERS);
    }

    /**
     * get all the authors in the index.
     *
     * @return an unsorted list of all authors in the index.
     */
    public List<Author> getAuthors() {
        return authorsIndex.stream().toList();
    }

    /**
     * Add an author.
     *
     * @param author the author to add.
     * @return if this set did not already contain the specified element.
     */
    public boolean add(Author author) {
        return this.authorsIndex.add(author);
    }

    /**
     * Add a collection of authors.
     *
     * @param authors the authors to add.
     * @return true if this set changed as a result of the call.
     */
    public boolean addAll(Collection<Author> authors) {
        return this.authorsIndex.addAll(authors);
    }

    /**
     * Facade method to execute query and return results.  Remember to use a try-with-resources.
     *
     * @param query the query to execute.
     * @return the results.
     */
    public ResultSet<Author> retrieve(Query<Author> query) {
        return this.authorsIndex.retrieve(query);
    }

    /**
     * Facde method to execute query with query options, returning result.
     *
     * @param query the query to executed.
     * @param queryOptions query options to apply.
     * @return the results.
     */
    public ResultSet<Author> retrieve(Query<Author> query, QueryOptions queryOptions) {
        return this.authorsIndex.retrieve(query, queryOptions);
    }

    /**
     * Attributes needed for various SQL and CQN queries.
     *
     * @return a map of the attributes.
     */
    public Map<String, Attribute<Author, ?>> attributes() {
        return attributes;
    }

}
