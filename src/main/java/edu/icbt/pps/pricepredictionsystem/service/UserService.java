package edu.icbt.pps.pricepredictionsystem.service;

import edu.icbt.pps.pricepredictionsystem.domain.User;
import edu.icbt.pps.pricepredictionsystem.dto.RegisterRequest;
import edu.icbt.pps.pricepredictionsystem.dto.RegistrationVerifyRequest;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;


public interface UserService extends GenericService<User, Long> {
    User findUserByEmail(String email) throws ServiceException;
    User register(RegisterRequest request) throws ServiceException;

    void registerVerify(RegistrationVerifyRequest request) throws ServiceException;
}
