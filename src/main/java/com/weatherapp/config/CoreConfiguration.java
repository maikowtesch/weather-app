package com.weatherapp.config;

import com.weatherapp.core.api.WeatherApi;
import com.weatherapp.core.service.WeatherService;
import com.weatherapp.core.service.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure the Spring container with beans from the core.
 */
@Configuration
public class CoreConfiguration {

    @Bean
    @Autowired
    public WeatherService weatherService(WeatherApi weatherApi) {
        return new WeatherServiceImpl(weatherApi);
    }
}
