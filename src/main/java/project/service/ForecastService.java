package project.service;


import project.entity.DTO.ImportForecastDTO;

import java.io.IOException;

public interface ForecastService {
    boolean areImported();

    String readForecastsFromFile() throws IOException;

    String importForecasts() throws IOException;

    String importForecast(ImportForecastDTO importForecastDTO);

    String findForecastByCity(String city);
}
