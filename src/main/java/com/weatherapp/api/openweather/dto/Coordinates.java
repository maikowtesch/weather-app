package com.weatherapp.api.openweather.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class composes the DTO {@link CurrentWeatherApiDTO}.
 */
@Getter
@Setter
public class Coordinates {
    private float lon;
    private float lat;
}
