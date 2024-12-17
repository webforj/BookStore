package com.webforj.bookstore.views;

import com.webforj.PendingResult;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Component;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import com.webforj.router.annotation.RouteAlias;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DashboardView is the 'home' view.
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Route(value = "dashboard", outlet = MainLayout.class)
@RouteAlias(value = "/")
@FrameTitle("Dashboard")
public class DashboardView extends Composite<Div> {
    Logger log = LogManager.getLogger(getClass());

    public DashboardView() {
        log.info("DashboardView constructor");
        Div content = getBoundComponent();
        content.add(new H1("Dashboard"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PendingResult<Component> whenAttached() {
        return super.whenAttached();
    }
}
