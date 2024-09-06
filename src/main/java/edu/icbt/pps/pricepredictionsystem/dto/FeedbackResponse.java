package edu.icbt.pps.pricepredictionsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackResponse {
    private String message;
    private Integer rate;
    private String dateTime;
    private String name;
}
