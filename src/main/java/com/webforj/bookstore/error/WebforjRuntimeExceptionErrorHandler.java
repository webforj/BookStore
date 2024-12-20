package com.webforj.bookstore.error;

import com.google.auto.service.AutoService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.error.ErrorHandler;
import com.webforj.exceptions.WebforjRuntimeException;

/**
 * WebforjRuntimeExceptionErrorHandler handles WebforjRuntimeExceptionErrorHandler error logging.
 *
 * @author Kevin Hagel
 * @see WebforjRuntimeException
 * @see AutoService
 * @since Dec 13, 2024
 */
@AutoService(ErrorHandler.class)
@WebforjSpringComponent
public class WebforjRuntimeExceptionErrorHandler extends AbstractBookstoreErrorHandler {

}
