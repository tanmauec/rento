package com.rento;

import com.rento.core.async.AsyncUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executors;

@SpringBootApplication
@EnableSwagger2
public class RentoApp {
    public static void main( String[] args ) {
        AsyncUtils.init(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4));
        SpringApplication.run(RentoApp.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.rento")).build();
    }

}
