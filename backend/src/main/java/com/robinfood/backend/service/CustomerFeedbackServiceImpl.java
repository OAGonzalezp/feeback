package com.robinfood.backend.service;

import com.robinfood.backend.dao.CustomerFeedbackDAO;
import com.robinfood.models.feedback.CustomerFeedback;
import com.robinfood.models.request.CustomerFeedbackRequest;
import com.robinfood.models.response.CustomerFeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {

    @Autowired
    CustomerFeedbackDAO customerFeedbackDAO;

    @Override
    public CustomerFeedback save(CustomerFeedbackRequest feedbackRequest) {
        return customerFeedbackDAO.save(feedbackRequest);
    }

    @Override
    public List<CustomerFeedbackResponse> getFeedbackList() {
        return customerFeedbackDAO.getFeedbackList();
    }
}
