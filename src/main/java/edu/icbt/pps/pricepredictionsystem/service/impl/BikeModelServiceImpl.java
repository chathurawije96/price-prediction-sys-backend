package edu.icbt.pps.pricepredictionsystem.service.impl;

import edu.icbt.pps.pricepredictionsystem.domain.BikeModel;
import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.repository.BikeModelRepository;
import edu.icbt.pps.pricepredictionsystem.service.BikeModelService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeModelServiceImpl extends GenericServiceImpl<BikeModel,Long> implements BikeModelService {
    @Autowired
    private BikeModelRepository bikeModelRepository;

    @PostConstruct
    void init(){
        init(bikeModelRepository);
    }

    @Override
    public List<MasterDataResponse> getAllBikeModels(Long id) throws ServiceException {
        try {
            return getListBikeModels(bikeModelRepository.findAllByBikeBrand_Id(id));
        } catch (DataAccessException e) {
            throw translateException(e);
        }
    }

    private List<MasterDataResponse> getListBikeModels(List<BikeModel> allByBikeBrandId) {
        List<MasterDataResponse>  masterDataResponses = new ArrayList<>();
        allByBikeBrandId.forEach(bikeModel -> {
            masterDataResponses.add(MasterDataResponse.builder()
                            .id(bikeModel.getId())
                            .name(bikeModel.getName())
                            .build());
        });
        return masterDataResponses;
    }
}
