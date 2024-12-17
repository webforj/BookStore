package com.webforj.bookstore.views;

import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.router.Router;
import com.webforj.router.annotation.Route;
import com.webforj.router.history.Location;
import com.webforj.router.history.ParametersBag;
import java.util.Random;

/**
 * ProductView
 *
 * @author Kevin Hagel
 * @since Dec 12, 2024
 */
@WebforjSpringComponent
@Route(outlet = MainLayout.class)
public class ProductView extends Composite<Div> {
    Paragraph paragraph = new Paragraph();
    Random random = new Random();

    public ProductView() {
        System.out.println("ProductView constructor");
        Button update = new Button("Update URL", ButtonTheme.PRIMARY);
        update.onClick(ev -> {
            filter("electronics", String.valueOf(random.nextInt(3) - 1));
        });

        Div div = getBoundComponent();
        div.add(update);
        div.add(paragraph);


    }

    public void filter(String category, String sort) {
        // update the UI
        updateUI(category, sort);

        // update the URL
        updateUrl(category, sort);
    }

    private void updateUI(String category, String sort) {
        paragraph.setText("Viewing category: " + category + " and sorting by: " + sort);
    }

    private void updateUrl(String category, String sort) {
        ParametersBag queryParameters = new ParametersBag();
        queryParameters.put("category", category);
        queryParameters.put("sort", sort);

        Location newLocation = new Location("/products?" + queryParameters.getQueryString());
        Router.getCurrent().getHistory()
          // Update the URL without reloading the page
          .replaceState(null, newLocation);
    }
}
