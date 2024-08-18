package edu.icbt.pps.pricepredictionsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricePredictRequest {

    private Long bikeType;
    private Long brand;
    private Long model;
    private Integer year;
    private Integer mileage;
    private Integer capacity;

}
