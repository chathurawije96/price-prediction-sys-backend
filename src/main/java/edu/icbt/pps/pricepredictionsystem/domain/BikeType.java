package edu.icbt.pps.pricepredictionsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name = "bike_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@ToString(callSuper = true, includeFieldNames = false)
public class BikeType extends EntityBase {

  public BikeType(Long id) {
    super.setId(id);
  }

  @NotBlank(message = "Bike type name cannot be empty")
  private String name;


  @Builder
  public BikeType(Long id, Status status, Date createdAt, Date updatedAt, String name) {
    super(id, status, createdAt, updatedAt);
    this.name = name;
  }
}
