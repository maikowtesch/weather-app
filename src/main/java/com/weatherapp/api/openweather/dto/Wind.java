package com.weatherapp.api.openweather.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class composes the DTO {@link CurrentWeatherApiDTO}.
 */
@Getter
@Setter
public class Wind {
    private float speed;
    private long deg;
}
