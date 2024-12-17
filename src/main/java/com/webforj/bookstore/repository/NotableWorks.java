package com.webforj.bookstore.repository;

import jakarta.persistence.Embeddable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * NotableWorks
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class NotableWorks {

    private List<String> notableWorks;
}
