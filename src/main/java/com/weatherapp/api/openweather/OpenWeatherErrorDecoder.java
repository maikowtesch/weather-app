package com.weatherapp.api.openweather;

import com.weatherapp.api.openweather.exception.OpenWeatherException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Decodes the HTTP error response that comes from the Feign Client.
 */
public class OpenWeatherErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new OpenWeatherException(response.status());
    }
}
