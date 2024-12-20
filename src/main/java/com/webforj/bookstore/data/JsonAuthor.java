package com.webforj.bookstore.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
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
public class JsonAuthor implements Comparable<JsonAuthor> {
    private String id = UUID.randomUUID().toString();
    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonProperty(value = "full_name", required = false)
    private String fullName;
    @JsonProperty(value = "pen_name", required = false)
    private String penName;

    @JsonProperty(value = "dob", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d yyyy")
    private Date dateOfBirth;

    @JsonProperty(value = "dod", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d yyyy")
    private Date dateOfDeath;

    @JsonProperty(value = "impacts")
    private List<String> impacts;
    @JsonProperty(value = "influences")
    private List<String> influences;

    private String nationality;

    @JsonProperty(value = "professions")
    private List<String> professions;

    @JsonProperty(value = "languages")
    private List<String> languages;

    @JsonProperty(value = "genres", required = false)
    private List<String> genres = new ArrayList<>();

    @JsonProperty(value = "publishers", required = true)
    private List<String> publishers;


    public String getId() {
        return id;
    }

    @Override
    public int compareTo(JsonAuthor o) {
        return getName().compareTo(o.getName());
    }
}
