
package project.web.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.entity.DTO.ImportForecastDTO;
import project.service.ForecastService;

import java.util.Date;

@RestController
public class WeatherController {

    private ForecastService forecastService;

    @Autowired
    public WeatherController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast")
    public String getForecast(@RequestParam String city) {
        Date date = new Date();
        String forecastByCity = forecastService.findForecastByCityAndDate(city, date);
        return forecastByCity;
    }
    //    http://localhost:8081/findForecastsByCity?city=Plovdiv

    @PostMapping("/forecast-add")
    public String addForecast(@RequestParam String city,@RequestParam Double minTemperature,@RequestParam Double maxTemperature) {
        ImportForecastDTO importForecastDTO = new ImportForecastDTO();
        importForecastDTO
        forecastService.addForecastRest(importForecastDTO);
        return importForecastDTO.toString();
    }

    @DeleteMapping("/forecast-delete")
    public String addForecast(@RequestParam Long id) {
        forecastService.findAndRemoveForecastById(id);
        return "Forecast deleted: id = " + id;
    }
}

