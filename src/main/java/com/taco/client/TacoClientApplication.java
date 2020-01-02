package com.taco.client;

import com.taco.client.model.Quote;
import com.taco.client.model.tacocloud.Ingredient;
import com.taco.client.model.tacocloud.Taco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import org.springframework.hateoas.*;

import java.util.Collection;

@SpringBootApplication
public class TacoClientApplication {

    private static final Logger logger = LoggerFactory.getLogger(TacoClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TacoClientApplication.class, args);
    }


    /**
     * HATEOAS >= v1.0
     * Spring boot >= 2.2.0
     * ResourceSupport changed to RepresentationModel
     * Resource changed to EntityModel
     * Resources changed to CollectionModel
     * PagedResources changed to PagedModel
     * ResourceAssembler changed to RepresentationModelAssembler
     */
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, Traverson traverson) {
        return args -> {
            //Using RestTemplate
            Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            logger.info(quote.toString());


            //Using Traverson
            ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                    new ParameterizedTypeReference<CollectionModel<Ingredient>>() {
                    };

            CollectionModel<Ingredient> ingredientRes =
                    traverson
                            .follow("ingredients")
                            .toObject(ingredientType);

            Collection<Ingredient> ingredients = ingredientRes.getContent();
            logger.info(ingredients.toString());
        };
    }
}
