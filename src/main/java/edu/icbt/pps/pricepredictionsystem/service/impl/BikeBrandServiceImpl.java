package edu.icbt.pps.pricepredictionsystem.service.impl;

import edu.icbt.pps.pricepredictionsystem.domain.BikeBrand;
import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.repository.BikeBrandRepository;
import edu.icbt.pps.pricepredictionsystem.service.BikeBrandService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeBrandServiceImpl extends GenericServiceImpl<BikeBrand,Long> implements BikeBrandService {
    @Autowired
    private BikeBrandRepository bikeBrandRepository;

    @PostConstruct
    void init(){
        init(bikeBrandRepository);
    }

    @Override
    public List<MasterDataResponse> getAllBikeBrands(Long id) throws ServiceException {
        try {
            return getListOfBrands(bikeBrandRepository.findAllByBikeType_Id(id));
        } catch (DataAccessException e) {
            throw translateException(e);
        }
    }

    private List<MasterDataResponse> getListOfBrands(List<BikeBrand> allByBikeTypeId) {
        List<MasterDataResponse> masterDataResponses = new ArrayList<>();
        allByBikeTypeId.forEach(bikeBrand -> {
            masterDataResponses.add(MasterDataResponse.builder()
                            .id(bikeBrand.getId())
                            .name(bikeBrand.getName())
                            .build());
        });
        return masterDataResponses;
    }
}
