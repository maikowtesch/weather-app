package com.weatherapp.core.service;

import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;

/**
 * Core interface that represents the weather service operations.
 */
public interface WeatherService {
    /**
     * Finds information about the current weather in a given location.
     * @param location City name and country code separated by comma. Possible values "<city name>" or "<city name>,<country code>".
     * @return {@link CurrentWeatherDTO} Current weather data.
     * @throws WeatherApiException
     * @throws WeatherApiLocationNotFoundException
     */
    CurrentWeatherDTO getCurrentWeatherInfo(String location) throws WeatherApiException, WeatherApiLocationNotFoundException;
}
