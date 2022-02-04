package com.robinfood.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.backend.service.CustomerFeedbackService;
import com.robinfood.models.enums.FeedbackQuestionType;
import com.robinfood.models.request.CustomerFeedbackRequest;
import com.robinfood.models.request.FeedbackOptionAnswerRequest;
import com.robinfood.models.request.FeedbackQuestionRequest;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerFeedbackController.class)
@EnableWebMvc
class CustomerFeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerFeedbackService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCustomerFeedbackWithDescriptionNull() throws Exception {
        this.mockMvc.perform(post("/v0/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildRequest(TestType.NOT_DESCRIPTION)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        IsEqual.equalTo("Description can not null")));
    }

    @Test
    void createCustomerFeedbackWithQuestionsEmpty() throws Exception {
        this.mockMvc.perform(post("/v0/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildRequest(TestType.EMPTY_QUESTIONS)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        IsEqual.equalTo("Feedback questions can not empty")));
    }

    @Test
    void createCustomerFeedbackWithQuestionOptionsEmpty() throws Exception {
        this.mockMvc.perform(post("/v0/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildRequest(TestType.ANSWER_OPTIONS_EMPTY)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        IsEqual.equalTo("Feedback questions can not null")));
    }

    @Test
    void getCustomerFeedback() throws Exception {
        this.mockMvc.perform(get("/v0/feedback")).andDo(print()).andExpect(status().isOk());
    }

    private String buildRequest(TestType testType) throws JsonProcessingException {
        FeedbackOptionAnswerRequest option = new FeedbackOptionAnswerRequest();
        option.setAnswerDescription("Yes");

        FeedbackQuestionRequest question = new FeedbackQuestionRequest();
        question.setQuestionStr("Your will pass this challenge?");
        question.setAnswers(testType == TestType.ANSWER_OPTIONS_EMPTY ? new ArrayList<>() : Arrays.asList(option));
        question.setType(FeedbackQuestionType.MULTIPLE);

        CustomerFeedbackRequest request = new CustomerFeedbackRequest();
        request.setDescription(testType == TestType.NOT_DESCRIPTION ? null : "Feedback test");
        request.setQuestions(testType == TestType.EMPTY_QUESTIONS ? new ArrayList<>() : Arrays.asList(question));

        return objectMapper.writeValueAsString(request);
    }

    private enum TestType {
        NOT_DESCRIPTION,
        EMPTY_QUESTIONS,
        ANSWER_OPTIONS_EMPTY
    }
}