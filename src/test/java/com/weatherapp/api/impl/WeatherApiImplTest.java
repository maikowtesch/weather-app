package com.weatherapp.api.impl;

import com.weatherapp.api.openweather.OpenWeatherFeignClient;
import com.weatherapp.api.openweather.dto.*;
import com.weatherapp.api.openweather.exception.OpenWeatherException;
import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class WeatherApiImplTest {
    @Mock
    private OpenWeatherFeignClient openWeatherClient;

    @InjectMocks
    private WeatherApiImpl weatherApi;

    @Test
    public void getCurrentWeatherByLocationSuccess() throws WeatherApiException {

        CurrentWeatherApiDTO testDTO = createOpenWeatherDTO();

        when(openWeatherClient.currentWeatherData(anyString(), anyString(), ArgumentMatchers.isNull())).thenReturn(testDTO);

        CurrentWeatherDTO result = weatherApi.getCurrentWeatherByLocation("location");

        assertEquals(testDTO.getMain().getTemp(), result.getTemperature(), 0);
        assertEquals(testDTO.getMain().getFeels_like(), result.getTemperatureFeelsLike(), 0);
        assertEquals(testDTO.getMain().getTemp_min(), result.getTemperatureMin(), 0);
        assertEquals(testDTO.getMain().getTemp_max(), result.getTemperatureMax(), 0);
        assertEquals(testDTO.getMain().getHumidity(), result.getHumidity());
        assertEquals(testDTO.getClouds().getAll(), result.getCloudPercentage());
        assertEquals(testDTO.getWeather().get(0).getMain(), result.getDescription());

        verify(openWeatherClient, times(1)).currentWeatherData(anyString(), anyString(), ArgumentMatchers.isNull());
    }

    @Test(expected = WeatherApiLocationNotFoundException.class)
    public void getCurrentWeatherByLocationLocationNotFoundException() throws WeatherApiException {

        when(openWeatherClient.currentWeatherData(anyString(), anyString(), ArgumentMatchers.isNull()))
                .thenThrow(new OpenWeatherException(HttpStatus.NOT_FOUND.value()));

        weatherApi.getCurrentWeatherByLocation("location");
    }

    @Test(expected = WeatherApiException.class)
    public void getCurrentWeatherByLocationApiException() throws WeatherApiException {

        when(openWeatherClient.currentWeatherData(anyString(), anyString(), ArgumentMatchers.isNull()))
                .thenThrow(new OpenWeatherException(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        weatherApi.getCurrentWeatherByLocation("location");
    }

    private CurrentWeatherApiDTO createOpenWeatherDTO() {
        Coordinates coord = new Coordinates();
        coord.setLat(45);
        coord.setLon(50);

        Weather weather = new Weather();
        weather.setId(100);
        weather.setMain("Cloudy");
        weather.setDescription("super cloudy");
        weather.setIcon("1ff");

        Main main = new Main();
        main.setTemp_min(19);
        main.setTemp(20);
        main.setTemp_max(21);
        main.setFeels_like(22);
        main.setHumidity(50);
        main.setPressure(100);

        Wind wind = new Wind();
        wind.setDeg(15);
        wind.setSpeed(5);

        Clouds clouds = new Clouds();
        clouds.setAll(30);

        CurrentWeatherApiDTO dto = new CurrentWeatherApiDTO();
        dto.setCoord(coord);
        dto.setWeather(Collections.singletonList(weather));
        dto.setMain(main);
        dto.setWind(wind);
        dto.setClouds(clouds);

        return dto;
    }
}
