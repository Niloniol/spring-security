package web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import web.repository.RoleRepository;
import web.repository.UserRepository;

@Configuration
@ComponentScan("web")
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, RoleRepository.class})
@PropertySource("classpath:application.properties")
@EntityScan("web.model")
public class WebConfig implements WebMvcConfigurer {

}