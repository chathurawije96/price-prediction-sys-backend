package edu.icbt.pps.pricepredictionsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterDataResponse {
    private Long id;
    private String name;
}
