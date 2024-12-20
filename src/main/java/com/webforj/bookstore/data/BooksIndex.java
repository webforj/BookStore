package com.webforj.bookstore.data;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.index.navigable.NavigableIndex;
import com.googlecode.cqengine.persistence.offheap.OffHeapPersistence;
import com.webforj.bookstore.repository.Book;
import com.webforj.bookstore.repository.BookAttributes;
import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * AuthorsIndex wraps an manages a CQEngine {@link ConcurrentIndexedCollection}.
 *
 * @author Kevin Hagel
 * @since Dec 18, 2024
 */
@Component
@Log4j2
public class BooksIndex {


    private final IndexedCollection<Book> booksIndex;
    private TreeMap<String, Attribute<Book, ?>> attributes;

    /**
     * No-arg constructor
     */
    public BooksIndex() {
        this.booksIndex = new ConcurrentIndexedCollection<>(OffHeapPersistence.onPrimaryKey(BookAttributes.ID));
    }

    /**
     * IN case we want to do some other initializing.
     */
    @PostConstruct
    public void init() {
        booksIndex.addIndex(NavigableIndex.onAttribute(BookAttributes.ID));
        booksIndex.addIndex(NavigableIndex.onAttribute(BookAttributes.AUTHOR));
        booksIndex.addIndex(NavigableIndex.onAttribute(BookAttributes.TITLE));

        this.attributes = new TreeMap<>();

        attributes.put(BookAttributes.ID.getAttributeName(), BookAttributes.ID);
        attributes.put(BookAttributes.GENRES.getAttributeName(), BookAttributes.GENRES);
        attributes.put(BookAttributes.ISBN.getAttributeName(), BookAttributes.ISBN);
        attributes.put(BookAttributes.NOTES.getAttributeName(), BookAttributes.NOTES);
        attributes.put(BookAttributes.LANGUAGE.getAttributeName(), BookAttributes.LANGUAGE);
        attributes.put(BookAttributes.PUBLICATION_DATE.getAttributeName(), BookAttributes.PUBLICATION_DATE);
        attributes.put(BookAttributes.TITLE.getAttributeName(), BookAttributes.TITLE);
        attributes.put(BookAttributes.PUBLISHER.getAttributeName(), BookAttributes.PUBLISHER);
        attributes.put(BookAttributes.AUTHOR.getAttributeName(), BookAttributes.AUTHOR);

    }

    /**
     * @return the books index.
     */
    public IndexedCollection<Book> getBooksIndex() {
        return booksIndex;
    }

    /**
     * Add a book.
     *
     * @param book the book to add.
     * @return if the set did not already contain the book.
     */
    public boolean add(Book book) {
        return this.booksIndex.add(book);
    }

    /**
     * @param books books to add.
     * @return if the set changed as a result of this call.
     */
    public boolean addAll(Collection<Book> books) {
        return this.booksIndex.addAll(books);
    }


    /**
     * get all the books in the index.
     *
     * @return an unsorted list of all books in the index.
     */
    public List<Book> getBooks() {
        return booksIndex.stream().toList();
    }
}
