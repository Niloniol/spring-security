package web.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

import java.util.Properties;

@EnableRetry
@EnableCaching
@SpringBootApplication
public class AppInit extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebConfig.class)
                .properties(getProperties());
    }

    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", "classpath:application.properties");
        return props;
    }
}