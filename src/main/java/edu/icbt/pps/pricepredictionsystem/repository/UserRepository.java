package edu.icbt.pps.pricepredictionsystem.repository;

import edu.icbt.pps.pricepredictionsystem.domain.EntityBase;
import edu.icbt.pps.pricepredictionsystem.domain.User;
import edu.icbt.pps.pricepredictionsystem.exception.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmailAndStatus(String email, EntityBase.Status status) throws DataAccessException;
}
