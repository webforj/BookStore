package com.webforj.bookstore.repository;


import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Book is a book.  It has one or more authors, a publisher, 0, 1, or many reviews.
 *
 * @author Kevin Hagel
 * @since Dec 12, 2024
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@PersistenceConfig(polymorphic = true, serializer = BookSerializer.class)
public class Book implements Comparable<Book> {

    @Id
    private String id;
    private String author;
    private String title;
    private String language;
    private List<String> genres = new ArrayList<>();
    private String publisher;
    private String isbn;
    private String publicationDate;
    private String notes;

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *   the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     *   {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements the
     *   {@code Comparable} interface and violates this condition should clearly indicate this fact.  The recommended
     *   language is "Note: this class has a natural ordering that is inconsistent with equals."
     */
    @Override
    public int compareTo(@Nonnull Book other) {
        Objects.requireNonNull(other, "Book cannot be null");
        return getTitle().compareTo(other.getTitle());
    }
}
