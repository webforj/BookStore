package com.webforj.bookstore.error;

import com.google.auto.service.AutoService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.error.ErrorHandler;

/**
 * WebforjExceptionErrorHandler handles {@link com.webforj.exceptions.WebforjException} errors.
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@AutoService(ErrorHandler.class)
@WebforjSpringComponent
public class WebforjExceptionErrorHandler extends AbstractBookstoreErrorHandler {
}
