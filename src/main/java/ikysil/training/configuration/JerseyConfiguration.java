package ikysil.training.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Configuration
@ApplicationPath("ws")
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        // Register Filters
        register(RequestContextFilter.class);

        final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        // Scan for @Provider & @Path under these packages
        packages(scanner, "ikysil.training");
    }

    /**
     * Jersey doesn't always work with Spring Boot fat jars, see the issue
     * <a href="https://github.com/spring-projects/spring-boot/issues/1468#issuecomment-280416338">spring-projects/spring-boot#1468</a>.
     */
    private void packages(ClassPathScanningCandidateComponentProvider scanner, String... packageNames) {
        for (String basePackage : packageNames) {
            registerClasses(scanner.findCandidateComponents(basePackage).stream()
                    .map(beanDefinition -> ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), getClassLoader()))
                    .collect(Collectors.toSet()));
        }
    }

}
