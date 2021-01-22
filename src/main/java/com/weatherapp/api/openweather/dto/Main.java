package com.weatherapp.api.openweather.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class composes the DTO {@link CurrentWeatherApiDTO}.
 */
@Getter
@Setter
public class Main {
    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private long pressure;
    private long humidity;
}