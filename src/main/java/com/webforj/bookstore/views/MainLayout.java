package com.webforj.bookstore.views;

import com.webforj.annotation.AppMeta;
import com.webforj.bookstore.spring.stereotype.WebforjSpringComponent;
import com.webforj.component.Component;
import com.webforj.component.Composite;
import com.webforj.component.element.Element;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H3;
import com.webforj.component.html.elements.Img;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.Icon;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.applayout.AppDrawerToggle;
import com.webforj.component.layout.applayout.AppLayout;
import com.webforj.component.tabbedpane.Tab;
import com.webforj.component.tabbedpane.TabbedPane;
import com.webforj.component.tabbedpane.event.TabSelectEvent;
import com.webforj.router.RouteOutlet;
import com.webforj.router.Router;
import com.webforj.router.annotation.Route;
import com.webforj.router.history.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

/**
 * MainLayout
 *
 * @author Kevin Hagel
 * @since Dec 13, 2024
 */
@WebforjSpringComponent
@Route(type = Route.Type.LAYOUT)
@AppMeta(name = "viewport", content = "width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=no")
public class MainLayout extends Composite<AppLayout> { // implements RouteOutlet {

    private final Logger log = LogManager.getLogger(getClass());

    /**
     * getBoundComponent returns this.
     */
    AppLayout boundComponent;

    private Div header;
    private Div drawer;

    public MainLayout() {

        setHeader();
        setDrawer();
        boundComponent = getBoundComponent();
        //        boundComponent.setHeaderOffscreen(false);
        //        boundComponent.setFooterOffscreen(false);
        boundComponent.setDrawerHeaderVisible(true);
        boundComponent.setDrawerFooterVisible(true);

        boundComponent.addToDrawerTitle(new Div("Menu"));
        boundComponent.addToDrawerHeaderActions(new Element("dwc-icon-button")
          .setAttribute("name", "pin"));
        boundComponent.addToDrawerFooter(new Paragraph("All rights reserved"));

        // Header
        header.addClassName("layout__header").add(
          new AppDrawerToggle(),
          new H3("WebforJ Spring Application"));
        boundComponent.addToHeader(header);

        // Drawer
        boundComponent.addToDrawer(drawer);
        drawer.addClassName("app-layout-drawer");

        // Drawer's logo container and logo
        Div drawerLogo = new Div();
        drawerLogo.addClassName("drawer__logo")
          .add(new Img("https://documentation.webforj.com/img/webforj_icon.svg", "logo"));
        drawer.add(drawerLogo);

        // Drawer's Menu
        TabbedPane drawerMenu = new TabbedPane();
        drawerMenu.addClassName("drawer__menu");
        drawer.add(drawerMenu);

        // Setting drawer menu's attributes
        drawerMenu.setBodyHidden(false);
        drawerMenu.setBorderless(false);
        drawerMenu.setPlacement(TabbedPane.Placement.LEFT);


        // Adding tabs to drawer menu
        Icon dashboardIcon = TablerIcon.create("dashboard");
        Icon booksIcon = TablerIcon.create("books");
        Icon authorsIcon = TablerIcon.create("writing-sign");
        Icon publisherIcon = TablerIcon.create("library");
        Icon genreIcon = TablerIcon.create("brand-campaignmonitor");
        Icon ordersIcon = TablerIcon.create("shopping-cart");
        Icon userIcon = TablerIcon.create("user");
        Icon productsIcon = TablerIcon.create("box");
        Icon analyticsIcon = TablerIcon.create("chart-dots-2");

        drawerMenu.addTab(new Tab("Dashboard", dashboardIcon));
        drawerMenu.addTab(new Tab("Books", booksIcon));
        drawerMenu.addTab(new Tab("Authors", authorsIcon));
        drawerMenu.addTab(new Tab("Publishers", publisherIcon));
        drawerMenu.addTab(new Tab("Genres", genreIcon));
        drawerMenu.addTab(new Tab("Products", productsIcon));
//        drawerMenu.addTab(new Tab("Orders", ordersIcon));
//        drawerMenu.addTab(new Tab("User", userIcon));
//        drawerMenu.addTab(new Tab("Analytics", analyticsIcon));
        drawerMenu.onSelect(this::onTabChange);
    }

    private void onTabChange(TabSelectEvent ev) {
        var tab = ev.getTab();
        log.atInfo().log("onTabChange, ev = {}\ntab = {}", ev.toString(), ev.getTab());
        //        Try.run(()->{
        //            Router router = Router.getCurrent();
        //            RouteRegistry registry = router.getRegistry();
        //
        //            List<RouteEntry> routes = registry.getAvailableRouteEntires();
        //            routes.forEach(route -> log.atInfo().withLocation().log("Path: {}", route.getPath()));
        //        });


        String value = ev.getTab().getText().replaceAll("<[^>]*>", "").trim();
        System.out.println("value = " + value);
        Router router = Router.getCurrent();
        switch (value) {
            case "Dashboard" -> Router.getCurrent().navigate(DashboardView.class);
            case "Books" -> Router.getCurrent().navigate(BooksView.class);
            case "Authors" -> Router.getCurrent().navigate(AuthorsView.class);
            case "Publishers" -> Router.getCurrent().navigate(PublishersView.class);
            case "Genres" -> Router.getCurrent().navigate(GenresView.class);
            case "Products" -> Router.getCurrent().navigate(ProductView.class);
            default -> throw new IllegalStateException("Unexpected value in tab menu: " + value);
        }
        Router.getCurrent().getResolvedLocation().ifPresent(location -> {
            log.atInfo().log("onTabChange, location = {}", location);
        });
    }

    void setHeader() {
        header = new Div();
    }

    void setDrawer() {
        drawer = new Div();
    }
//
//    /**
//     * Renders the given router component.
//     *
//     * @param component the component to render.
//     */
//    @Override
//    public void showRouteContent(Component component) {
//        AppLayout layout = getBoundComponent();
//        getBoundComponent().getComponents();
//        if (!component.isDestroyed())
//            layout.addToContent(component);
//        else
//            log.atInfo().withLocation().log("showRouteContent, component is destroyed");
//    }
//
//    /**
//     * Removes the given router component.
//     *
//     * @param component the component to remove.
//     */
//    @Override
//    public void removeRouteContent(Component component) {
//        log.atInfo().withLocation().log("removeRouteContent, component is {}", component);
//        AppLayout layout = getBoundComponent();
//        layout.remove(component);
//        component.destroy();
//    }
}
