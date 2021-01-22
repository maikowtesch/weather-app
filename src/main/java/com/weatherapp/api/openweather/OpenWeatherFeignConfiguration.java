package com.weatherapp.api.openweather;

import feign.codec.ErrorDecoder;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;

/**
 * General Feign configuration.
 */
public class OpenWeatherFeignConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new OpenWeatherErrorDecoder();
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
