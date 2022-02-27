package com.rento.configuration;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.activation.DataSource;


/**
 * The type App config.
 */
@Configuration
@ComponentScan("com.rento.*")
@Slf4j
@EnableJpaAuditing
public class AppConfig {

    /**
     * The constant client.
     */
//Already a singleton.
    //Use everywhere, it has inbuilt connection pool.
    //For customization use client.newBuilder().build();
    //Ref: https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/
    public static final OkHttpClient client = new OkHttpClient();

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
@Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        return restTemplate;
    }
}

