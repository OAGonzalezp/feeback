package com.robinfood.backend.repository;

import com.robinfood.models.feedback.CustomerFeedback;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ICustomerFeedbackRepository  extends JpaRepository<CustomerFeedback, Long> {
}
