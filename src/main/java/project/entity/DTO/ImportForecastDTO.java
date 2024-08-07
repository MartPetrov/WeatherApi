package project.entity.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ImportForecastDTO {


    @NotNull
    @DecimalMin("-20")
    @DecimalMax("60")
    private double maxTemperature;

    @DecimalMin("-50")
    @DecimalMax("40")
    @NotNull
    private double minTemperature;

    @NotNull
    private String city;

    @NotNull
    private String date;
}
