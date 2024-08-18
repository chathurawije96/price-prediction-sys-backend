package edu.icbt.pps.pricepredictionsystem.service.impl;

import edu.icbt.pps.pricepredictionsystem.domain.UserFeedback;
import edu.icbt.pps.pricepredictionsystem.repository.UserFeedbackRepository;
import edu.icbt.pps.pricepredictionsystem.service.UserFeedbackService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackServiceImpl extends GenericServiceImpl<UserFeedback,Long> implements UserFeedbackService {
    @Autowired
    private UserFeedbackRepository userFeedbackRepository;

    @PostConstruct
    void init(){
        init(userFeedbackRepository);
    }
}
