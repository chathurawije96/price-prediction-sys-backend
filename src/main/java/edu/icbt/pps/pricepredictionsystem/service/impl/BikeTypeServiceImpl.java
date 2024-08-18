package edu.icbt.pps.pricepredictionsystem.service.impl;

import edu.icbt.pps.pricepredictionsystem.domain.BikeType;
import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.repository.BikeTypeRepository;
import edu.icbt.pps.pricepredictionsystem.service.BikeTypeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeTypeServiceImpl extends GenericServiceImpl<BikeType,Long> implements BikeTypeService {
    @Autowired
    private BikeTypeRepository bikeTypeRepository;
    @PostConstruct
    void init(){
        init(bikeTypeRepository);
    }

    @Override
    public List<MasterDataResponse> getAllBikeTypes() throws ServiceException {
        return getBikeTypes(bikeTypeRepository.findAll());
    }

    private List<MasterDataResponse> getBikeTypes(List<BikeType> all) {
        List<MasterDataResponse> masterDataResponses = new ArrayList<>();
        all.forEach(bikeType -> {
            masterDataResponses.add(MasterDataResponse.builder()
                            .id(bikeType.getId())
                            .name(bikeType.getName())
                            .build());
        });
        return masterDataResponses;
    }
}
