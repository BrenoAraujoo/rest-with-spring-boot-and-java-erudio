package br.com.erudio.config;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    //Para serialização do formato yml
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
      converters.add(new YamlJackson2HttpMessageConverter());
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//              IMPLEMENTAÇÃO VIA QUERY PARAM

//        configurer.favorParameter(true) // Habilita parametros via URI
//        .parameterName("mediaType") //nome do parametro
//                .ignoreAcceptHeader(true)// Não aceita parametros do Header
//                .useRegisteredExtensionsOnly(false) // Permite o uso de extensões não registradas
//                .defaultContentType(MediaType.APPLICATION_JSON) // tipo padrão de media type
//                .mediaType("json",MediaType.APPLICATION_JSON)
//                .mediaType("xml",MediaType.APPLICATION_XML);


//              IMPLEMENTAÇÃO VIA HEADER

        configurer.favorParameter(false) // Desabilita parametros via URI
                .ignoreAcceptHeader(false)// Aceita parametros do Header
                .useRegisteredExtensionsOnly(false) // Permite o uso de extensões não registradas
                .defaultContentType(MediaType.APPLICATION_JSON) // tipo padrão de media type
                    .mediaType("json",MediaType.APPLICATION_JSON)
                    .mediaType("xml",MediaType.APPLICATION_XML)
                    .mediaType("x-yaml",MEDIA_TYPE_APPLICATION_YML);

    }
}
