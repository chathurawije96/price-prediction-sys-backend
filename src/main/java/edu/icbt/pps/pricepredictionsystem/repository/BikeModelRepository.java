package edu.icbt.pps.pricepredictionsystem.repository;

import edu.icbt.pps.pricepredictionsystem.domain.BikeModel;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeModelRepository extends JpaRepository<BikeModel,Long> {
    List<BikeModel> findAllByBikeBrand_Id(Long id) throws DataAccessException;
}
