package com.example.springbootidstar.utility;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${spring.profiles.active:}")
    private String activeProfiles;

    @Bean
    public OpenAPI springShopOpenAPI() {
        // TODO: fix configuration
        return new OpenAPI()
                .info(new Info()
                        .title("idstar")
                        .version(activeProfiles + "-1")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Service Documentation")
                        .url("asdasd"));
    }
}
