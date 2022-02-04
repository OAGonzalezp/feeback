package com.robinfood.models.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum FeedbackQuestionType {
	MULTIPLE("MULTIPLE"),
	OPEN("OPEN");

	private final String name;

    private FeedbackQuestionType(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }

}
