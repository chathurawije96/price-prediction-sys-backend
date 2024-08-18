package edu.icbt.pps.pricepredictionsystem.controller;

import edu.icbt.pps.pricepredictionsystem.domain.User;
import edu.icbt.pps.pricepredictionsystem.dto.*;
import edu.icbt.pps.pricepredictionsystem.dto.common.ErrorResponse;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceExceptionType;
import edu.icbt.pps.pricepredictionsystem.service.UserService;
import edu.icbt.pps.pricepredictionsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/auth")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String email = authentication.getName();
            User user = userService.findUserByEmail(email);
            String token = jwtUtil.generateToken(user.getUsername());
            LoginResponse loginRes = new LoginResponse(token,user.getId(),user.getEmail(),user.getUserType().name());

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        try {
            validateEmailAddress(registerRequest.getEmail().trim());
            User user = userService.register(registerRequest);
            return ResponseEntity.ok(RegisterResponse.builder().userId(user.getId())
                    .email(user.getEmail())
                    .build());
        } catch (ServiceException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/register/verify")
    public ResponseEntity registerVerify(@RequestBody RegistrationVerifyRequest request) {
        try {
            userService.registerVerify(request);
            return ResponseEntity.ok(RegistrationVerifyResponse.builder().message("Email Verification Success").build());
        } catch (ServiceException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    private void validateEmailAddress(String email) throws ServiceException {
        //email validation
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            throw new ServiceException(ServiceExceptionType.VALIDATION_FAILED,
                    "", "Invalid email. Try again.");
        }
        //user validate
        User user = userService.findUserByEmail(email.trim());
        if (user != null) {
            throw new ServiceException(ServiceExceptionType.VALIDATION_FAILED,
                    "", "Entered email is already registered. Please go to login.");
        }
    }
}
