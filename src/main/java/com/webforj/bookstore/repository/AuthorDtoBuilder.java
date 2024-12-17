package com.webforj.bookstore.repository;

import java.util.Date;
import java.util.List;

public class AuthorDtoBuilder {
    private String id;
    private String name;
    private String fullName;
    private String birthName;
    private String nationality;
    private Date birthDate;
    private Date deathDate;
    private List<String> notableWorks;

    public AuthorDtoBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public AuthorDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AuthorDtoBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public AuthorDtoBuilder setBirthName(String birthName) {
        this.birthName = birthName;
        return this;
    }

    public AuthorDtoBuilder setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public AuthorDtoBuilder setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public AuthorDtoBuilder setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    public AuthorDtoBuilder setNotableWorks(List<String> notableWorks) {
        this.notableWorks = notableWorks;
        return this;
    }

    public AuthorDto createAuthorDto() {
        return new AuthorDto(id, name, fullName, birthName, nationality, birthDate, deathDate, notableWorks);
    }
}
