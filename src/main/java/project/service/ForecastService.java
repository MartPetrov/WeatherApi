package project.service;


import project.entity.DTO.ImportForecastDTO;

import java.io.IOException;
import java.util.Date;

public interface ForecastService {
    boolean areImported();

    String readForecastsFromFile() throws IOException;

    String importForecasts() throws IOException;

    String importForecast(ImportForecastDTO importForecastDTO);

    String findForecastByCityAndDate(String city, Date date);

    void addForecastRest(ImportForecastDTO importForecastDTO);

    void findAndRemoveForecastById(Long id);

}
