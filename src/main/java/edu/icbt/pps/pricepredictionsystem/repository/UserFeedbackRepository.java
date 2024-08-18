package edu.icbt.pps.pricepredictionsystem.repository;

import edu.icbt.pps.pricepredictionsystem.domain.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback,Long> {
}
