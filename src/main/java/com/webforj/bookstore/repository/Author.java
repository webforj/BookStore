package com.webforj.bookstore.repository;

import com.webforj.bookstore.views.ListStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Author is an author of a book.  An author may have written 0, 1, or many books.
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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String fullName;
    private String birthName;
    private Date birthDate;
    private Date deathDate;
    private String nationality;
    private String profession;
    private List<String> notableWorks = new ArrayList<>();
    private List<Book> books = new ArrayList<>();


}
