package edu.icbt.pps.pricepredictionsystem.repository;

import edu.icbt.pps.pricepredictionsystem.domain.BikeBrand;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeBrandRepository extends JpaRepository<BikeBrand,Long> {

    List<BikeBrand> findAllByBikeType_Id(Long id) throws DataAccessException;
}
