package com.webforj.bookstore;

import com.webforj.App;
import com.webforj.annotation.AppEntry;
import com.webforj.annotation.AppTitle;
import com.webforj.annotation.Routify;
import com.webforj.bookstore.data.DataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

/**
 * App subchild for the bookstore application.  We are mixing spring and webforj here.
 */
@SpringBootApplication(exclude = {FlywayAutoConfiguration.class}, scanBasePackageClasses = DataLoader.class)
@AppEntry
@AppTitle("Book Store")
@Routify(
  packages = {
    "com.webforj.bookstore.views",
    "com.webforj.bookstore.routes"
  },
  defaultFrameName = "MainFrame",
  manageFramesVisibility = false,
  initializeFrame = true,
  debug = true
)

public class BookStoreApplication extends App {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }
}
