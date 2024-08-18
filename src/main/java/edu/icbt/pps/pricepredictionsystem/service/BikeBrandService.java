package edu.icbt.pps.pricepredictionsystem.service;

import edu.icbt.pps.pricepredictionsystem.domain.BikeBrand;
import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;

import java.util.List;

public interface BikeBrandService extends GenericService<BikeBrand,Long>{
    List<MasterDataResponse> getAllBikeBrands(Long id) throws ServiceException;
}
