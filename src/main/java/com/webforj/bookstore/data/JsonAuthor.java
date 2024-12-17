package com.webforj.bookstore.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;

/**
 * JsonAuthor
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Data
public class JsonAuthor {
    private final String id = UUID.randomUUID().toString();
    private String name;
    @JsonProperty(value = "full_name", required = false)
    private String fullName;
    @JsonProperty(value = "birth_name", required = false)
    private String birthName;
    @JsonProperty(value="birth_date", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d, yyyy")
    private Date dateOfBirth;

    @JsonProperty("death_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d, yyyy")
    private Date dateOfDeath;

    private String nationality;
    private String profession;

    @JsonProperty(value = "notable_works", required = false)
    private List<String> notableWorks;
    @JsonProperty(value = "fields_of_study", required = false)
    private List<String> fieldsOfStudy;

    private List<String> genres;
    @JsonProperty(required = false)
    private String impact;
    private List<String> influences;

    public String getId() {
        return id;
    }

}
