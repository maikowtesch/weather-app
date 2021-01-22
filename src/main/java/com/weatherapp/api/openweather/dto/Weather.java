package com.weatherapp.api.openweather.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class composes the DTO {@link CurrentWeatherApiDTO}.
 */
@Getter
@Setter
public class Weather {
    private long id;
    private String main;
    private String description;
    private String icon;
}
