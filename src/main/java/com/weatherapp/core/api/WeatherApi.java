package com.weatherapp.core.api;

import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;

/**
 * Core interface for implementations of the weather api.
 */
public interface WeatherApi {
    /**
     * Finds current weather data for a location.
     * @param location City name and country code separated by comma. Possible values "<city name>" or "<city name>,<country code>".
     * @return {@link CurrentWeatherDTO} Current weather data.
     * @throws WeatherApiException
     * @throws WeatherApiLocationNotFoundException
     */
    CurrentWeatherDTO getCurrentWeatherByLocation(String location) throws WeatherApiLocationNotFoundException, WeatherApiException;
}
