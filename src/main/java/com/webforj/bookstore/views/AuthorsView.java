package com.webforj.bookstore.views;

import static com.webforj.component.optiondialog.OptionDialog.showMessageDialog;

import com.webforj.annotation.InlineStyleSheet;
import com.webforj.bookstore.repository.Author;
import com.webforj.bookstore.repository.Book;
import com.webforj.bookstore.service.AuthorsService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.field.TextField;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;
import com.webforj.component.navigator.Navigator;
import com.webforj.component.table.Column;
import com.webforj.component.table.Table;
import com.webforj.component.table.renderer.IconRenderer;
import com.webforj.component.table.renderer.Renderer;
import com.webforj.data.Paginator;
import com.webforj.data.repository.CollectionRepository;
import com.webforj.data.repository.Repository;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;

/**
 * AuthorsView is the table displaying the authros
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Primary
@Route(value = "authors", outlet = MainLayout.class)
@FrameTitle("Authors")
@InlineStyleSheet("context://css/table/artistsTableRichContent.css")
public class AuthorsView extends Composite<Div> {

    Logger log = LogManager.getLogger(getClass());
    private final AuthorsService authorsService;
    private Repository<Author> repository;
    private Paginator paginator;
    private String searchTerm = "";
    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");


    public AuthorsView(AuthorsService authorsService) {
        this.authorsService = authorsService;
        log.atInfo().withLocation().log("Books constructor");
    }

    @PostConstruct
    public void init() throws IOException {
        var authors = authorsService.getAllAuthors();
        repository = new CollectionRepository<>(authors);
        repository.setFilter((Author author) -> {
            String authorName = author.getName();
            return authorName.toLowerCase().contains(searchTerm.toLowerCase());
        });


        Table<Book> table = new Table<>();
        table.setRowHeight(45);
        table.addClassName("table");
        paginator = new Paginator(repository);
        paginator.setSize(10);

        FlexLayout layout = FlexLayout
          .create(buildTableHeader(), buildTable(), buildTableFooter())
          .vertical()
          .contentAlign()
          .center()
          .build()
          .setPadding("var(--dwc-space-l)");

        getBoundComponent().add(layout);
    }

    FlexLayout buildTableHeader() {
        TextField search = new TextField(TextField.Type.SEARCH, "Search");
        search.setPlaceholder("Search by author ...");
        search.onModify(ev -> {
            this.searchTerm = ev.getText().toLowerCase();
            paginator.setCurrent(1);
            repository.commit();
        });

        ChoiceBox pages = new ChoiceBox("Entries per page");
        pages.insert("10", "25", "50", "100");
        pages.selectIndex(0);
        pages.onSelect(e -> {
            paginator.setSize(Integer.parseInt(e.getSelectedItem().getText()));
        });

        return FlexLayout.create(pages, search).horizontal().justify().between().build();
    }

    Table<Author> buildTable() {
        Table<Author> table = new Table<>();
        table.setHeight("450px");
        table.setWidth("85vw");
        table.setMaxHeight(450);
        table.setRowHeight(40);
        table.addClassName("table");
        table.setSelectionMode(Table.SelectionMode.SINGLE);
        table.setHeaderCheckboxSelection(false);

        table.addColumn("Author", new AuthorNameRenderer())
          .setComparator(Comparator.nullsLast(Comparator.comparing(Author::getName)))
          .setMinWidth(100f)
          .setSortable(true)
          .setPinDirection(Column.PinDirection.LEFT);

        table.addColumn("Name", Author::getName).setHidden(true);
        table.addColumn("DateOfBirth", author -> formatter.format(author.getDateOfBirth())).setHidden(true);
        table.addColumn("DateOfDeath",
          author -> author.getDateOfDeath() == null ? "" : formatter.format(author.getDateOfDeath())).setHidden(true);


        table.addColumn("full_name", Author::getFullName)
          .setLabel("Full Name")
          .setMinWidth(100f)
          .setSortable(true)
          .setPinDirection(Column.PinDirection.LEFT);
        table.addColumn("nationality", Author::getNationality)
          .setLabel("Nationality")
          .setMinWidth(100f)
          .setSortable(true)
          .setPinDirection(Column.PinDirection.LEFT);

        IconRenderer<Author> viewIconRender = new IconRenderer<>("eye-up", event -> {
            showMessageDialog("You asked to view the author "+ event.getItem().getName());
        });

        table.addColumn(viewIconRender)
          .setPinDirection(Column.PinDirection.LEFT)
          .setMinWidth(50f)
          .setSortable(false);



        table.setRepository(repository);

        return table;
    }

    private String formatNameWithDates(Author author) {
        String dateOfbirth = formatter.format(author.getDateOfBirth());
        Date dateOfDeath = author.getDateOfDeath();
        String span = "";
        if (dateOfDeath == null) {
            span = " (born: " + dateOfbirth + ")";
        } else {
            span = " (" + dateOfbirth + " - " + formatter.format(dateOfDeath) + ")";
        }
        return author.getName() + span;
    }

    /**
     * Builds  footer, holding the navigator stuff.
     *
     * @return a flex layout.
     */
    FlexLayout buildTableFooter() {
        Navigator pages = new Navigator(paginator, Navigator.Layout.PAGES);
        pages.setAutoDisable(true);

        Navigator preview = new Navigator(paginator, Navigator.Layout.PREVIEW);
        preview.setHideMainButtons(true);
        preview.setStyle("border", "0");
        preview.setText("`Showing ${startIndex + 1} to ${endIndex + 1} of ${totalItems} entries`");

        return FlexLayout.create(pages, preview).horizontal().justify().between().build();
    }



}


class AuthorNameRenderer extends Renderer<Author> {
    /**
     * Build the renderer expression to be executed in the client.
     *
     * @return the expression to be executed in the client
     */
    @Override
    public String build() {
        return /* html */"""
            <%
            const name = cell.row.getValue('Name');
            const dateOfBirth = cell.row.getValue('DateOfBirth');
            const dateOfDeath = cell.row.getValue('DateOfDeath');
            var  span = "(" + dateOfBirth + " - " + dateOfDeath + ")";
            if( !dateOfDeath ) {
            console.log("date of death is null");
               span = " (born " + dateOfBirth + ")"
            }
            console.log("dateOfDeath " + dateOfDeath);
            %>
            <div part="avatar-renderer">
              <div part="avatar-text">
               <span><b><%= name %></b></span>
               <div part="avatar-subtext">
                  <span part="badge badge-low"><%= span %></span>
                </span>
                </div>
              </div>
            </div>
          """;
    }
}
