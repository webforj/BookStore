package com.webforj.bookstore.views;

import com.webforj.bookstore.repository.AuthorDto;
import com.webforj.bookstore.service.AuthorsService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.navigator.Navigator;
import com.webforj.component.table.Table;
import com.webforj.data.repository.CollectionRepository;
import com.webforj.data.repository.Repository;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.annotation.Primary;

/**
 * AuthorsView
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Primary
@Route(value = "authors", outlet = MainLayout.class)
@FrameTitle("Authors")
public class AuthorsView extends Composite<Div> {

    private final AuthorsService authorsService;



    public AuthorsView(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }


    @PostConstruct
    public void init() throws IOException {
        var authors = authorsService.getAllAuthors();
        Repository<AuthorDto> repo = new CollectionRepository<>(authors);



        Navigator nav = new Navigator(repo, Navigator.Layout.PAGES);


        nav.setLayout(Navigator.Layout.PREVIEW);
        nav.setAutoDisable(true);
        nav.getPaginator().setMax(5);
        nav.setStyle("margin-right", "20px");

        Table<AuthorDto> table = new Table<>();
        table.setHeight("400px");

        table.addColumn("id", AuthorDto::getId);
        table.addColumn("name", AuthorDto::getName);
        table.addColumn("Dates", this::lifeString);

        table.addColumn("notable works", AuthorDto::getNotableWorks);

        table.setRepository(repo);

        FlexLayout layout = FlexLayout.create(table, nav).vertical().build();
        layout.setItemAlignment(FlexAlignment.END, nav);

        getBoundComponent().add(layout);
    }

    protected String lifeString(AuthorDto authorDto) {
        Date dateOfbirth = authorDto.getBirthDate();
        Date dateOfDeath = authorDto.getDeathDate();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        if (dateOfDeath == null) {
            return "(born " + formatter.format(dateOfbirth) + ")";
        } else {
            return "(" + formatter.format(dateOfbirth) + " - " + formatter.format(dateOfDeath) + ")";
        }
    }
}
