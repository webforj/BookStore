package com.webforj.bookstore.views;

import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.router.annotation.Route;
import lombok.extern.log4j.Log4j2;

/**
 * SettingsView
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Log4j2
@Route(outlet = DashboardView.class)
public class SettingsView extends Composite<Div> {

    public SettingsView() {
        getBoundComponent().add(new H1("Settings Content"));
    }
}
