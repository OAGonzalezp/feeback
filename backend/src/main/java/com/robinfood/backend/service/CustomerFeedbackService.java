package com.robinfood.backend.service;

import com.robinfood.models.feedback.CustomerFeedback;
import com.robinfood.models.request.CustomerFeedbackRequest;
import com.robinfood.models.response.CustomerFeedbackResponse;

import java.util.List;

public interface CustomerFeedbackService {

    CustomerFeedback save(CustomerFeedbackRequest feedbackRequest);

    List<CustomerFeedbackResponse> getFeedbackList();
}
