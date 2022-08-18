package br.com.erudio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.favorParameter(true) // Define aceitar parametros
        .parameterName("mediaType") //nome do parametro
                .ignoreAcceptHeader(true)// Ignora parametros do Header
                .useRegisteredExtensionsOnly(false) // Permite o uso de extensões não registradas
                .defaultContentType(MediaType.APPLICATION_JSON) // tipo padrão de media type
                .mediaType("json",MediaType.APPLICATION_JSON)
                .mediaType("xml",MediaType.APPLICATION_XML);
        WebMvcConfigurer.super.configureContentNegotiation(configurer);
    }
}
