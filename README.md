# weather-app

Run the app like a regular Spring Boot app.

Call the endpoint:
```
GET http://localhost:8080/weather/current?location=London
```

It's also possible to use a combination of city name and country code separated by comma:
```
GET http://localhost:8080/weather/current?location=London,uk
```

We use [Open Weather](https://openweathermap.org/) to obtain real weather information.
