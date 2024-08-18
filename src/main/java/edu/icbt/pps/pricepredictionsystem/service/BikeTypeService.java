package edu.icbt.pps.pricepredictionsystem.service;

import edu.icbt.pps.pricepredictionsystem.domain.BikeType;
import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;

import java.util.List;

public interface BikeTypeService extends GenericService<BikeType,Long> {

    List<MasterDataResponse> getAllBikeTypes() throws ServiceException;
}
