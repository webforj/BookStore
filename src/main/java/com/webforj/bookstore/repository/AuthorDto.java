package com.webforj.bookstore.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

/**
 * DTO for {@link Author}
 */
@Data
@Builder
public class AuthorDto implements Serializable {
    String id;
    String name;
    String fullName;
    String birthName;
    String nationality;
    Date birthDate;
    Date deathDate;
    private List<String> notableWorks;

    public AuthorDto(String id, String name, String fullName, String birthName, String nationality, Date birthDate,
      Date deathDate, List<String> notableWorks) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.birthName = birthName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.notableWorks = notableWorks;
    }
}
