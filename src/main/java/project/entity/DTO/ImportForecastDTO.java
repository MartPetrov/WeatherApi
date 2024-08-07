package project.entity.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
public class ImportForecastDTO {

    @Getter
    @Setter
    @NotNull
    @DecimalMin("-20")
    @DecimalMax("60")
    private double maxTemperature;

    @Getter
    @Setter
    @DecimalMin("-50")
    @DecimalMax("40")
    @NotNull
    private double minTemperature;
    @Getter
    @Setter
    @NotNull
    private String city;
    @Getter
    @Setter
    @NotNull
    private Date date;

}
