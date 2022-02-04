package com.robinfood.backend.exception;

import com.robinfood.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FeedbackOptionAnswerEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FeedbackOptionAnswerEmptyException() {
    }
    public FeedbackOptionAnswerEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeedbackOptionAnswerEmptyException(ResponseCodes response) {
        super(response.getMessage());
    }

    public FeedbackOptionAnswerEmptyException(String message) {
        super(message);
    }

    public FeedbackOptionAnswerEmptyException(Throwable cause) {
        super(cause);
    }

}
