package com.kaibacorp.figmabackend.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info =
@Info(title = "Figma-API",
        version = "v1",
        description = "Aplicação feita para manipular Cargos e Setores no banco de dados"))
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Figma-API")
                                .version("v1")
                                .license( new License().
                                        name("Apache 2.0").
                                        url("http://springdoc.org")
                                )
                );
    }
}
