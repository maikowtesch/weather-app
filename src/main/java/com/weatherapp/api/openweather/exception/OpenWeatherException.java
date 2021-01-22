package com.weatherapp.api.openweather.exception;

import lombok.Getter;

/**
 * General exception when calling the Open Weather API.
 */
@Getter
public class OpenWeatherException extends RuntimeException {
    private long responseCode;

    public OpenWeatherException(long responseCode) {
        super();
        this.responseCode = responseCode;
    }
}
