package com.robinfood.backend.controller;

import com.robinfood.backend.service.CustomerFeedbackService;
import com.robinfood.models.enums.ResponseCodes;
import com.robinfood.models.feedback.CustomerFeedback;
import com.robinfood.models.request.CustomerFeedbackRequest;
import com.robinfood.models.response.CustomerFeedbackResponse;
import com.robinfood.models.response.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v0")
public class CustomerFeedbackController {

    Logger logger = LoggerFactory.getLogger(CustomerFeedbackController.class);

    @Autowired
    private CustomerFeedbackService customerFeedbackService;

    @PostMapping(value = "/feedback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse> createCustomerFeedback(@Valid @RequestBody final CustomerFeedbackRequest customerFeedbackRequest) {
        DataResponse response = new DataResponse();
        logger.debug("Creating feedback form for custom");
        logger.debug("Request received {}", customerFeedbackRequest);

        CustomerFeedback feedback = customerFeedbackService.save(customerFeedbackRequest);

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(feedback.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/feedback")
    public ResponseEntity<DataResponse<List<CustomerFeedbackResponse>>> getCustomerFeedback() {
        DataResponse response = new DataResponse();

        List<CustomerFeedbackResponse> feedback = customerFeedbackService.getFeedbackList();

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(feedback);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
