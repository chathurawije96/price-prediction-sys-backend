package edu.icbt.pps.pricepredictionsystem.config;


import edu.icbt.pps.pricepredictionsystem.domain.EntityBase;
import edu.icbt.pps.pricepredictionsystem.domain.User;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import edu.icbt.pps.pricepredictionsystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {

            User user = userRepository.findUserByEmailAndStatus(email, EntityBase.Status.ACTIVE);
            List<String> roles = new ArrayList<>();
            roles.add(user.getUserType().name());
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(roles.toArray(new String[0]))
                    .build();
        } catch (DataAccessException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

    }
}
