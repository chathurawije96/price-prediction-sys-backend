package edu.icbt.pps.pricepredictionsystem.service.workflow;

import edu.icbt.pps.pricepredictionsystem.dto.FeedbackRequest;
import edu.icbt.pps.pricepredictionsystem.dto.PricePredictRequest;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;

public interface WorkflowService {
    String predictPrice(PricePredictRequest pricePredictRequest) throws ServiceException;

    String saveFeedback(FeedbackRequest feedbackRequest) throws ServiceException;
}
