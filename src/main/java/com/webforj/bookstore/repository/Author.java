package com.webforj.bookstore.repository;

import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Author is an author of a book.  This is a representation of an author which is indexed.
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
@PersistenceConfig(polymorphic = true, serializer = AuthorSerializer.class)
public class Author {



    @Id
    private String id;
    private String name;
    private String penName;
    private String fullName;
    private Date dateOfBirth;
    private Date dateOfDeath;
    protected String nationality;
    private List<String> languages = new ArrayList<>();
    private List<String> impacts = new ArrayList<>();
    private List<String> influences = new ArrayList<>();
    private List<String> professions = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<String> publishers = new ArrayList<>();



}
