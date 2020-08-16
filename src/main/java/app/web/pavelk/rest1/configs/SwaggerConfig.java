package app.web.pavelk.rest1.configs;

import app.web.pavelk.rest1.entities.Rover;
import app.web.pavelk.rest1.repositories.RoverRepo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {
    @Bean
    public CommandLineRunner loadService(RoverRepo roverRepo){
        return args -> {
            log.info("Start load data!");
            log.info("Data={}", roverRepo.save(new Rover("r1", "m1")));
            log.info("Data={}", roverRepo.save(new Rover("r2", "m2")));
            log.info("Data={}", roverRepo.save(new Rover("r3", "m3")));
//            roverRepo.save(new Rover("r1", "m1"));
            log.info("Data was loaded");

        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("app.web.pavelk.rest1.controllers")) //для кого
                .paths(PathSelectors.regex("/api.*")) //для пути
                .build();
    }
}
