package edu.icbt.pps.pricepredictionsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bike_brand")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@ToString(callSuper = true, includeFieldNames = false)
public class BikeBrand extends EntityBase {

  public BikeBrand(Long id) {
    super.setId(id);
  }

  @NotBlank(message = "Bike brand name cannot be empty")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "bike_type_id")
  private BikeType bikeType;

  @Builder
  public BikeBrand(Long id, Status status, Date createdAt, Date updatedAt, String name, BikeType bikeType) {
    super(id, status, createdAt, updatedAt);
    this.name = name;
    this.bikeType = bikeType;
  }
}
