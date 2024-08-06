
package project.web.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.service.ForecastService;

@RestController
public class WeatherController {

    private ForecastService forecastService;

    @Autowired
    public WeatherController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast")
    public String getForecast(@RequestParam String city) {
        String forecastByCity = forecastService.findForecastByCity(city);
        return forecastByCity;
    }
    //    http://localhost:8081/findForecastsByCity?city=Plovdiv
}

