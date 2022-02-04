package com.robinfood.models.request;

import com.robinfood.models.enums.FeedbackQuestionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class FeedbackQuestionRequest implements Serializable {

    @Valid
    @NotNull(message = "Question description can not null")
    private String questionStr;

    @Valid
    @NotNull(message = "Question type can not null")
    private FeedbackQuestionType type;

    private List<FeedbackOptionAnswerRequest> answers;
}
