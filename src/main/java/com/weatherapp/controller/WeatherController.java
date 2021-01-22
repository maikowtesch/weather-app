package com.weatherapp.controller;

import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;
import com.weatherapp.core.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements the endpoints for the weather app.
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    public static final String CITY_NOT_FOUND = "CITY_NOT_FOUND";
    @Autowired
    private WeatherService weatherService;

    /**
     * Finds current weather for a location.
     * @param location City name and country code separated by comma. Possible values "<city name>" or "<city name>,<country code>".
     * @return {@link CurrentWeatherDTO} Current weather data.
     */
    @GetMapping(value="/current", produces="application/json")
    public ResponseEntity<Object> current(@RequestParam("location") String location) {
        try {
            return ResponseEntity.ok(this.weatherService.getCurrentWeatherInfo(location));
        } catch (WeatherApiLocationNotFoundException e) {
            return new ResponseEntity<>(CITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (WeatherApiException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
