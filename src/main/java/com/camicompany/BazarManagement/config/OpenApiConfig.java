package com.camicompany.BazarManagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI bazarOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bazar Management API")
                        .version("1.0.0")
                        .description(
                                "REST API for managing products, customers, and sales in a bazar. Developed with Spring Boot 4, following best practices, robust validations, and professional error handling. Integrative project for TodoCode Academy."));
    }
}
