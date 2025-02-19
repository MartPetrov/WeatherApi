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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
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
        if (this.forecastRepository.count() == 0) {
            String json = this.readForecastsFromFile();
            ImportForecastDTO[] importForecastDTOS = this.gson.fromJson(json, ImportForecastDTO[].class);
            return Arrays.stream(importForecastDTOS).map(this::importForecast).collect(Collectors.joining("\n"
            ));
        }
        return "Import is not needed";
    }

    @Override
    public String importForecast(ImportForecastDTO importForecastDTO) {
        Forecast forecast = this.modelMapper.map(importForecastDTO, Forecast.class);
        this.forecastRepository.save(forecast);
        return "Forecasts are imported";
    }

    @Override
    public String findForecastByCityAndDate(String city) {
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.toString();
        Optional<Forecast> forecastsByCity= this.forecastRepository.getForecastsByCityAndDate(city,formattedDate);
        return forecastsByCity.map(gson::toJson).orElse(null);
    }

    @Override
    public void addForecastRest(ImportForecastDTO importForecastDTO) {
        Forecast forecast = this.modelMapper.map(importForecastDTO, Forecast.class);
        this.forecastRepository.save(forecast);
    }

    @Override
    public void findAndRemoveForecastById(Long id) {
        this.forecastRepository.deleteById(id);
    }
}
