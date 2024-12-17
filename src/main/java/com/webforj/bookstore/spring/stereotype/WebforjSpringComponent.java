package com.webforj.bookstore.spring.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Helping to avoid confusion between {@link com.webforj.component.Component} and spring {@link Component}.  This interface has
 * &#064;Component  in it, so spring will pick it up.  Stole this idea from {@link Service}.
 * <p/>
 * Note that any class declaring this annotation will also have Scope=prototype.
 *
 * @author Kevin Hagel
 * @since Dec 12, 2024
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface WebforjSpringComponent {
}
