package com.robinfood.backend.advice;

import com.robinfood.backend.exception.FeedbackOptionAnswerEmptyException;
import com.robinfood.models.enums.ResponseCodes;
import com.robinfood.models.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice {
	
	@Autowired
    HttpServletRequest httpServletRequest;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processUnmergeException(final MethodArgumentNotValidException ex) {

        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        DataResponse response = new DataResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(messages.stream().collect(Collectors.joining(",")));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeedbackOptionAnswerEmptyException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processEmptyAnswerException(final FeedbackOptionAnswerEmptyException ex) {
        return new ResponseEntity(ResponseCodes.NOK_FEEDBACK_OPTIONS_ANSWER_EMPTY, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processGenericException(final Exception ex) {

        DataResponse response = new DataResponse();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
