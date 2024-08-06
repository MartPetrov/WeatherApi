package project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Forecast;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

@Query(value = "SELECT * FROM forecasts f WHERE f.city = :city limit 1;",nativeQuery = true)
    Forecast getForecastsByCity(String city);
}
