package com.weatherapp.core.service.impl;

import com.weatherapp.core.api.WeatherApi;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;
import com.weatherapp.core.service.WeatherService;
import com.weatherapp.core.api.dto.CurrentWeatherDTO;

/**
 * Core implementation of the {@link WeatherService).
 */
public class WeatherServiceImpl implements WeatherService {

    private WeatherApi weatherApi;

    public WeatherServiceImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    /**
     * Finds information about the current weather in a given location.
     */
    @Override
    public CurrentWeatherDTO getCurrentWeatherInfo(String location) throws WeatherApiException, WeatherApiLocationNotFoundException {
        return weatherApi.getCurrentWeatherByLocation(location);
    }
}
