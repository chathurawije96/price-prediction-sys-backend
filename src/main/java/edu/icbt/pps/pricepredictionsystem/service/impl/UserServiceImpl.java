package edu.icbt.pps.pricepredictionsystem.service.impl;

import edu.icbt.pps.pricepredictionsystem.domain.EmailVerification;
import edu.icbt.pps.pricepredictionsystem.domain.EntityBase;
import edu.icbt.pps.pricepredictionsystem.domain.User;
import edu.icbt.pps.pricepredictionsystem.dto.RegisterRequest;
import edu.icbt.pps.pricepredictionsystem.dto.RegistrationVerifyRequest;
import edu.icbt.pps.pricepredictionsystem.email.EmailSender;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceExceptionType;
import edu.icbt.pps.pricepredictionsystem.repository.UserRepository;
import edu.icbt.pps.pricepredictionsystem.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSender emailSender;

    @PostConstruct
    void init(){
        init(userRepository);
    }

    @Override
    public User findUserByEmail(String email) throws ServiceException {
        try {
            return userRepository.findUserByEmailAndStatus(email, EntityBase.Status.ACTIVE);
        } catch (DataAccessException e) {
            throw translateException(e);
        }
    }

    @Override
    public User register(RegisterRequest request) throws ServiceException {
        String otp = "" + ((int) (Math.random() * 9000) + 1000);
        User user = User.builder()
                .userType(User.UserType.CUSTOMER)
                .identityNo(request.getIdValue())
                .idType(User.IdType.valueOf(request.getIdType()))
                .lastLoggedOn(new Date())
                .name(request.getName())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .createdAt(new Date())
                .updatedAt(new Date())
                .status(EntityBase.Status.VERIFICATION_PENDING)
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailVerification(EmailVerification.builder()
                        .codeSentOn(new Date())
                        .status(EntityBase.Status.VERIFICATION_PENDING)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .source("Email")
                        .tries((byte) 0)
                        .verificationCode(otp)
                        .build())
                .build();
        User save = this.save(user);


        log.debug("Registration Email {} OTP : {}", save.getEmail(), otp);
        emailSender.sendEmail(request.getEmail().trim(),"PPS Verification",getOtpTemplateMessage(otp));

        return save;
    }

    @Override
    public void registerVerify(RegistrationVerifyRequest request) throws ServiceException {
        Optional<User> optionalUser = this.findById(request.getUserId());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getEmailVerification().getVerificationCode().equals(request.getOtp())){
                user.setStatus(EntityBase.Status.ACTIVE);
                user.getEmailVerification().setStatus(EntityBase.Status.VERIFICATION_SUCCESS);
                user.getEmailVerification().setVerifiedOn(new Date());
                user.getEmailVerification().setTriedOn(new Date());
                this.save(user);
            } else {
                user.getEmailVerification().setTriedOn(new Date());
                this.save(user);
                throw new ServiceException(ServiceExceptionType.VALIDATION_FAILED,"Email Verification Failed.. "+ user.getEmail());
            }
        } else {
            throw new ServiceException(ServiceExceptionType.VALIDATION_FAILED,"Invalid User");
        }
    }

    private String getOtpTemplateMessage(String otp) {
        String message = "Dear User,\n";
        message += "Please enter the code in your registration form.\n\n";
        message += "Code: " + otp + "\n";
        message += "Thank you for registering with us.\n\n";
        message += "PPS Team.\n";
        message += "www.pps.lk";
        return message;
    }
}
