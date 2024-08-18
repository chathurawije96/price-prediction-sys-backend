package edu.icbt.pps.pricepredictionsystem.service.impl;

import edu.icbt.pps.pricepredictionsystem.domain.EmailVerification;
import edu.icbt.pps.pricepredictionsystem.repository.EmailVerificationRepository;
import edu.icbt.pps.pricepredictionsystem.service.EmailVerificationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationServiceImpl extends GenericServiceImpl<EmailVerification,Long> implements EmailVerificationService {

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    @PostConstruct
    void init(){
        init(emailVerificationRepository);
    }
}
