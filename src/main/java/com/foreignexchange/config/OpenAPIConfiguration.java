package com.foreignexchange.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Value("${service.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("selcukegeli.tr@gmail.com");
        contact.setName("segeli");

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Info info = new Info()
                .title("Foreign Exchange Service API")
                .description("Foreign Exchange Service API")
                .version("1.0")
                .contact(contact);
        return new OpenAPI()
                .info(info);
    }
}
