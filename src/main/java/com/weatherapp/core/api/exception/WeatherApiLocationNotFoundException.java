package com.weatherapp.core.api.exception;

/**
 * Exception used when the {@link com.weatherapp.core.api.WeatherApi} implementation can't find a location.
 */
public class WeatherApiLocationNotFoundException extends WeatherApiException {
    public WeatherApiLocationNotFoundException() {
        super();
    }

    public WeatherApiLocationNotFoundException(Exception e) {
        super(e);
    }
}
