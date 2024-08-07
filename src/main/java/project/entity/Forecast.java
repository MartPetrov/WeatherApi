package project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "forecasts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "max_temperature", nullable = false)
    private double maxTemperature;

    @Column(name = "min_temperature", nullable = false)
    private double minTemperature;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;


    @NotNull
    @Column(name = "date", nullable = false)
    private String date;

    @Override
    public String toString() {
        return String.format(
                "\t-min temperature: %.2f%n" +
                "\t--max temperature: %.2f%n" +
                this.minTemperature,
                this.maxTemperature);
    }

}
