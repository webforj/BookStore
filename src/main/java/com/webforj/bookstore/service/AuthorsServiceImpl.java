package com.webforj.bookstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webforj.bookstore.data.DataLoader;
import com.webforj.bookstore.repository.AuthorDto;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * AuthorsServiceImpl
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DataLoader dataLoader;


    public AuthorsServiceImpl(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public Set<AuthorDto> getAllAuthors() throws IOException {
        return dataLoader.getJsonAuthors().stream()
          .map(jsonAuthor ->
            AuthorDto.builder()
              .id(jsonAuthor.getId())
              .name(jsonAuthor.getName())
              .nationality(jsonAuthor.getNationality())
              .notableWorks(jsonAuthor.getNotableWorks())
              .fullName(jsonAuthor.getFullName())
              .birthName(jsonAuthor.getBirthName())
              .birthDate(jsonAuthor.getDateOfBirth())
              .deathDate(jsonAuthor.getDateOfDeath())
              .build()
          )
          .collect(Collectors.toSet());
    }



}
