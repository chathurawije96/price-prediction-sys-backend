package edu.icbt.pps.pricepredictionsystem.controller;

import edu.icbt.pps.pricepredictionsystem.dto.FeedbackRequest;
import edu.icbt.pps.pricepredictionsystem.dto.PricePredictRequest;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.service.workflow.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @PostMapping("/predict-price")
    ResponseEntity<?> predictPrice(@RequestBody PricePredictRequest pricePredictRequest){
        try {
            return ResponseEntity.ok(workflowService.predictPrice(pricePredictRequest));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/feedback")
    ResponseEntity<?> sendFeedback(@RequestBody FeedbackRequest feedbackRequest){
        try {
            return ResponseEntity.ok(workflowService.saveFeedback(feedbackRequest));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/feedback")
    ResponseEntity<?> allFeedback(){
        try {
            return ResponseEntity.ok(workflowService.allFeedback());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
