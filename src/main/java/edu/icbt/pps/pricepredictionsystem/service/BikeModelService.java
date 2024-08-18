package edu.icbt.pps.pricepredictionsystem.service;

import edu.icbt.pps.pricepredictionsystem.domain.BikeModel;
import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;

import java.util.List;

public interface BikeModelService extends GenericService<BikeModel,Long> {
    List<MasterDataResponse> getAllBikeModels(Long id) throws ServiceException;
}
