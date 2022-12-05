package io.cartin.backend;

import io.cartin.backend.models.*;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
        }
)
public class BackendApplication implements RepositoryRestConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("1.6.13") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Cartin Backend").version(appVersion));
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        config.exposeIdsFor(CartinUser.class);
        config.exposeIdsFor(Order.class);
        config.exposeIdsFor(OrderItems.class);
        config.exposeIdsFor(OrderStatus.class);
        config.exposeIdsFor(Cart.class);
        config.exposeIdsFor(CartItem.class);
        config.exposeIdsFor(Role.class);






    }

}
