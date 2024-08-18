package edu.icbt.pps.pricepredictionsystem.repository;

import edu.icbt.pps.pricepredictionsystem.domain.BikeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeTypeRepository extends JpaRepository<BikeType,Long> {
}
