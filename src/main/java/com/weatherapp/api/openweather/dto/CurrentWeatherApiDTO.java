package com.weatherapp.api.openweather.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for the Current Weather Data endpoint (from the Open Weather API).
 */
@Getter
@Setter
public class CurrentWeatherApiDTO {
    private Coordinates coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
}