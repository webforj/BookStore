package com.webforj.bookstore.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.PropertySourceProcessor;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class YamlPropertySourceFactory implements PropertySourceFactory {
    /**
     * Create a {@link PropertySource} that wraps the given resource.
     * <p>Implementations will typically create {@link ResourcePropertySource}
     * instances, with {@link PropertySourceProcessor} automatically adapting property source names
     * via {@link ResourcePropertySource#withResourceName()} if necessary, e.g. when combining
     * multiple sources for the same name into a
     * {@link org.springframework.core.env.CompositePropertySource}. Custom implementations with
     * custom {@link PropertySource} types need to make sure to expose distinct enough names,
     * possibly deriving from {@link ResourcePropertySource} where possible.
     *
     * @param name the name of the property source (can be {@code null} in which case the
     *     factory implementation will have to generate a name based on the given resource)
     * @param resource the resource (potentially encoded) to wrap
     * @return the new {@link PropertySource} (never {@code null})
     * @throws IOException if resource resolution failed
     */
    @Override
    public @NonNull PropertySource<?> createPropertySource(@Nullable String name, @NonNull EncodedResource resource)
        throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        Properties properties = factory.getObject();
        assert properties != null;
        return new PropertiesPropertySource(Objects.requireNonNull(resource.getResource()
            .getFilename()), properties);
    }
}
