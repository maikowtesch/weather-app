package com.weatherapp.core.api.exception;

/**
 * General exception when calling {@link com.weatherapp.core.api.WeatherApi} implementations.
 */
public class WeatherApiException extends Exception {
    public WeatherApiException() {
        super();
    }

    public WeatherApiException(Exception e) {
        super(e);
    }
}
