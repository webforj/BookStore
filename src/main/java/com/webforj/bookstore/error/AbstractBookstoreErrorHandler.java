package com.webforj.bookstore.error;

import com.google.auto.service.AutoService;
import com.webforj.error.ErrorHandler;
import com.webforj.error.StackTracePageBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AbstractBookstoreErrorHandler ancestor of our ErrorHandler implementations.  By putting the logging here, the
 * children are really only needed for the AutoService and the exception specification.
 * <p/>
 * The default {@link com.webforj.error.GlobalErrorHandler} logs with java.util.Logging, while our app logs with log4j,
 * this seemed to be the easiest way to take the error logging.
 *
 * @author Kevin Hagel
 * @see ErrorHandler
 * @see AutoService
 * @since Dec 14, 2024
 */
public abstract class AbstractBookstoreErrorHandler implements ErrorHandler {
    protected final Logger log = LogManager.getLogger(this.getClass());

    /**
     * Called when an error occurs.
     *
     * @param throwable the error that occurred.
     * @param debug {@code true} if debug mode is enabled, {@code false} otherwise.
     */
    @Override
    public void onError(Throwable throwable, boolean debug) {
        // Log to BBj Debug log
        log.atError()
          .withLocation()
          .withThrowable(throwable)
          .log("{}: message {}", throwable.getClass().getName(), throwable.getMessage());
        // display the error page with the stack trace
        String title = debug ? throwable.getClass().getName() : "500. That's an error";
        showErrorPage(title, StackTracePageBuilder.of(title, throwable, true));
    }
}
