package com.webforj.bookstore.views;

import com.webforj.bookstore.repository.Book;
import com.webforj.bookstore.service.BookService;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Composite;
import com.webforj.component.field.TextField;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;
import com.webforj.component.navigator.Navigator;
import com.webforj.component.table.Column;
import com.webforj.component.table.Table;
import com.webforj.data.Paginator;
import com.webforj.data.repository.CollectionRepository;
import com.webforj.data.repository.Repository;
import com.webforj.router.annotation.Route;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
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
    private final BookService bookService;
    private Repository<Book> repository;
    private Paginator paginator;
    private String searchTerm = "";


    public BooksView(BookService bookService) {
        this.bookService = bookService;
        log.atInfo().withLocation().log("Books constructor");
    }

    @PostConstruct
    public void init() throws IOException {
        var books = bookService.getAllBooks();
        repository = new CollectionRepository<>(books);
        Table<Book> table = new Table<>();
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
        search.setPlaceholder("Search by athlete ...");
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

    Table<Book> buildTable() {
        Table<Book> table = new Table<>();
        table.setHeight("400px");
        table.setSelectionMode(Table.SelectionMode.SINGLE);
        table.setHeaderCheckboxSelection(false);


        table.addColumn("title", Book::getTitle)
          .setLabel("Title")
          .setSortable(true);
        table.addColumn("author", Book::getAuthor)
          .setLabel("Author")
          .setSortable(true);
        table.addColumn("isbn", Book::getIsbn)
          .setLabel("ISBN")
          .setSortable(false);
        table.addColumn("publication_date", Book::getPublicationDate)
          .setLabel("Publication Date")
          .setMinWidth(150f)
          .setSortable(false);
        table.addColumn("language", Book::getLanguage)
          .setLabel("Language")
          .setSortable(false);
        table.addColumn("publisher", Book::getPublisher)
          .setLabel("Publisher")
          .setSortable(false);


        table.getColumnById("title").setPinDirection(Column.PinDirection.LEFT).setMinWidth(300f);
        table.getColumnById("author").setPinDirection(Column.PinDirection.LEFT).setMinWidth(200f);
        table.getColumnById("publication_date").setPinDirection(Column.PinDirection.AUTO);
        table.getColumnById("isbn").setPinDirection(Column.PinDirection.AUTO);
        table.getColumnById("publisher").setPinDirection(Column.PinDirection.AUTO);
        table.getColumnById("language").setPinDirection(Column.PinDirection.AUTO);

        table.setRepository(repository);

        return table;
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
