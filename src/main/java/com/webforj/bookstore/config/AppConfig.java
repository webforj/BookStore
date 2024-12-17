package com.webforj.bookstore.config;

import com.webforj.bookstore.data.DataLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * AppConfig
 *
 * @author Kevin Hagel
 * @since Dec 12, 2024
 */
@Configuration
@PropertySource(value = "classpath:/application.yml", factory = YamlPropertySourceFactory.class)
@EnableAutoConfiguration()
@ComponentScan(basePackages = {
  "com.webforj.bookstore.views",
  "com.webforj.bookstore.repository",
  "com.webforj.bookstore.data",
  "com.webforj.bookstore.service",
  "com.webforj.bookstore.routes"})
public class AppConfig {
    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    DataLoader dataLoader;

    public AppConfig() {
        System.out.println("AppConfig constructor");
    }
}
