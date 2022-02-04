package com.robinfood.models.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class FeedbackOptionAnswerRequest implements Serializable {

    @Valid
    @NotNull(message = "Answer description can not null")
    private String answerDescription;

}
