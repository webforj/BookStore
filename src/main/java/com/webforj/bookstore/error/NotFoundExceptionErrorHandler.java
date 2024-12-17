package com.webforj.bookstore.error;

import com.google.auto.service.AutoService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.error.ErrorHandler;
import com.webforj.router.exception.NotFoundException;

/**
 * NotFoundExceptionErrorHandler handles the {@link NotFoundException} error logging.
 *
 * @author Kevin Hagel
 * @see AutoService
 * @see NotFoundException
 * @since Dec 14, 2024
 */
@AutoService(ErrorHandler.class)
@WebforjSpringComponent
public class NotFoundExceptionErrorHandler extends AbstractBookstoreErrorHandler {

}
