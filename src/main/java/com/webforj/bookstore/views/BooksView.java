package com.webforj.bookstore.views;

import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.router.annotation.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BooksView
 *
 * @author Kevin Hagel
 * @since Dec 12, 2024
 */
@WebforjSpringComponent
@Route(value = "books", outlet = MainLayout.class)
public class BooksView extends Composite<Div> {

    Logger log = LogManager.getLogger(getClass());


    public BooksView() {
        log.atInfo().withLocation().log("Books constructor");
        Div content = getBoundComponent();
        content.add(new H1("BooksView"));
    }

}
