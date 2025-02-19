package project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Forecast;

import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

@Query(value = "SELECT * FROM forecasts f WHERE f.city = :city AND date = :date limit 1;",nativeQuery = true)
Optional<Forecast>getForecastsByCityAndDate(String city, String date);
}
