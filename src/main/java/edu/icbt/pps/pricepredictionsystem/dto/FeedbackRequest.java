package edu.icbt.pps.pricepredictionsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {
    private Integer rate;
    private String message;
    private String contactNo;
}
