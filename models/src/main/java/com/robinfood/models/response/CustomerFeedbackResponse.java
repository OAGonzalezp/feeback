package com.robinfood.models.response;

import com.robinfood.models.request.CustomerFeedbackRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerFeedbackResponse extends CustomerFeedbackRequest {
    private Long id;
}
