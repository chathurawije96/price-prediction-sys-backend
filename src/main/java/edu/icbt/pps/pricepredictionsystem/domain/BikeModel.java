package edu.icbt.pps.pricepredictionsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bike_model")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@ToString(callSuper = true, includeFieldNames = false)
public class BikeModel extends EntityBase {

  public BikeModel(Long id) {
    super.setId(id);
  }

  @NotBlank(message = "Bike model name cannot be empty")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "bike_brand_id")
  private BikeBrand bikeBrand;

  @Builder
  public BikeModel(Long id, Status status, Date createdAt, Date updatedAt, String name, BikeBrand bikeBrand) {
    super(id, status, createdAt, updatedAt);
    this.name = name;
    this.bikeBrand = bikeBrand;
  }
}
