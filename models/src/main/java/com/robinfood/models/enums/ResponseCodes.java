package com.robinfood.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.robinfood.models.response.DataResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCodes {
    OK(1, null),
    NOK_FEEDBACK_OPTIONS_ANSWER_EMPTY(1001, "Empty options answer for MULTIPLE question");

    private DataResponse data = null;

    private int code;
    private String message;

    ResponseCodes(Integer code, String msg) {
        data = new DataResponse(code, msg);
        this.code = code;
        this.message = msg;
    }

    @JsonValue
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final DataResponse value() {
        return data;
    }

}
