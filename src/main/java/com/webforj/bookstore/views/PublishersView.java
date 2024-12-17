package com.webforj.bookstore.views;

import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.router.annotation.Route;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

/**
 * PublishersView
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Route(value = "publishers", outlet = MainLayout.class)
public class PublishersView extends Composite<Div> {

    Logger log = LogManager.getLogger(getClass());

    public PublishersView() {
        log.atInfo().withLocation().log("PublishersView constructor");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.atInfo().log("PublishersView onDestroy");
    }

    @PostConstruct
    public void init() {
        log.atInfo().log("PublishersView init");
        Div content = getBoundComponent();
        content.add(new H1("PublishersView"));
    }
}
