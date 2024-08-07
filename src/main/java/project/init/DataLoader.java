package project.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.service.ForecastService;

import java.io.IOException;

@Component
public class DataLoader implements ApplicationRunner {

    private final ForecastService forecastService;

    @Autowired
    public DataLoader(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    public void run(ApplicationArguments args) throws IOException {
        forecastService.importForecasts();
    }
}
