package com.taco.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;

import java.net.URI;

@Configuration
public class HateoasConfig {

    @Bean
    public Traverson traverson() {
        return new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
    }
}
