package edu.icbt.pps.pricepredictionsystem.service.workflow.impl;

import edu.icbt.pps.pricepredictionsystem.client.ModelRestClient;
import edu.icbt.pps.pricepredictionsystem.domain.*;
import edu.icbt.pps.pricepredictionsystem.dto.FeedbackRequest;
import edu.icbt.pps.pricepredictionsystem.dto.PricePredictRequest;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.service.*;
import edu.icbt.pps.pricepredictionsystem.service.workflow.WorkflowService;
import edu.icbt.pps.pricepredictionsystem.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private BikeTypeService bikeTypeService;
    @Autowired
    private BikeBrandService bikeBrandService;
    @Autowired
    private BikeModelService bikeModelService;
    @Autowired
    private ModelRestClient modelRestClient;
    @Autowired
    private UserFeedbackService userFeedbackService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonUtil commonUtil;

    @Override
    public String predictPrice(PricePredictRequest pricePredictRequest) throws ServiceException {
        Optional<BikeType> bikeType = bikeTypeService.findById(pricePredictRequest.getBikeType());
        Optional<BikeBrand> bikeBrand = bikeBrandService.findById(pricePredictRequest.getBrand());
        Optional<BikeModel> bikeModel = bikeModelService.findById(pricePredictRequest.getModel());
        doValidation(bikeModel,bikeType,bikeBrand);
        Map<String,Object> request = new HashMap<>();
        request.put("Bike Type",bikeType.get().getName());
        request.put("Brand",bikeBrand.get().getName());
        request.put("Model",bikeModel.get().getName());
        request.put("Mileage",pricePredictRequest.getMileage());
        request.put("Year",pricePredictRequest.getYear());
        request.put("Capacity", pricePredictRequest.getCapacity());

        double price = modelRestClient.predictPrice(request);
        BigDecimal formattedPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);

        return "LKR "+formattedPrice.toString();
    }

    @Override
    public String saveFeedback(FeedbackRequest feedbackRequest) throws ServiceException {
        userFeedbackService.save(UserFeedback.builder()
                        .createdAt(new Date())
                        .user(userService.findUserByEmail(commonUtil.getUserName()))
                        .rate(feedbackRequest.getRate())
                        .status(EntityBase.Status.ACTIVE)
                        .updatedAt(new Date())
                        .message(feedbackRequest.getMessage())
                        .contactNumber(feedbackRequest.getContactNo())
                        .build());
        return "Customer feedback added successfully..";
    }

    private void doValidation(Optional<BikeModel> bikeModel, Optional<BikeType> bikeType, Optional<BikeBrand> bikeBrand) throws ServiceException {
        if (bikeType.isEmpty()){
            throw new ServiceException("Invalid bike type");
        }
        if (bikeBrand.isEmpty()){
            throw new ServiceException("Invalid bike brand");
        }
        if (bikeModel.isEmpty()){
            throw new ServiceException("Invalid bike model");
        }
    }
}
