package com.webforj.bookstore.error;

import com.google.auto.service.AutoService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.error.ErrorHandler;

/**
 * ConceiverExceptionErrorHandler
 *
 * @author Kevin Hagel
 * @since Dec 16, 2024
 */
@AutoService(ErrorHandler.class)
@WebforjSpringComponent
public class ConceiverExceptionErrorHandler extends AbstractBookstoreErrorHandler {
}
