package project.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.DTO.ImportForecastDTO;
import project.entity.Forecast;
import project.repositories.ForecastRepository;
import project.service.ForecastService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;

import static project.constans.Paths.JSON_FORECAST;


@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;


    @Autowired
    public ForecastServiceImpl(
            ForecastRepository forecastRepository,
            ModelMapper modelMapper, Gson gson) {
        this.forecastRepository = forecastRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(JSON_FORECAST);
    }

    @Override
    public String importForecasts() throws IOException {
        String json = this.readForecastsFromFile();
        ImportForecastDTO[] importForecastDTOS = this.gson.fromJson(json, ImportForecastDTO[].class);
        return Arrays.stream(importForecastDTOS).map(this::importForecast).collect(Collectors.joining("\n"
        ));
    }

    @Override
    public String importForecast(ImportForecastDTO importForecastDTO) {
        Forecast forecast = this.modelMapper.map(importForecastDTO, Forecast.class);
        this.forecastRepository.save(forecast);
        return "Forecasts are imported";
    }

    @Override
    public String findForecastByCity(String city) {
        Forecast forecastsByCity = this.forecastRepository.getForecastsByCity(city);
        return gson.toJson(forecastsByCity);
    }
}
