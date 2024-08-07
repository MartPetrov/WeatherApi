
package project.web.restControllers;

import io.micrometer.observation.ObservationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.DTO.ImportForecastDTO;
import project.entity.Forecast;
import project.service.ForecastService;

import java.util.Optional;

@RestController
public class WeatherController {

    private ForecastService forecastService;
    private ModelMapper modelMapper;

    @Autowired
    public WeatherController(ForecastService forecastService, ModelMapper modelMapper) {
        this.forecastService = forecastService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/forecast")
    public ResponseEntity<String> getForecast(@RequestParam String city) {
        String forecastByCity = forecastService.findForecastByCityAndDate(city);
        return ResponseEntity.of(Optional.ofNullable(forecastByCity));
    }

    @PostMapping("/forecast-add")
    public String addForecast(@RequestParam String city, @RequestParam Double minTemperature, @RequestParam Double maxTemperature , @RequestParam String date) {
        ImportForecastDTO importForecastDTO = new ImportForecastDTO();
        importForecastDTO.setCity(city);
        importForecastDTO.setMinTemperature(minTemperature);
        importForecastDTO.setMaxTemperature(maxTemperature);
        importForecastDTO.setDate(date);
        forecastService.addForecastRest(importForecastDTO);
        return importForecastDTO.toString();
    }

    @DeleteMapping("/forecast-delete")
    public String addForecast(@RequestParam Long id) {
        forecastService.findAndRemoveForecastById(id);
        return "Forecast deleted: id = " + id;
    }
}

