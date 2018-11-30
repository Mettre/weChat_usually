package com.mettre.usually.pojoVM;

import com.mettre.usually.enum_.StateEnum;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class FeedbackModifyVM {

    @Min(value = 1, message = "反馈id为空")
    private Long feedbackId;

    private StateEnum state;

}
