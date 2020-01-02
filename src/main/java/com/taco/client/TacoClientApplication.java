package com.taco.client;

import com.taco.client.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TacoClientApplication {

    private static final Logger logger = LoggerFactory.getLogger(TacoClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TacoClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            logger.info(quote.toString());
        };
    }

}
