package com.weatherapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.api.openweather.OpenWeatherFeignClient;
import com.weatherapp.api.openweather.dto.*;
import com.weatherapp.api.openweather.exception.OpenWeatherException;
import com.weatherapp.core.api.dto.CurrentWeatherDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
@ComponentScan(basePackages = "com.weatherapp")
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenWeatherFeignClient openWeatherClient;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void currentWeatherEndpointSuccess() throws Exception {
        CurrentWeatherApiDTO testDTO = createOpenWeatherDTO();

        when(openWeatherClient.currentWeatherData(anyString(), anyString(), anyString())).thenReturn(testDTO);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/weather/current?location=location"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        CurrentWeatherDTO result = mapper.readValue(mvcResult.getResponse().getContentAsString(), CurrentWeatherDTO.class);

        assertEquals(testDTO.getMain().getTemp(), result.getTemperature(), 0);
        assertEquals(testDTO.getMain().getFeels_like(), result.getTemperatureFeelsLike(), 0);
        assertEquals(testDTO.getMain().getTemp_min(), result.getTemperatureMin(), 0);
        assertEquals(testDTO.getMain().getTemp_max(), result.getTemperatureMax(), 0);
        assertEquals(testDTO.getMain().getHumidity(), result.getHumidity());
        assertEquals(testDTO.getClouds().getAll(), result.getCloudPercentage());
        assertEquals(testDTO.getWeather().get(0).getMain(), result.getDescription());
    }

    @Test
    public void currentWeatherEndpointNotFound() throws Exception {
        when(openWeatherClient.currentWeatherData(anyString(), anyString(), anyString()))
                .thenThrow(new OpenWeatherException(HttpStatus.NOT_FOUND.value()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/weather/current?location=location"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("CITY_NOT_FOUND"));
    }

    @Test
    public void currentWeatherEndpointInternalError() throws Exception {
        when(openWeatherClient.currentWeatherData(anyString(), anyString(), anyString()))
                .thenThrow(new OpenWeatherException(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/weather/current?location=location"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
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
