package com.webforj.bookstore.views;

import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import jakarta.annotation.PostConstruct;

/**
 * GenresView
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Route(value = "genres", outlet = MainLayout.class)
@FrameTitle("Genres")
public class GenresView extends Composite<Div> {

    public GenresView() {
    }


    @PostConstruct
    public void init() {
        Div content = getBoundComponent();
        content.add(new H1("GenresView"));
    }
}
