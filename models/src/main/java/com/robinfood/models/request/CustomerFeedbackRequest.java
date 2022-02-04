package com.robinfood.models.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomerFeedbackRequest implements Serializable {

    @Valid
    @NotNull(message = "Description can not null")
    private String description;
    @Valid
    @NotNull(message = "Feedback questions can not null")
    @NotEmpty(message = "Feedback questions can not empty")
    private List<FeedbackQuestionRequest> questions;

}
