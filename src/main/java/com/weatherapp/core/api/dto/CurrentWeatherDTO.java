package com.weatherapp.core.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Core DTO which is part of the contract in the {@link com.weatherapp.core.api.WeatherApi) interface.
 */
@Getter
@Setter
@Builder
public class CurrentWeatherDTO {
    private float temperature;
    private float temperatureFeelsLike;
    private float temperatureMax;
    private float temperatureMin;
    private long humidity;
    private long cloudPercentage;
    private String description;
}
