package com.weatherapp.api.openweather;

import com.weatherapp.api.openweather.exception.OpenWeatherException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Decodes de HTTP response from the Feign Client in case there's an error.
 */
public class OpenWeatherErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new OpenWeatherException(response.status());
    }
}
