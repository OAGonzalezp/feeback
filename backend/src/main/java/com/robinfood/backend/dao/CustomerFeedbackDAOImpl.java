package com.robinfood.backend.dao;

import com.robinfood.backend.exception.FeedbackOptionAnswerEmptyException;
import com.robinfood.backend.repository.ICustomerFeedbackRepository;
import com.robinfood.models.enums.FeedbackQuestionType;
import com.robinfood.models.enums.ResponseCodes;
import com.robinfood.models.feedback.CustomerFeedback;
import com.robinfood.models.feedback.FeedbackOptionAnswer;
import com.robinfood.models.feedback.FeedbackQuestion;
import com.robinfood.models.request.CustomerFeedbackRequest;
import com.robinfood.models.request.FeedbackOptionAnswerRequest;
import com.robinfood.models.request.FeedbackQuestionRequest;
import com.robinfood.models.response.CustomerFeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerFeedbackDAOImpl implements CustomerFeedbackDAO {

    @Autowired
    ICustomerFeedbackRepository customerFeedbackRepository;

    @Override
    public CustomerFeedback save(CustomerFeedbackRequest feedbackRequest) {
        CustomerFeedback customerFeedback = new CustomerFeedback();
        customerFeedback.setDescription(feedbackRequest.getDescription());
        customerFeedback.setQuestions(buildQuestions(feedbackRequest, customerFeedback));

        return customerFeedbackRepository.save(customerFeedback);
    }

    @Override
    public List<CustomerFeedbackResponse> getFeedbackList() {

        List<CustomerFeedback> feedbacks = customerFeedbackRepository.findAll();
        List<CustomerFeedbackResponse> feedbackResponseList = feedbacks.stream().map(x ->{
            CustomerFeedbackResponse feedbackResponse = new CustomerFeedbackResponse();
            feedbackResponse.setId(x.getId());
            feedbackResponse.setDescription(x.getDescription());
            feedbackResponse.setQuestions(buildQuestionsResponse(x.getQuestions()));

            return feedbackResponse;
        }).collect(Collectors.toList());

        return feedbackResponseList;
    }

    private Set<FeedbackQuestion> buildQuestions(CustomerFeedbackRequest feedbackRequest, CustomerFeedback customerFeedback) {
        Set<FeedbackQuestion> feedbackQuestions = Optional.ofNullable(feedbackRequest.getQuestions())
                .orElse(new ArrayList<>()).stream().map(x -> {
                    FeedbackQuestion question = new FeedbackQuestion();
                    question.setQuestionStr(x.getQuestionStr());
                    question.setType(x.getType());
                    question.setCustomerFeedback(customerFeedback);
                    question.setOptionAnswers(buildOptions(x, question));

                    if(FeedbackQuestionType.MULTIPLE.equals(question.getType()) && (x.getAnswers() == null || x.getAnswers().isEmpty())) {
                        throw new FeedbackOptionAnswerEmptyException();
                    }

                    return question;
                }).collect(Collectors.toSet());

        return feedbackQuestions;
    }

    private Set<FeedbackOptionAnswer> buildOptions(FeedbackQuestionRequest questionRequest, FeedbackQuestion question) {
        Set<FeedbackOptionAnswer> options = Optional.ofNullable(questionRequest.getAnswers()).orElse(new ArrayList<>())
                .stream().map(x -> {
                    FeedbackOptionAnswer answer = new FeedbackOptionAnswer();
                    answer.setAnswerDescription(x.getAnswerDescription());
                    answer.setQuestion(question);

                    return answer;
                }).collect(Collectors.toSet());

        return options;
    }

    private List<FeedbackQuestionRequest> buildQuestionsResponse(Set<FeedbackQuestion> questionSet) {
        List<FeedbackQuestionRequest> feedbackQuestions = questionSet.stream().map(x -> {
            FeedbackQuestionRequest request = new FeedbackQuestionRequest();
            request.setQuestionStr(x.getQuestionStr());
            request.setType(x.getType());
            request.setAnswers(buildAnswerOptionResponse(x.getOptionAnswers()));

            return request;
        }).collect(Collectors.toList());
        return feedbackQuestions;
    }

    private List<FeedbackOptionAnswerRequest> buildAnswerOptionResponse(Set<FeedbackOptionAnswer> optionAnswers) {
        List<FeedbackOptionAnswerRequest> optionAnswerList = optionAnswers.stream().map(x -> {
            FeedbackOptionAnswerRequest optionAnswer = new FeedbackOptionAnswerRequest();
            optionAnswer.setAnswerDescription(x.getAnswerDescription());

            return optionAnswer;
        }).collect(Collectors.toList());

        return optionAnswerList;
    }
}
