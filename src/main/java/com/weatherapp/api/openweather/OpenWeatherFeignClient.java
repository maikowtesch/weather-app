package com.weatherapp.api.openweather;

import com.weatherapp.api.openweather.dto.CurrentWeatherApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign Client responsible for connecting to the Open Weather API.
 */
@FeignClient(
        value = "openweather-api",
        url = "${api.openweather.base}",
        configuration = OpenWeatherFeignConfiguration.class)
public interface OpenWeatherFeignClient {

    /**
     * Returns the current weather for a given city.
     * @param location City name and country code separated by comma. Possible values "<city name>" or "<city name>,<country code>".
     * @param units The temperature unit. Possible values: standard(Kelvin), metric(Celsius) or imperial(Fahrenheit).
     * @param appid The api key.
     * @return {@link CurrentWeatherApiDTO}
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json",
            value = "/weather")
    CurrentWeatherApiDTO currentWeatherData(@RequestParam("q") String location,
                                            @RequestParam("units") String units,
                                            @RequestParam("appid") String appid);
}
