package com.weatherapp.api.impl;

import com.weatherapp.api.openweather.OpenWeatherFeignClient;
import com.weatherapp.api.openweather.dto.CurrentWeatherApiDTO;
import com.weatherapp.api.openweather.exception.OpenWeatherException;
import com.weatherapp.core.api.WeatherApi;
import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Implementation of the core {@link WeatherApi} interface.
 */
@Component
public class WeatherApiImpl implements WeatherApi {

    public static final String METRIC = "metric";

    @Value("${api.openweather.key}")
    private String openWeatherKey;

    @Autowired
    private OpenWeatherFeignClient openWeatherClient;

    /**
     * Finds current weather data for a location.
     */
    @Override
    public CurrentWeatherDTO getCurrentWeatherByLocation(String location) throws WeatherApiException {
        try {
            // Calls the Open Weather api to find current weather data
            CurrentWeatherApiDTO apiDTO = openWeatherClient.currentWeatherData(location, METRIC, openWeatherKey);

            // Translates the response into the core DTO
            CurrentWeatherDTO responseDTO = CurrentWeatherDTO.builder()
                    .temperature(apiDTO.getMain().getTemp())
                    .temperatureFeelsLike(apiDTO.getMain().getFeels_like())
                    .temperatureMin(apiDTO.getMain().getTemp_min())
                    .temperatureMax(apiDTO.getMain().getTemp_max())
                    .humidity(apiDTO.getMain().getHumidity())
                    .cloudPercentage(apiDTO.getClouds().getAll())
                    .description( (apiDTO.getWeather() == null || apiDTO.getWeather().isEmpty()) ? null : apiDTO.getWeather().get(0).getMain())
                    .build();

            return responseDTO;
        } catch (OpenWeatherException e) {
            // Throw core exceptions for possible error scenarios
            if (e.getResponseCode() == HttpStatus.NOT_FOUND.value()) {
                throw new WeatherApiLocationNotFoundException(e);
            } else {
                throw new WeatherApiException(e);
            }
        }

    }
}
