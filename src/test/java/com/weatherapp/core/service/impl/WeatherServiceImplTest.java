package com.weatherapp.core.service.impl;

import com.weatherapp.core.api.WeatherApi;
import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import com.weatherapp.core.api.exception.WeatherApiException;
import com.weatherapp.core.api.exception.WeatherApiLocationNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Mock
    private WeatherApi weatherApi;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    public void currentWeatherInformationSuccess() throws WeatherApiException {

        CurrentWeatherDTO testDTO = createCurrentWeatherDTO();

        when(weatherApi.getCurrentWeatherByLocation(anyString())).thenReturn(testDTO);

        CurrentWeatherDTO result = weatherService.currentWeatherInformation("location");

        assertEquals(testDTO, result);
        verify(weatherApi, times(1)).getCurrentWeatherByLocation(any());
    }

    @Test(expected = WeatherApiLocationNotFoundException.class)
    public void currentWeatherInformationLocationNotFound() throws WeatherApiException {

        when(weatherApi.getCurrentWeatherByLocation(anyString())).thenThrow(new WeatherApiLocationNotFoundException());

        weatherService.currentWeatherInformation("location");
    }

    @Test(expected = WeatherApiException.class)
    public void currentWeatherInformationApiException() throws WeatherApiException {

        when(weatherApi.getCurrentWeatherByLocation(anyString())).thenThrow(new WeatherApiException());

        weatherService.currentWeatherInformation("location");
    }

    private CurrentWeatherDTO createCurrentWeatherDTO() {
        return CurrentWeatherDTO.builder()
                .temperatureMin(14)
                .temperature(15)
                .temperatureMax(16)
                .temperatureFeelsLike(17)
                .humidity(50)
                .description("test").build();
    }
}
