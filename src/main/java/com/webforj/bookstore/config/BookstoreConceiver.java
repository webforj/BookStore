package com.webforj.bookstore.config;

import com.google.auto.service.AutoService;
import com.google.common.base.Stopwatch;
import com.webforj.bookstore.BookStoreApplication;
import com.webforj.conceiver.Conceiver;
import com.webforj.conceiver.DefaultConceiver;
import com.webforj.conceiver.exception.ConceiverException;
import io.vavr.control.Try;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BookstoreConceiver is a {@link Conceiver} implementation which allows us to create our spring factory outside the App * child, injecting
 * our app child and others.
 *
 * @author Kevin Hagel
 * @see AutoService
 * @since Dec 12, 2024
 */
@AutoService(Conceiver.class)
@Getter
public class BookstoreConceiver extends DefaultConceiver {

    private final Logger log = LogManager.getLogger(BookstoreConceiver.class);

    public BookstoreConceiver(AnnotationConfigApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * -- GETTER -- Return the application context created in this conceiver.
     */
    AnnotationConfigApplicationContext applicationContext;

    public BookstoreConceiver() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.atInfo().withLocation().log("Creating application context ... registering AppConfig and BookStoreApplication");
        this.applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, BookStoreApplication.class);
        log.atInfo().withLocation().log("finished creating application context0 total Time {}", stopwatch.stop());
    }

    /**
     * Get the instance of the given class, in our implementation have it pass through the spring application context.
     *
     * @param classOfT the class.
     * @return the instance of the class.
     * @throws ConceiverException if an error occurs while creating the instance.
     */
    @Override
    public <T> T get(Class<T> classOfT) throws ConceiverException {
        log.atInfo().withLocation().log("{}.get() - classOfT: {}", getClass().getSimpleName(), classOfT);
        return Try
          .of(() -> applicationContext.getBean(classOfT))
          .recover(throwable -> {
              log.atError().log("get bean of class {} failed, attempting recovery {}", classOfT.getName(), throwable);
              try {
                  AbstractBeanDefinition bd =
                    BeanDefinitionReaderUtils.createBeanDefinition(null, classOfT.getName(),
                      getClass().getClassLoader());
                  applicationContext.registerBeanDefinition(classOfT.getName(), bd);
                  return applicationContext.getBean(classOfT);
              } catch (ClassNotFoundException e) {
                  throw new RuntimeException(e);
              }
          })
          .getOrElseThrow(t -> new ConceiverException("Could not get Bean " + classOfT, t));
    }

}
